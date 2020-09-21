package klucsik.hovadobjam.trash;

import javassist.NotFoundException;
import klucsik.hovadobjam.exceptionhandling.InvalidInputException;
import klucsik.hovadobjam.material.MaterialRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class TrashService {

    private final TrashRepository trashRepository;
    private final MaterialRepository materialRepository;

    public TrashDto find(Long id) throws NotFoundException {
        Trash trash = trashRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Cannot find trash with id:'%s'", id)));
        return TrashMapper.INSTANCE.trashToDto(trash);
    }

    public Page<TrashDto> list(Integer page, Integer pageSize) {
        Pageable pageparams = PageRequest.of(page, pageSize);
        Page<Trash> trashPage = trashRepository.findAll(pageparams);
        return trashPage.map(TrashMapper.INSTANCE::trashToDto);
    }

    public void delete(Long id) {
        // TODO: Softdelete
        trashRepository.deleteById(id);
    }
    // if found, update, else create
    @Transactional
    public TrashDto save(TrashDto trashDto) throws NotFoundException {
        Trash trash = new Trash();
        if (trashDto.getId() != null) trash = trashRepository.findById(trashDto.getId()).orElse(null);
        TrashMapper.INSTANCE.dtoToTrash(trashDto,trash);
        //TODO: changes in the material fields should make a warning, you cant edit those from here

        //validate material presence, if not provided, ok, but if provided must be with a valid id.
        if (trashDto.getMaterialDto() != null) {
            Long materialId = trashDto.getMaterialDto().getId();
            if (materialId == null) throw new InvalidInputException("materialId id must be provided!");
            materialRepository.findById(materialId).orElseThrow(() -> new NotFoundException(String.format("Cannot find material with id:'%s'", materialId)));
        }
        trashRepository.saveAndFlush(trash);
        trashRepository.refresh(trash); //we refresh te object
        return TrashMapper.INSTANCE.trashToDto(trash); //we give back the refreshed object
    } // FIXME: The Entity Manager fails in put method

}
