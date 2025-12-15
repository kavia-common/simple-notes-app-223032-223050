package com.example.notesbackend.config;

import com.example.notesbackend.dto.NoteRequest;
import com.example.notesbackend.model.Note;
import com.example.notesbackend.repository.NoteRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * General application configuration: CORS, OpenAPI metadata, and seed data.
 */
@Configuration
@OpenAPIDefinition(info = @Info(
        title = "Simple Notes API",
        version = "0.1.0",
        description = "REST API for managing notes",
        contact = @Contact(name = "NotesApp", email = "support@example.com")
))
public class AppConfig {

    // PUBLIC_INTERFACE
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        /** Enables permissive CORS for local development. */
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOriginPatterns("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD")
                        .allowedHeaders("*")
                        .allowCredentials(false);
            }
        };
    }

    // PUBLIC_INTERFACE
    @Bean
    public CommandLineRunner seedData(NoteRepository repository) {
        /** Seeds demo notes on startup for demonstration purposes. */
        return args -> {
            if (repository.count() == 0) {
                repository.save(new Note("Welcome", "This is your first note!"));
                repository.save(new Note("Tips", "Use POST /api/notes to create a new note."));
                repository.save(new Note("Docs", "Visit /docs for Swagger UI."));
            }
        };
    }
}
