package com.example.notesbackend.service;

import com.example.notesbackend.dto.NoteRequest;
import com.example.notesbackend.model.Note;
import com.example.notesbackend.repository.NoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service layer for managing notes.
 */
@Service
public class NoteService {

    private final NoteRepository repository;

    public NoteService(NoteRepository repository) {
        this.repository = repository;
    }

    // PUBLIC_INTERFACE
    @Transactional(readOnly = true)
    public List<Note> findAll() {
        /** Returns all notes. */
        return repository.findAll();
    }

    // PUBLIC_INTERFACE
    @Transactional(readOnly = true)
    public Note findById(Long id) {
        /** Returns a note by id or throws NotFoundException. */
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Note with id %d not found".formatted(id)));
    }

    // PUBLIC_INTERFACE
    @Transactional
    public Note create(NoteRequest request) {
        /** Creates a new note from request. */
        Note toSave = new Note(request.getTitle(), request.getContent());
        return repository.save(toSave);
    }

    // PUBLIC_INTERFACE
    @Transactional
    public Note update(Long id, NoteRequest request) {
        /** Updates an existing note. */
        Note existing = findById(id);
        existing.setTitle(request.getTitle());
        existing.setContent(request.getContent());
        return repository.save(existing);
    }

    // PUBLIC_INTERFACE
    @Transactional
    public void delete(Long id) {
        /** Deletes a note by id or throws NotFoundException. */
        if (!repository.existsById(id)) {
            throw new NotFoundException("Note with id %d not found".formatted(id));
        }
        repository.deleteById(id);
    }
}
