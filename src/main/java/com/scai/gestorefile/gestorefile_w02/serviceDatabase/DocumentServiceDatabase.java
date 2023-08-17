package com.scai.gestorefile.gestorefile_w02.serviceDatabase;

import com.scai.gestorefile.gestorefile_w02.model.Document;
import com.scai.gestorefile.gestorefile_w02.repository.DocumentRepository;
import com.scai.gestorefile.gestorefile_w02.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentServiceDatabase {

    private final DocumentRepository documentRepository;
    private final DocumentService documentService;

    @Autowired
    public DocumentServiceDatabase(DocumentRepository documentRepository, DocumentService documentService) {
        this.documentRepository = documentRepository;
        this.documentService = documentService;

    }

    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    public Optional<Document> getDocumentById(int id) {
        return documentRepository.findById(id);
    }

    public Document createDocument(String tag, String path) {
        Document document = new Document(tag, path);
        return documentRepository.save(document);
    }

    public boolean deleteDocumentById(int id) {
        if (!getDocumentById(id).isEmpty()) {
            documentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Document update(int id, String tag, String context) {
        Optional<Document> documentById = getDocumentById(id);
        Document document = documentById.get();
        document.setTag(tag);
        documentService.write(document,context);
        return documentRepository.save(document);

    }

}
