package klucsik.hovadobjam.trash;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.*;

@Service
@AllArgsConstructor
@Transactional
public class TrashService {

    private final TrashRepository trashRepository;

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
        // TODO: Softdelete
        trashRepository.deleteById(id);
    }
    // if found, update, else create
    @Transactional
    public TrashDto save(@Valid TrashDto trashDto) {
        Trash trash = new Trash();
        if (trashDto.getId() != null) trash = trashRepository.findById(trashDto.getId()).orElse(null);
        TrashMapper.INSTANCE.dtoToTrash(trashDto,trash);
        //TODO: changes in the material fields should make a warning, you cant edit those from here
        trashRepository.saveAndFlush(trash);
        trashRepository.refresh(trash); //we refresh te object
        return TrashMapper.INSTANCE.trashToDto(trash); //we give back the refreshed object
    } // FIXME: The Entity Manager fails in put method

}
