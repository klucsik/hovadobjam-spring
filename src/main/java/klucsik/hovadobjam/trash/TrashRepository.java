package klucsik.hovadobjam.trash;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrashRepository extends JpaRepository<Trash,Long> {
}
