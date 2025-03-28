package com.project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileProcessing {

    protected static void fileEncryptDecrypt(File file, String password) {
        try {
            byte[] passwordBytes = password.getBytes();

            FileInputStream fis = new FileInputStream(file);
            byte[] fileData = new byte[fis.available()];
            fis.read(fileData);
            fis.close();

            for (int i = 0; i < fileData.length; i++) {
                fileData[i] = (byte) (fileData[i] ^ passwordBytes[i % passwordBytes.length]);
            }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(fileData);
            System.out.println("Done!!!");
            fos.close();
        } catch (Exception e) {
            System.out.println("Sorry, unable to process the file");;
        }
    }
}
