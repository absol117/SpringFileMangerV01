package test;


import com.scai.gestorefile_v03.models.Document;
import com.scai.gestorefile_v03.services.DocumentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.nio.file.Path;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class MainTest {

    @Test
    public void mathTest() {
        int result = Math.addExact(4,4);
        assertEquals(8, result);
    }
    @ParameterizedTest
    @MethodSource("addFileParameters")
    public void addFileTest(int id, String tag, Path path) {
        DocumentService documentService = new DocumentService();
        Document document = documentService.addFile(id, tag, path);
        assertTrue(document.getFile().exists());

    }

    public static Stream<Arguments> addFileParameters() {
        Path path = Path.of("C:/Users/lucam/OneDrive/Desktop/GestoreFile/GestoreFile_V03/src/main/resources/database/prova2.txt");
        return Stream.of(Arguments.of(1,"tag1",path));

    }



}
