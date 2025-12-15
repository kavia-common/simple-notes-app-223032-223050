package com.example.notesbackend.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Provides compatibility redirects for OpenAPI documents.
 * - Redirects /openapi.json -> configured springdoc api docs path (default /v3/api-docs or overridden value).
 * This avoids 404/500 when external tooling expects /openapi.json.
 */
@RestController
@Tag(name = "OpenAPI", description = "OpenAPI documentation compatibility endpoints")
public class OpenApiDocsController {

    @Value("${springdoc.api-docs.path:/v3/api-docs}")
    private String apiDocsPath;

    // PUBLIC_INTERFACE
    @GetMapping("/openapi.json")
    @Operation(
            summary = "OpenAPI JSON (compat)",
            description = "Redirects to the configured OpenAPI JSON endpoint as defined by springdoc.api-docs.path"
    )
    public RedirectView openApiJsonRedirect() {
        // Ensure we redirect to the exact configured path.
        RedirectView rv = new RedirectView(apiDocsPath);
        rv.setHttp10Compatible(false);
        return rv;
    }
}
