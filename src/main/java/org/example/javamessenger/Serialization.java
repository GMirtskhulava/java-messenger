package org.example.javamessenger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serialization {
    private static final String DATA_FOLDER = "data";

    public static void serializeObject(Object object, String fileName) throws IOException {
        File dataFolder = new File(DATA_FOLDER);
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }

        File file = new File(dataFolder, fileName);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(object);
        objectOutputStream.close();
    }

    public static Object deserializeObject(String fileName) throws IOException, ClassNotFoundException {
        File file = new File(DATA_FOLDER, fileName);
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        Object object = objectInputStream.readObject();
        objectInputStream.close();
        return object;
    }

    public static boolean fileExists(String fileName) {
        File file = new File(DATA_FOLDER, fileName);
        return file.exists();
    }
}
