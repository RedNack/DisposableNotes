package workgroup.storage;

import org.springframework.core.io.Resource;
import workgroup.model.Note;

import java.io.IOException;
import java.util.List;

public interface StorageService {
    List<String> loadAll() throws IOException;

    void createFileFromNote(Note note);

    Resource loadAsResource(String filename);

    void deleteFile(String filename);

    void init();
}
