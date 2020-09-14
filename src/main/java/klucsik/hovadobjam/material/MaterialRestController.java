package klucsik.hovadobjam.material;

import klucsik.hovadobjam.trash.TrashDto;
import klucsik.hovadobjam.trash.TrashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
