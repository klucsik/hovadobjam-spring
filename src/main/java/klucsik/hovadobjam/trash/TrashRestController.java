package klucsik.hovadobjam.trash;

import javassist.NotFoundException;
import klucsik.hovadobjam.exceptionhandling.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
        return ResponseEntity.ok(service.find(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrashDto> update(@PathVariable("id") Long id, @RequestBody @Valid TrashDto updatedTrash) {
        if (!updatedTrash.getId().equals(id))
            throw new InvalidInputException(String.format("entity Id '%d' and resource Id '%d' doesn't match!", id, updatedTrash.getId()));
        return ResponseEntity.ok(service.save(updatedTrash));
    }

    @PostMapping("")
    public ResponseEntity<TrashDto> create(@RequestBody @Valid TrashDto updatedTrash) {
        return ResponseEntity.ok(service.save(updatedTrash));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) throws NotFoundException {
        if (service.find(id) == null) {
            throw new NotFoundException(String.format("Cannot find trash with id:'%s'", id));
        } else {
            service.delete(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }
}