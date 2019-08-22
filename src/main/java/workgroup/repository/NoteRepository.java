package workgroup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import workgroup.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {
}

