package com.epam;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;

public class DataObject extends NonSerializable implements Serializable {
    private int i;
    private String s;
    private CustomObject co = new CustomObject();
    private transient String[] strArray = new String[2];

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(getMyData());
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        setMyData((String)in.readObject());
    }

    @Override
    public String toString() {
        return "DataObject{" +
                "i=" + i +
                ", s='" + s + '\'' +
                ", co=" + co +
                ", strArray=" + Arrays.toString(strArray) +
                "} " + super.toString();
    }
}
