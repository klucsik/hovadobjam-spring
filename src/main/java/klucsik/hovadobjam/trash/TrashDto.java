package klucsik.hovadobjam.trash;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class TrashDto {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdatedAt;
    private String name;
}
