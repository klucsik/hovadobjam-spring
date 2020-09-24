package klucsik.hovadobjam.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private UserService service;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> show(@PathVariable("id") Long id) {
        UserDto foundEntity = service.find(id);
        if (foundEntity == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundEntity);
        }
    }

    @PostMapping("/sign-up")
    ResponseEntity<UserDto> signUp(@RequestBody User user){
        service.signUpUser(user);
        return ResponseEntity.ok(service.find(user.getId()));
    }
}
