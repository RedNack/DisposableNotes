package workgroup.notes.controllers.files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import workgroup.model.Note;
import workgroup.service.NoteService;
import workgroup.storage.StorageService;

import java.io.IOException;
import java.util.Objects;

@Controller
public class FileControllers {

    @Autowired
    NoteService noteService;
    @Autowired
    StorageService storageService;

    @Autowired
    FileControllers(StorageService storageService) {
        this.storageService = storageService;
    }


    @GetMapping("/loadAllFiles")
    public String createFile(Model model) throws IOException {
        model.addAttribute("fileList", storageService.loadAll());
        return "test/allFiles";
    }

    //Проверяем пароль, и скачиваем записку
    @PostMapping("/save/id={xParam}")
    public String saveNote(Model model, @PathVariable("xParam") int id, @ModelAttribute("password") String pass) {
        Note note = noteService.getById(id);

        if (!Objects.equals(pass, note.getPass())) {
            note = noteService.getErrorNote();
            model.addAttribute("note", note);
        } else {
            storageService.createFileFromNote(note);
            model.addAttribute("note", note);
            return "redirect:/save/filename/" + note.getId();
        }
        return "readNote";
    }

    //Для скачивания файла с запиской
    @GetMapping("save/filename/{filename}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename + ".txt");
        ResponseEntity<Resource> entity = ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
        noteService.deleteNote(noteService.getById(Integer.valueOf(filename)));
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            storageService.deleteFile(filename + ".txt");
        });
        thread.start();
        return entity;
    }
}
