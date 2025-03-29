package com.project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Utility class for handling file encryption and decryption operations.
 * <p>
 * This class provides a static method to encrypt or decrypt a file
 * using XOR-based encryption with a password.
 */
public class FileProcessing {

    /**
     * Encrypts or decrypts the specified file using the provided password.
     * <p>
     * The encryption algorithm uses XOR operation between the file's bytes and
     * the password bytes. The same method is used for both encryption and decryption.
     *
     * @param file     The file to be encrypted or decrypted.
     * @param password The password used for XOR encryption/decryption.
     */
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
