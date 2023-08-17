package com.scai.gestorefile.gestorefile_w02.main;

import com.scai.gestorefile.gestorefile_w02.model.Document;
import com.scai.gestorefile.gestorefile_w02.service.DocumentService;

import java.net.URI;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws URISyntaxException {


        String path = "C:/Users/lucam/OneDrive/Desktop/GestoreFile/GestoreFile_W02/src/main/resources/database/prova01.txt";
        DocumentService documentService = new DocumentService();
        String pathDestination = "C:/Users/lucam/OneDrive/Desktop/GestoreFile/GestoreFile_W02/src/main/resources/static/prova03.txt";
        Document document = documentService.addDocument(1, "tag1", pathDestination);


        documentService.delete(document.getFile().getPath());
        //System.out.println(documentService.move(document, pathDestination));
        /*


        documentService.write(document,"alloraVediamo!!!");
        System.out.println(documentService.read(document));
        documentService.delete(path);

        String s = "C:/Users/lucam/OneDrive/Desktop/GestoreFile/GestoreFile_W02/src/main/resources/database";
        URI uri = new URI(s);
        System.out.println(uri.getPath());
        */
    }
}
