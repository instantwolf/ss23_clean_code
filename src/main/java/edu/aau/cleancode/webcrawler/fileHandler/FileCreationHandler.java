package edu.aau.cleancode.webcrawler.fileHandler;

import java.io.File;

/**
 *
 */
public class FileCreationHandler {

    File file;
    String fileName;


    public FileCreationHandler(String fileName, boolean openIfExists) {
        this.fileName = fileName;
    }


}
