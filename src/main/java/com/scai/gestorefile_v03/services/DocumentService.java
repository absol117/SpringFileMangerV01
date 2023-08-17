package com.scai.gestorefile_v03.services;

import com.scai.gestorefile_v03.models.Document;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class DocumentService {

    public Document document;

    public DocumentService(Document document) {
        this.document = document;
    }

    public DocumentService() {
    }

    public String read() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(document.getFile()));
            String ris;
            while ((ris = bufferedReader.readLine()) != null) {
                stringBuilder.append(ris).append("\n");
            }
            return stringBuilder.toString();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean write(String content, String path, boolean overwrite) {
        try {
            if (overwrite) {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path, true));
                bufferedWriter.write(content);
                bufferedWriter.flush();
                return true;
            } else {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path, false));
                bufferedWriter.write(content);
                bufferedWriter.flush();
                return false;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public Document addFile(int id, String tag, Path path) {
        File file = new File(path.toUri());
        try {
            if(file.createNewFile()) {
                Document document1 = new Document(id, tag, path);
                document1.setFile(file);
                System.out.println("File creato con successo: " + file.getAbsolutePath());
                return document1;
            } else {
                System.out.println("Il file esiste già e verrà sovrascritto: " + file.getAbsolutePath());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public boolean deleteByPath(Path path) {
        File file = new File(path.toUri());
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    public boolean delete(Document document) {
        if (document.getFile().exists()) {
            return document.getFile().delete();
        }
        return false;
    }


    public boolean move(Document doc, Path path) {
        if (doc.getFile().exists()) {
            try {
                Files.move(doc.getFile().toPath(), path, StandardCopyOption.REPLACE_EXISTING);
                return true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

}
