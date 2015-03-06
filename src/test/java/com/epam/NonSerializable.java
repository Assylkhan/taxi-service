package com.epam;

public class NonSerializable {
    private String myData = "";

    public String getMyData() {
        return myData;
    }

    public void setMyData(String myData) {
        this.myData = myData;
    }

    @Override
    public String toString() {
        return "NonSerializable{" +
                "myData='" + myData + '\'' +
                '}';
    }
}
