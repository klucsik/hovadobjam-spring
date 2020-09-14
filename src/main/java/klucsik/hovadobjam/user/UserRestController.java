package klucsik.hovadobjam.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private UserService service;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> show(@PathVariable("id") Long id) {
        UserDto foundProduct = service.find(id);
        if (foundProduct == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundProduct);
        }
    }
}
