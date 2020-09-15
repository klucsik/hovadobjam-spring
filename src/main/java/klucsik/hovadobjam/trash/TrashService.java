package klucsik.hovadobjam.trash;

import klucsik.hovadobjam.user.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
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

    public void delete(Long id){
        Trash trash = trashRepository.findById(id).orElse(null);
        trashRepository.delete(trash);
    }

}
