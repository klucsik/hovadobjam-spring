package klucsik.hovadobjam.user;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @SneakyThrows
    public UserDto find(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Cannot find user with id:'%s'", id)));
        return UserMapper.INSTANCE.userToDto(user);
    }

    public void signUpUser(User user){
        final String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userRepository.save(user);
    }


    @SneakyThrows //TODO: OwO whats dis?
    public User loadUser(String userName) {
        return userRepository.findByUsername(userName).orElseThrow(() -> new NotFoundException(String.format("Cannot find user with name:'%s'", userName)));
    }
}
