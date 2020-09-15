package klucsik.hovadobjam.material;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/materials")
public class MaterialRestController {

    @Autowired
    private MaterialService service;

    @GetMapping("/{id}")
    public ResponseEntity<MaterialDto> show(@PathVariable("id") Long id) {
        MaterialDto foundEntity = service.find(id);
        if (foundEntity == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundEntity);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        if (service.find(id) == null) {
            return  new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            service.delete(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }
}
