package klucsik.hovadobjam.user;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserDto {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdatedAt;
    private String username;
    private String email;

}
