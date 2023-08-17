package com.lucam.fs.manager.services;

import com.lucam.fs.manager.models.Document;
import com.lucam.fs.manager.repositories.DocumentRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext
@TestMethodOrder(OrderAnnotation.class)
class DocumentServiceTest {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private DocumentRepository documentRepository;

    private static Path tempDirectory;

    @BeforeAll
    @SneakyThrows
    static void beforeAll() {
        tempDirectory = Files.createTempDirectory("test");
    }

    @Test
    @Order(1)
    void shouldCreateDocument() {
        Document document = Document.builder()
                .tags(List.of("test", "file", "document", "txt"))
                .path(tempDirectory.resolve("test.txt").toString())
                .build();

        Document result = documentService.save(document);

        assertEquals(document.getPath(), result.getPath());
        assertNotNull(result.getId());

        documentRepository.deleteById(result.getId());
        assertDoesNotThrow(() -> Files.delete(Path.of(result.getPath())));
    }

    @Test
    @Order(2)
    void shouldDeleteDocument() {
        Document document = Document.builder()
                .tags(List.of("test", "file", "document", "txt"))
                .path(tempDirectory.resolve("test.txt").toString())
                .build();

        document = documentService.save(document);

        documentService.deleteById(document.getId());

        assertTrue(Files.notExists(Path.of(document.getPath())));
        assertTrue(documentRepository.findById(document.getId()).isEmpty());
    }

    @Test
    @Order(3)
    void shouldMoveDocument() {
        Document document = Document.builder()
                .tags(List.of("test", "file", "document", "txt"))
                .path(tempDirectory.resolve("test.txt").toString())
                .build();

        document = documentService.save(document);

        Path path2 = tempDirectory.resolve("test2.txt");
        Document result = documentService.move(document, path2.toString());

        assertTrue(result.getPath().endsWith("test2.txt"));
    }

}