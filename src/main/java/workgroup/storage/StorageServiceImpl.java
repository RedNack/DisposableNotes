package workgroup.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import workgroup.model.Note;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StorageServiceImpl implements StorageService {

    private Path rootLocation;

    @Autowired
    public StorageServiceImpl(StorageConfig storageConfig) {
        rootLocation = Paths.get(storageConfig.getLocation());
    }

    @Override
    public List<String> loadAll() throws IOException {
        Stream<Path> pathStream = Files.walk(rootLocation);
        ArrayList<String> arrayList = (ArrayList) pathStream.map(path -> {
            path = path.getFileName();
            return path;
        }).collect(Collectors.toList());
        return arrayList;
    }

    @Override
    public void createFileFromNote(Note note) {
        try {

            Path file = Paths.get(rootLocation.toString() + "/" + note.getId() + ".txt");
            Files.createFile(file);
            String text = note.getName() + "\n" + note.getText();
            Files.write(file, text.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Resource loadAsResource(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteFile(String filename) {
        try {

            Files.walk(rootLocation).filter(path -> !path.equals(rootLocation))
                    .map(path -> {
                        if (path.getFileName().toString().equals(filename)) {
                            try {
                                Files.deleteIfExists(path);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        return path;
                    }).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
