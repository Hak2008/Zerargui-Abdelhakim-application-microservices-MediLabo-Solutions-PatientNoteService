package com.medilabosolutions.PatientNoteService.repository;

import com.medilabosolutions.PatientNoteService.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface NoteRepository extends MongoRepository<Note, String> {
    List<Note> findByPatId(int patId);
    void deleteAllByPatId(int patId);
}
