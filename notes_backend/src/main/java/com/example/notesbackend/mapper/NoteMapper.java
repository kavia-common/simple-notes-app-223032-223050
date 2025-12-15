package com.example.notesbackend.mapper;

import com.example.notesbackend.dto.NoteRequest;
import com.example.notesbackend.dto.NoteResponse;
import com.example.notesbackend.model.Note;

/**
 * Mapper to convert between Note entity and DTOs.
 */
public final class NoteMapper {

    private NoteMapper() {}

    // PUBLIC_INTERFACE
    public static Note toEntity(NoteRequest req) {
        /** Converts a NoteRequest DTO to a Note entity (without id). */
        return new Note(req.getTitle(), req.getContent());
    }

    // PUBLIC_INTERFACE
    public static void updateEntity(Note note, NoteRequest req) {
        /** Applies fields from NoteRequest to an existing Note entity. */
        note.setTitle(req.getTitle());
        note.setContent(req.getContent());
    }

    // PUBLIC_INTERFACE
    public static NoteResponse toResponse(Note note) {
        /** Converts a Note entity to NoteResponse DTO. */
        return new NoteResponse()
                .setId(note.getId())
                .setTitle(note.getTitle())
                .setContent(note.getContent())
                .setCreatedAt(note.getCreatedAt())
                .setUpdatedAt(note.getUpdatedAt());
    }
}
