package com.epam;

import java.io.*;

public class CustomObject implements Serializable {
    private boolean b;

/*    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeBoolean(b);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        b = in.readBoolean();
    }*/
}
