package com.scai.gestorefile_v03.models;

import java.io.File;
import java.nio.file.Path;

public class Document {
    private int id;
    private String tag;
    private Path path;
    private File file;

    public Document(int id, String tag, Path path) {
        this.id = id;
        this.tag = tag;
        this.path = path;
        this.file = new File(path.toUri());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", tag='" + tag + '\'' +
                ", path=" + path +
                ", file=" + file.getName() +
                '}';
    }
}
