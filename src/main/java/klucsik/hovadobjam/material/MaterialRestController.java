package klucsik.hovadobjam.material;

import javassist.NotFoundException;
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
    public ResponseEntity<MaterialDto> show(@PathVariable("id") Long id) throws NotFoundException {
        MaterialDto foundEntity = service.find(id);
        if (foundEntity == null){
            throw new NotFoundException(String.format("Cannot find material with id:'%s'", id));
        }
        return ResponseEntity.ok(foundEntity);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) throws NotFoundException {
        if (service.find(id)==null){
            throw new NotFoundException(String.format("Cannot find trash with id:'%s'", id));
        } else {
            service.delete(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }
}
