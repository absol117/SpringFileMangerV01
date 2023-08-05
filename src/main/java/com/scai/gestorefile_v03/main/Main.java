package com.scai.gestorefile_v03.main;

import com.scai.gestorefile_v03.models.Document;
import com.scai.gestorefile_v03.services.DocumentService;

import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        /*
        Path path = Path.of("C:/Users/lucam/OneDrive/Desktop/GestoreFile/GestoreFile_V03/src/main/resources/database/prova.txt");
        Document document = new Document(1, "tag1", path);
        System.out.println(document.getFile().exists());

        DocumentService documentService = new DocumentService(document);
        System.out.println(documentService.read());

        System.out.println(documentService.write("alloravediamo", path.toString(), false));
        System.out.println(documentService.read());

        Path path2 = Path.of("C:/Users/lucam/OneDrive/Desktop/GestoreFile/GestoreFile_V03/src/main/resources/database/prova2.txt");
        System.out.println(documentService.addFile(path2));
        */
        Path path = Path.of("C:/Users/lucam/OneDrive/Desktop/GestoreFile/GestoreFile_V03/src/main/resources/database/prova2.txt");
        Document document = new Document(1, "tag1", path);
        DocumentService documentService = new DocumentService(document);
        //System.out.println(documentService.delete(document));
        System.out.println(documentService.addFile(4,"tag4", path));

      //  Path path2 = Path.of("C:/Users/lucam/OneDrive/Desktop/GestoreFile/GestoreFile_V03/src/main/resources/static/prova2.txt");
      //  System.out.println(documentService.move(document, path2));

    }
}
