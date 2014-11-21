package at.wrdlbrnft.helpers;

import java.io.*;

/**
 * Created with IntelliJ Idea 13
 * User: Xaver
 * Date: 16/06/14
 */
public class SerializationHelper {

    public static Object deserialize(String s) {
        try {
            byte [] data = Base64Coder.decode(s);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Object object = objectInputStream.readObject();
            objectInputStream.close();
            return object;
        } catch (IOException e) {
            throw new IllegalStateException("Error while deserializing a String!", e);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Error while deserializing a String!", e);
        }
    }

    public static String serialize(Object object) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream( byteArrayOutputStream );
            objectOutputStream.writeObject(object);
            objectOutputStream.close();
            return new String(Base64Coder.encode(byteArrayOutputStream.toByteArray()));
        } catch (IOException e) {
            throw new IllegalStateException("Error while serializing a String!", e);
        }
    }
}
