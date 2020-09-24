package klucsik.hovadobjam.user;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

//https://auth0.com/blog/implementing-jwt-authentication-on-spring-boot/#Enabling-User-Registration-on-Spring-Boot-APIs
//https://medium.com/@kamer.dev/spring-boot-user-registration-and-login-43a33ea19745

@Entity//(name = "Users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime lastUpdatedAt;

    @Column(unique = true)
    private String username;
    private String email;
    private String password;

    @Builder.Default
    private UserRole userRole = UserRole.USER;


    public User(String username) {
        this.username = username;
    }

    public <T> User(String username, String password, List<T> emptyList) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format("User[id=%d, username='%s', email='%s']", id, username, email);
    }


    //userDetails, we currently not use the extra functionality this class provides
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
