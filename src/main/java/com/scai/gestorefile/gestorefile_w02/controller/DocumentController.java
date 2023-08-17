package com.scai.gestorefile.gestorefile_w02.controller;

import com.scai.gestorefile.gestorefile_w02.model.Document;
import com.scai.gestorefile.gestorefile_w02.serviceDatabase.DocumentServiceDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentServiceDatabase documentServiceDatabase;

    @Autowired
    public DocumentController(DocumentServiceDatabase documentServiceDatabase) {
        this.documentServiceDatabase = documentServiceDatabase;
    }

    @GetMapping("/getAll")
    public List<Document> getAllDocuments() {
        return documentServiceDatabase.getAllDocuments();
    }


    @GetMapping("/id/{id}")
    public Optional<Document> getDocumentById(@PathVariable int id) {
        return documentServiceDatabase.getDocumentById(id);
    }



    // non va se metto il PostMapping
    @GetMapping("/save")
    public Document saveDocument() {
        String s = "C:/Users/lucam/OneDrive/Desktop/GestoreFile/GestoreFile_W02/src/main/resources/database/prova.txt";
        String tag1 = "tag1";
        return documentServiceDatabase.createDocument(tag1, s);
    }



    //Devo finire


}
