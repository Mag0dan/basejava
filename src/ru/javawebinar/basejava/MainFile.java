package ru.javawebinar.basejava;

import java.io.File;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) {
        File file = new File(".\\.gitignore");
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }
        File dir = new File("./src");
        printDeepDirectory(dir, "");
    }

    public static void printDeepDirectory(File file, String indent) {
        System.out.println(indent + "Directory: " + file.getName());
        indent = indent + "\t";
        final File[] files = file.listFiles();
        if (files == null) {
            return;
        }
        for (File folderFile : files) {
            if (!folderFile.isDirectory()) {
                System.out.println(indent + "File: " + folderFile.getName());
            }
        }
        for (File folderFile : files) {
            if (folderFile.isDirectory()) {
                printDeepDirectory(folderFile, indent);
            }
        }
    }
}
