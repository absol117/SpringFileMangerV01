package com.lucam.fs.manager.repositories;

import com.lucam.fs.manager.models.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DocumentRepository extends JpaRepository<Document, UUID> {
    Optional<Document> findDocumentByPath(String path);
}
