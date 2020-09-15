package klucsik.hovadobjam.trash;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trashes")
public class TrashRestController {

    @Autowired
    private TrashService service;

    @GetMapping("")
    public ResponseEntity<Page<TrashDto>> list(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "pagesize", required = false, defaultValue = "20") Integer pagesize
    ) {
        Page<TrashDto> customerPage = service.list(page, pagesize);
        if (customerPage == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(customerPage);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrashDto> show(@PathVariable("id") Long id) throws NotFoundException {
        TrashDto foundEntity = service.find(id);
        if (foundEntity==null){
            throw new NotFoundException(String.format("Cannot find trash with id:'%s'", id));
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