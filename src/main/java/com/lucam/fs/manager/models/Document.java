package com.lucam.fs.manager.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "documents")
public class Document {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(unique = true, nullable = false)
    private String path;

    @ElementCollection // creates a table for the list
    private List<String> tags = new ArrayList<>();

    @Transient // not persisted
    private File file;

}
