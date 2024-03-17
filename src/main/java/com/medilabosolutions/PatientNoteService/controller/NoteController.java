package com.medilabosolutions.PatientNoteService.controller;

import com.medilabosolutions.PatientNoteService.model.Note;
import com.medilabosolutions.PatientNoteService.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;

    @GetMapping("/note/list")
    public ResponseEntity<List<Note>> getAllNotes() {
        List<Note> notes = noteRepository.findAll();
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @GetMapping("/note/details/{patId}")
    public ResponseEntity<List<Note>> getNotesByPatId(@PathVariable int patId) {
        List<Note> notes = noteRepository.findByPatId(patId);
        if (notes.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Notes not found for patient: " + patId);
        }
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @PostMapping("/note/validate")
    public ResponseEntity<Note> addNote(@RequestBody Note note) {
        Note savedNote = noteRepository.save(note);
        return new ResponseEntity<>(savedNote, HttpStatus.CREATED);
    }

    @PutMapping("/note/update/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable String id, @RequestBody Note noteDetails) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not found with id: " + id));

        // Update note details if provided
        note.setPatId(noteDetails.getPatId());
        note.setPatient(noteDetails.getPatient());
        note.setNote(noteDetails.getNote());

        Note updatedNote = noteRepository.save(note);
        return new ResponseEntity<>(updatedNote, HttpStatus.OK);
    }

    @DeleteMapping("/note/delete/{id}")
    public ResponseEntity<String> deleteNote(@PathVariable String id) {
        if (!noteRepository.existsById(id)) {
            return new ResponseEntity<>("Note not found with id: " + id, HttpStatus.NOT_FOUND);
        }
        noteRepository.deleteById(id);
        return new ResponseEntity<>("Note deleted successfully", HttpStatus.OK);
    }
}
