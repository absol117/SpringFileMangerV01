package test;

import com.scai.gestorefile_v03.models.Document;
import com.scai.gestorefile_v03.services.DocumentService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    private final DocumentService documentService = new DocumentService();

    @Test
    @SneakyThrows
    void shouldDeleteFile() {
        // GIVEN
        Path prova = Files.createTempFile("prova", ".txt");

        // WHEN - THEN
        assertTrue(documentService.deleteByPath(prova));

        assertFalse(prova.toFile().exists());

        Files.deleteIfExists(prova);
    }

    @Test
    @SneakyThrows
    void shouldDeleteDocument() {
        // GIVEN
        Path prova = Files.createTempFile("prova", ".txt");

        Document document = new Document(1, "tag1", prova);

        // WHEN - THEN
        assertTrue(documentService.delete(document));

        assertFalse(prova.toFile().exists());

        Files.deleteIfExists(prova);
    }

    @Test
    @SneakyThrows
    void shouldAddFile() {
        // GIVEN
        Path prova = Files.createTempFile("prova", ".txt");
        Files.delete(prova);

        assertFalse(prova.toFile().exists());

        // WHEN
        Document test = documentService.addFile(0, "test", prova);


        // THEN
        assertEquals(prova, test.getPath());
        assertTrue(test.getFile().exists());

        Files.deleteIfExists(prova);
    }


}
