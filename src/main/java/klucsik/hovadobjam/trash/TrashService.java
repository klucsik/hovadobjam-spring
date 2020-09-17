package klucsik.hovadobjam.trash;

import klucsik.hovadobjam.exceptionhandling.InvalidInputException;
import klucsik.hovadobjam.material.MaterialService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Service
@AllArgsConstructor
public class TrashService {

    private final TrashRepository trashRepository;
    private final MaterialService materialService;

    public TrashDto find(Long id) {
        Trash trash = trashRepository.findById(id).orElse(null);
        return TrashMapper.INSTANCE.trashToDto(trash);
    }

    public Page<TrashDto> list(Integer page, Integer pageSize) {
        Pageable pageparams = PageRequest.of(page, pageSize);
        Page<Trash> trashPage = trashRepository.findAll(pageparams);
        return trashPage.map(TrashMapper.INSTANCE::trashToDto);
    }

    public void delete(Long id) {
        Trash trash = trashRepository.findById(id).orElse(null);
        if (trash==null) return;
        trashRepository.delete(trash);
    }

    // if found, update, else create
    public TrashDto save(TrashDto trashDto) {
        //FIXME: This should skip the null properties on update, it should be done in the mapper. https://www.baeldung.com/spring-data-partial-update
        Trash trash = TrashMapper.INSTANCE.dtoToTrash(trashDto);
        //TODO: changes in the material fields should make a warning, you cant edit those from here
        //FIXME: use more elegant validation, I could not done it with annotations (always got rollback exception)
        validateTrash(trash); //If invalid, the rest is not reached.
        trashRepository.save(trash);
        return find(trash.getId()); //we give back the refreshed object
    } // FIXME: The associated object isnt fetched at this point

    private void validateTrash(Trash trash) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Trash>> violations = validator.validate(trash);
        if (!violations.isEmpty()) {
            throw new InvalidInputException(violations.toString());
        }
    }
}
