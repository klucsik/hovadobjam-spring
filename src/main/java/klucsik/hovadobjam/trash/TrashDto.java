package klucsik.hovadobjam.trash;

import klucsik.hovadobjam.material.MaterialDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Setter
@Getter
public class TrashDto {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdatedAt;
    @NotBlank(message = "Name is mandatory")
    private String name;
    //put here @valid if want object graph validation
    private MaterialDto materialDto;
}
