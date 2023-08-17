package com.lucam.fs.manager.controllers;

import com.lucam.fs.manager.models.Document;
import com.lucam.fs.manager.services.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentService documentServiceDatabase;

    @GetMapping("/all")
    public List<Document> findAllDocuments() {
        return documentServiceDatabase.findAll();
    }


    @GetMapping("/{id}")
    public Optional<Document> findDocumentById(@PathVariable String id) {
        return documentServiceDatabase.findById(UUID.fromString(id));
    }

    @PostMapping("/create")
    public Document saveDocument(@RequestBody Document document) {
        return documentServiceDatabase.save(document);
    }

    @PutMapping("/update")
    public Document updateDocument(@RequestBody Document document) {
        return documentServiceDatabase.update(document);
    }

    @PostMapping("/{id}/move")
    public Document moveDocument(@PathVariable String id, @RequestBody String path) {
        return documentServiceDatabase.move(id, path);
    }

    @DeleteMapping("/{id}")
    public void deleteDocumentById(@PathVariable String id) {
        documentServiceDatabase.deleteById(UUID.fromString(id));
    }

}
