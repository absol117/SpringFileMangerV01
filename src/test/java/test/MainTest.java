package test;


import com.scai.gestorefile_v03.models.Document;
import com.scai.gestorefile_v03.services.DocumentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.nio.file.Path;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class MainTest {

    private DocumentService documentService = new DocumentService();

    /*
    @Test
    public void mathTest() {
        int result = Math.addExact(4,4);
        assertEquals(8, result);
    }
    */


    @ParameterizedTest
    @MethodSource("addFileParameters")
    public void addFileTest(int id, String tag, Path path) {
        Document document = documentService.addFile(id, tag, path);
        assertTrue(document.getFile().exists());

    }

    public static Stream<Arguments> addFileParameters() {
        Path path = Path.of("C:/Users/lucam/OneDrive/Desktop/GestoreFile/GestoreFile_V03/src/main/resources/database/prova02.txt");
        return Stream.of(Arguments.of(1,"tag1",path));

    }

    @Test
    public void addFileTest2() {
        int id = 1;
        String tag = "tag1";
        Path path = Path.of("C:/Users/lucam/OneDrive/Desktop/GestoreFile/GestoreFile_V03/src/main/resources/database/prova02.txt");

        Document document = documentService.addFile(id,tag,path);
        assertTrue(document.getFile().exists());
        System.out.println(document.getPath());
    }




    @Test
    public void addFileMkdirTest() {
        Path folderPath = Path.of("C:/Users/lucam/OneDrive/Desktop/GestoreFile/GestoreFile_V03/src/main/resources/database");
        String nameFile = "prova04.txt";
        assertTrue(documentService.addFileMkdir(folderPath,nameFile));
    }




    @ParameterizedTest
    @MethodSource("deleteByPathParameters")
    public void deleteByPathTest(Path path) {
        documentService.deleteByPath(path);
        assertTrue(documentService.deleteByPath(path));
    }
    public static Stream<Arguments> deleteByPathParameters() {
        Path path = Path.of("C:/Users/lucam/OneDrive/Desktop/GestoreFile/GestoreFile_V03/src/main/resources/database/prova02.txt");
        return Stream.of(Arguments.of(path));
    }

    @Test
    public void deleteByPathTest2() {
        Path path = Path.of("C:/Users/lucam/OneDrive/Desktop/GestoreFile/GestoreFile_V03/src/main/resources/database/prova02.txt");
        documentService.deleteByPath(path);
        assertTrue(documentService.deleteByPath(path));
    }







    @ParameterizedTest
    @MethodSource("deleteParameters")
    public void deleteTest(Document document) {
        documentService.delete(document);
        assertTrue(documentService.delete(document));

    }
    public static Stream<Arguments> deleteParameters() {
        Path path = Path.of("C:/Users/lucam/OneDrive/Desktop/GestoreFile/GestoreFile_V03/src/main/resources/database/prova02.txt");
        return Stream.of(Arguments.of(new Document(1,"tag1", path)));
    }


    @Test
    public void deleteTest2() {
        Path path = Path.of("C:/Users/lucam/OneDrive/Desktop/GestoreFile/GestoreFile_V03/src/main/resources/database/prova02.txt");
        int id = 2;
        String tag = "tag2";
        Document document = new Document(id,tag,path);
        documentService.delete(document);
        assertTrue(documentService.delete(document));
    }




    @ParameterizedTest
    @MethodSource("moveParameters")
    public void moveTest(Document doc, Path path) {
        documentService.move(doc, path);
        assertTrue(documentService.move(doc, path));
    }

    public static Stream<Arguments> moveParameters() {
        Path pathSource = Path.of("C:/Users/lucam/OneDrive/Desktop/GestoreFile/GestoreFile_V03/src/main/resources/database/provaMove01.txt");
        Path pathDestination = Path.of("C:/Users/lucam/OneDrive/Desktop/GestoreFile/GestoreFile_V03/src/main/resources/databaseMove/provaMove02.txt");
        return Stream.of(Arguments.of(new Document(2, "tag2", pathSource), pathDestination));

    }

    @Test
    public void moveTest2() {
        Path pathSource = Path.of("C:/Users/lucam/OneDrive/Desktop/GestoreFile/GestoreFile_V03/src/main/resources/database/prova02.txt");
        int id = 2;
        String tag = "tag2";
        Document document = new Document(id,tag,pathSource);
        Path pathDestination = Path.of("C:/Users/lucam/OneDrive/Desktop/GestoreFile/GestoreFile_V03/src/main/resources/databaseMove/provaMove02.txt");

        assertTrue(documentService.move(document, pathDestination));
    }




}
