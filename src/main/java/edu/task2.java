package edu;

import java.io.IOException;
import java.nio.file.*;

public class task2 {
    public static void cloneFile(Path path) {
        String fileName = path.getFileName().toString();
        String baseName = fileName.substring(0, fileName.lastIndexOf('.'));
        String extension = fileName.substring(fileName.lastIndexOf('.'));

        int copyNumber = 1;
        Path copyPath = path.resolveSibling(baseName + " - копия" + extension);

        while (Files.exists(copyPath)) {
            copyNumber++;
            copyPath = path.resolveSibling(baseName + " - копия (" + copyNumber + ")" + extension);
        }

        try {
            Files.copy(path, copyPath);
            System.out.println("File cloned successfully: " + copyPath);
        } catch (IOException e) {
            System.out.println("Failed to clone file: " + e.getMessage());
        }
    }

    // Test the FileCloner class
    public static void main(String[] args) {
        Path filePath = Paths.get("path/to/file.txt");
        cloneFile(filePath);
    }
}