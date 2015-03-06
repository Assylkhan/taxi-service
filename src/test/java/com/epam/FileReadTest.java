package com.epam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class FileReadTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine()) {
            String fileName = scanner.nextLine();
            InputStream stream = FileReadTest.class.getClassLoader().getResourceAsStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            StringBuilder sb = new StringBuilder();
            String s = null;
            try {
                while ((s = br.readLine()) != null){
                    sb.append(s);
                }
                System.out.println(sb);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
