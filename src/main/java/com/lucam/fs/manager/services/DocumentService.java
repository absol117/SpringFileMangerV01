package com.lucam.fs.manager.services;

import com.lucam.fs.manager.models.Document;
import com.lucam.fs.manager.repositories.DocumentRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;

    @SneakyThrows
    public Document save(Document document) {
        document.setId(null);

        if (document.getPath().isBlank())
            throw new RuntimeException("Path is blank");

        Path path = Path.of(document.getPath());
        document.setFile(path.toFile());

        if (Files.exists(path))
            throw new RuntimeException("File already exists exist");

        Files.createFile(path);
        return documentRepository.save(document);
    }

    public Document update(Document document) {
        if (document.getId() == null)
            throw new RuntimeException("Id is null");

        if (document.getPath().isBlank())
            throw new RuntimeException("Path is blank");

        return documentRepository.findById(document.getId())
                .map(x -> move(x, document.getPath()))
                .map(documentRepository::save)
                .orElseThrow(() -> new RuntimeException("Document not found"));
    }

    public List<Document> findAll() {
        return documentRepository.findAll();
    }

    public void deleteById(UUID id) {
        documentRepository.findById(id)
                .ifPresentOrElse(document -> {
                    try {
                        log.debug("Deleting document {} at {}", document.getId(), document.getPath());
                        Files.delete(Path.of(document.getPath()));
                        documentRepository.deleteById(document.getId());
                    } catch (IOException e) {
                        log.debug("Unable to delete document {} at {}", document.getId(), document.getPath());
                    }
                }, () -> log.warn("Document not found"));
    }

    public Document move(Document document, String newPath) {
        return documentRepository.findDocumentByPath(document.getPath())
                .map(d -> {
                    try {
                        log.debug("Attempting to move document {} from {} to {}", document.getId(), document.getPath(), newPath);
                        Files.move(Path.of(d.getPath()), Path.of(newPath), StandardCopyOption.REPLACE_EXISTING);

                        log.debug("Successfully moved document {} from {} to {}", document.getId(), document.getPath(), newPath);
                        d.setPath(newPath);
                        return documentRepository.save(d);
                    } catch (Exception e) {
                        log.debug("Unable to move document {} from {} to {}", document.getId(), document.getPath(), newPath);
                        return null;
                    }
                }).orElseThrow(() -> new RuntimeException("Document not found"));
    }

    public Optional<Document> findById(UUID id) {
        return documentRepository.findById(id);
    }

    public Document move(String id, String path) {
        return findById(UUID.fromString(id))
                .map(x -> move(x, path))
                .orElseThrow(() -> new RuntimeException("Document not found"));
    }
}
