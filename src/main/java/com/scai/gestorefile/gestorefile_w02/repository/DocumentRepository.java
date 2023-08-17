package com.scai.gestorefile.gestorefile_w02.repository;

import com.scai.gestorefile.gestorefile_w02.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Integer> {

}
