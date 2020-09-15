package klucsik.hovadobjam.material;

import klucsik.hovadobjam.trash.Trash;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime lastUpdatedAt;

    private String name;

    @OneToMany(mappedBy = "material")
    private List<Trash> trashes;

    public Material(String name){
        this.name=name;
    }

    @Override
    public String toString() {
        return String.format("Material[id=%d, name='%s']", id, name);
    }

    @PreRemove
    public void preRemove(){

        for(Trash trash : this.trashes){
            trash.setMaterial(null);
        }

    }
}