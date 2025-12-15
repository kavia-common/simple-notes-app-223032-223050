package com.example.notesbackend.web;

import com.example.notesbackend.dto.NoteRequest;
import com.example.notesbackend.dto.NoteResponse;
import com.example.notesbackend.mapper.NoteMapper;
import com.example.notesbackend.model.Note;
import com.example.notesbackend.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller exposing CRUD endpoints for Notes.
 */
@RestController
@RequestMapping("/api/notes")
@Tag(name = "Notes", description = "CRUD operations for notes")
@CrossOrigin(origins = "*") // Permissive CORS for local dev
public class NoteController {

    private final NoteService service;

    public NoteController(NoteService service) {
        this.service = service;
    }

    // PUBLIC_INTERFACE
    @GetMapping
    @Operation(
            summary = "List notes",
            description = "Returns all notes",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of notes",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = NoteResponse.class))))
            }
    )
    public List<NoteResponse> listNotes() {
        /** Returns all notes as NoteResponse list. */
        return service.findAll().stream().map(NoteMapper::toResponse).toList();
    }

    // PUBLIC_INTERFACE
    @GetMapping("/{id}")
    @Operation(
            summary = "Get a note",
            description = "Returns a single note by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Note found",
                            content = @Content(schema = @Schema(implementation = NoteResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Note not found")
            }
    )
    public NoteResponse getNote(@PathVariable Long id) {
        /** Returns a note by id. */
        Note note = service.findById(id);
        return NoteMapper.toResponse(note);
    }

    // PUBLIC_INTERFACE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create a note",
            description = "Creates a new note with title and content",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Note created",
                            content = @Content(schema = @Schema(implementation = NoteResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Validation error")
            }
    )
    public NoteResponse createNote(@Valid @RequestBody NoteRequest request) {
        /** Creates a new note from NoteRequest. */
        Note created = service.create(request);
        return NoteMapper.toResponse(created);
    }

    // PUBLIC_INTERFACE
    @PutMapping("/{id}")
    @Operation(
            summary = "Update a note",
            description = "Updates title and content of an existing note",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Note updated",
                            content = @Content(schema = @Schema(implementation = NoteResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Validation error"),
                    @ApiResponse(responseCode = "404", description = "Note not found")
            }
    )
    public NoteResponse updateNote(@PathVariable Long id, @Valid @RequestBody NoteRequest request) {
        /** Updates an existing note by id. */
        Note updated = service.update(id, request);
        return NoteMapper.toResponse(updated);
    }

    // PUBLIC_INTERFACE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Delete a note",
            description = "Deletes a note by id",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Note deleted"),
                    @ApiResponse(responseCode = "404", description = "Note not found")
            }
    )
    public void deleteNote(@PathVariable Long id) {
        /** Deletes a note by id. */
        service.delete(id);
    }
}
