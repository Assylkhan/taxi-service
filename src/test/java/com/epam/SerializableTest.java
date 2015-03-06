package com.epam;

import java.io.*;

public class SerializableTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        DataObject dataObject = new DataObject();
        File file = new File("store.bin");
        dataObject.setMyData("ekw9fgwje98gf3h4j98fgj498fj49foij438fj348fj34908fj0943jf43j3f9j");

        FileOutputStream fo = new FileOutputStream(file);
        ObjectOutputStream so = new ObjectOutputStream(fo);
        so.writeObject(dataObject);
        so.flush();
        so.close();

        FileInputStream fi = new FileInputStream("store.bin");
        ObjectInputStream si = new ObjectInputStream(fi);
        DataObject dataObject1 = (DataObject) si.readObject();
        System.out.println(dataObject1);
        si.close();
    }
}
