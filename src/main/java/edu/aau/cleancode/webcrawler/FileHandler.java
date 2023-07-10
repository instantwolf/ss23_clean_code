package edu.aau.cleancode.webcrawler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {
    private File file;

    private String filePath;

    private boolean appendContent;

    public FileHandler(String filePath, boolean appendContent){
        this.file = new File(filePath);
        this.filePath = filePath;
        this.appendContent = appendContent;
    }

    private FileWriter openFile() throws IOException {

        if (!this.file.exists()) {
            this.file.getParentFile().mkdirs();
            this.file.createNewFile();
        }

        // Open the file in append mode or overwrite mode based on the appendMode parameter
        FileWriter fileWriter = new FileWriter(file, this.appendContent);
        return fileWriter;
    }

    public void write(String content) throws IOException{
        FileWriter writer = openFile();
        writer.write(content);
        writer.close();
    }



    public boolean isValidFilePath(String filePath){
        return false;
    }


}
