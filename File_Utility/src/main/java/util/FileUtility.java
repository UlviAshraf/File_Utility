package util;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.*;


/**
 *
 * @author UlviAshraf
 */
public class FileUtility {

    public static void writeIntoFile(String fileName, String text) throws Exception {
        try (FileWriter fw = new FileWriter(fileName);
                BufferedWriter bw = new BufferedWriter(fw);) {
            bw.write(text);
        }
    }

    public static void writeIntoFileByte(String fileName, String text) throws Exception {
        try (FileOutputStream fos = new FileOutputStream(fileName);) {
            byte[] data = text.getBytes();
            fos.write(data);
        }
    }

    public static void writeIntoFileNio(String fileName, String text) throws Exception {
        Path paths = Paths.get(fileName);
        byte[] data = text.getBytes();
        Files.write(paths, data);
    }

    public static void writeObject(String fileName, Serializable object) throws Exception {
        try (FileOutputStream fos = new FileOutputStream(fileName);
                ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeUnshared(object);

        }
    }

    public static String readFile(String fileName) throws Exception {
        try (FileInputStream fis = new FileInputStream(fileName);
                InputStreamReader r = new InputStreamReader(fis);
                BufferedReader reader = new BufferedReader(r);) {
            String line;
            String result = "";
            while ((line = reader.readLine()) != null) {
                result += line + "\n";
            }
            return result;
        }
    }

    public static byte[] readBytes(String fileName) throws Exception {
        File file = new File(fileName);
        byte[] data = new byte[(int) file.length()];
        try (FileInputStream fis = new FileInputStream(file);) {
            fis.read(data);
            return data;
        }
    }

    public static byte[] readNio(String fileName) throws Exception {
        Path paths = Paths.get(fileName);
        byte[] data = Files.readAllBytes(paths);
        return data;
    }

    public static Object readObject(String fileName) throws Exception {
        Object obj;
        try (FileInputStream fis = new FileInputStream(fileName);
                ObjectInputStream ois = new ObjectInputStream(fis);) {
            obj = ois.readObject();
            return obj;
        }
    }
}
