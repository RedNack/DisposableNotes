package workgroup.notes.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import workgroup.model.Note;
import workgroup.service.NoteService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
public class NotesControllers {
    @Autowired
    NoteService noteService;

    @Autowired
    Environment environment;

    ////////////////////////////////////////////////////
    //                main page                       //
    ////////////////////////////////////////////////////
    @GetMapping()
    public String mainPage() {
        return "mainPage";
    }

    ////////////////////////////////////////////////////
    //               работа с записками               //
    ////////////////////////////////////////////////////
    //******************Добавление*********************
    //Добавление новой записки. Когда нажимаем на `создать записку`
    @GetMapping("/addNote")
    public String addPage() {
        return "addNote";
    }

    //когда ввели все данные для записки и нажали ОК
    @PostMapping("/addNote")
    public String addNote(@ModelAttribute("note") Note note) {
        noteService.addNote(note);
        return "redirect:/address/" + note.getId();
    }

    //Выводим адресс с запиской
    @GetMapping("/address/{id}")
    public String getAddress(Model model, @PathVariable int id, HttpServletRequest request) {
        String URL = request.getRequestURL().toString();
        String URI = request.getRequestURI();
        URL = URL.replaceAll(URI, "");

        model.addAttribute("note", id);
        model.addAttribute("address", URL);
        return "address";
    }

    //*******************Чтение и удаление*****************************
    //Получает записку, оставленную пользователем. Возвращает страницу с вводом пароля
    @GetMapping("/id={xParam}")
    public String enterPassword(Model model, @PathVariable("xParam") int id) {
        model.addAttribute("note", id);
        return "checkPass";
    }

    //Проверяем пароль, и выводим записку
    @PostMapping("/id={xParam}")
    public String readNote(Model model, @PathVariable("xParam") int id, @ModelAttribute("password") String pass) {
        Note note = noteService.getById(id);
        if (!Objects.equals(pass, note.getPass())) {
            note = noteService.getErrorNote();
            model.addAttribute("note", note);
        } else {
            model.addAttribute("note", note);
            noteService.deleteNote(note);
        }
        return "readNote";
    }

    ////////////////////////////////////////////////////
    //                     TEST                       //
    ////////////////////////////////////////////////////
    //Тестовый контроллер для просмотра всех записок
    @GetMapping("/0xD15EA5E0xD15EA5E0xD15EA5E0xD15EA5E0xD15EA5E0xD15EA5E0xD15EA5E0xD15EA5E0xD15EA5E")
    public String secretPage(Model model) {
        model.addAttribute("noteList", noteService.getAll());
        return "secretPage";
    }
}
