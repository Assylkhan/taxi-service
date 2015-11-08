package com.epam;

import java.io.*;

public class CustomObject implements Serializable {
    private boolean b;

    public static void main(String[] args) {
        System.out.println(System.getProperty("java.class.path"));
    }

/*    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeBoolean(b);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        b = in.readBoolean();
    }*/
}
