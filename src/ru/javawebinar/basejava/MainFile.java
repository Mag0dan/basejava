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
        doi(dir);
    }

    public static void doi(File file) {
        System.out.println("--------Start Folder" + file.getName() + "-------");
        final File[] files = file.listFiles();
        if(files == null) {
            return;
        }
        for (File folderFile : files) {
            if(folderFile.isDirectory()) {
                doi(folderFile);
            } else {
                System.out.println(folderFile.getName());
            }
        }
        System.out.println("---------End Folder" + file.getName() + "---------");
    }
}
