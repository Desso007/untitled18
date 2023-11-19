package edu;

import java.io.*;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

public class task4 {


    public static void main(String[] args) {
        try (PrintWriter writer = new PrintWriter(
                new OutputStreamWriter(
                        new BufferedOutputStream(
                                new CheckedOutputStream(
                                        new FileOutputStream("output.txt"),
                                        new CRC32()
                                )
                        ),
                        "UTF-8"
                )
        )) {
            writer.println("Programming is learned by writing programs. ― Brian Kernighan");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}