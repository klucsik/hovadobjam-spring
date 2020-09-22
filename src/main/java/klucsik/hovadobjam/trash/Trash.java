package klucsik.hovadobjam.trash;

import klucsik.hovadobjam.material.Material;
import klucsik.hovadobjam.trashalias.TrashAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Trash {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime lastUpdatedAt;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @ManyToOne
    private Material material;

    @OneToMany(mappedBy = "trash")
    private List<TrashAlias> trashAliases;

    public Trash(String name){
        this.name=name;
    }

    public Trash(Material material, String name){
        this.name=name;
        this.material=material;
    }

    @Override
    public String toString() {
        return String.format("Trash[id=%d, name='%s', material='%s']", id, name, material);
    }
}
