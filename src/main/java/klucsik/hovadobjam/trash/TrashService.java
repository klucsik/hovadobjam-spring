package klucsik.hovadobjam.trash;

import klucsik.hovadobjam.user.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TrashService {

    private final TrashRepository trashRepository;

    public TrashDto find(Long id) {
        Trash trash = trashRepository.findById(id).orElse(null);
        return TrashMapper.INSTANCE.trashToDto(trash);
    }

}
