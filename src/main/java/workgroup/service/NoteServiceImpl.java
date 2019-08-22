package workgroup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workgroup.model.Note;
import workgroup.repository.NoteRepository;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public void addNote(Note note) {
        noteRepository.save(note);
    }

    @Override
    public void deleteNote(Note note) {
        noteRepository.delete(note);
    }

    @Override
    public Note getById(int id) {
        return noteRepository.findById(id).orElse(getErrorNote());
    }

    @Override
    public List<Note> getAll() {
        return noteRepository.findAll();
    }

    @Override
    public Note getErrorNote() {
        Note note = new Note();
        note.setName("Ошибка !");
        note.setText("Такой страницы не существует или неверный пароль.");
        note.setId(0);
        return note;
    }
}
