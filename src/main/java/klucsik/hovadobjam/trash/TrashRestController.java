package klucsik.hovadobjam.trash;

import klucsik.hovadobjam.user.UserDto;
import klucsik.hovadobjam.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/trashes")
public class TrashRestController {

    @Autowired
    private TrashService service;

    @GetMapping("/{id}")
    public ResponseEntity<TrashDto> show(@PathVariable("id") Long id) {
        TrashDto foundEntity = service.find(id);
        if (foundEntity == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundEntity);
        }
    }
}