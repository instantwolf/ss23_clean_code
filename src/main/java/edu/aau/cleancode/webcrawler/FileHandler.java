package edu.aau.cleancode.webcrawler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {
    private File file;
    private boolean overwrite;

    public FileHandler(String filePath, boolean overwrite){
        file = new File(filePath);
        this.overwrite = overwrite;
    }

    public FileWriter createFile() throws IOException {
        if (file.exists() && !overwrite) {
            throw new IOException("File already exists and overwrite flag is not set");
        }
        return new FileWriter(file);
    }

    public boolean isValidFilePath(String filePath){
        return false;
    }


}
