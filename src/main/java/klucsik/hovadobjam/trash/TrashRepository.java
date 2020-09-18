package klucsik.hovadobjam.trash;

import klucsik.hovadobjam.customs.RepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrashRepository extends JpaRepository<Trash,Long> , RepositoryCustom<Trash> {
    void refresh(Trash trash);
}
