package klucsik.hovadobjam.material;

import klucsik.hovadobjam.trash.TrashDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class MaterialDto {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdatedAt;
    @NotNull
    private String name;
    private List<TrashDto> trashesDto;
}
