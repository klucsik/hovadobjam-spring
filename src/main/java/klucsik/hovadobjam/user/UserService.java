package klucsik.hovadobjam.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDto find(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(" Invalid user Id: " + id));
        return UserMapper.INSTANCE.usertToDto(user);
    }
}
