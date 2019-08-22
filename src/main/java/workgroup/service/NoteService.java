package workgroup.service;

import workgroup.model.Note;

import java.util.List;

public interface NoteService {
    void addNote(Note note);

    void deleteNote(Note note);

    Note getById(int id);

    List<Note> getAll();

    Note getErrorNote();
}
