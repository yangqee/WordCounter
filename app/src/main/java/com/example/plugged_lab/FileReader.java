package com.example.plugged_lab;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class FileReader {
    // Variable Declaration
    private String filePath;
    private ArrayList<String> words;

    /**
     * turns filePath into an ArrayList of each .next()
     * @return ArrayList of Strings
     */
    private ArrayList<String> separateText() {
        ArrayList<String> wordsT = new ArrayList<>();
        TestFileReader tfr = new TestFileReader(MainActivity.tContext, filePath);
        Scanner s = new Scanner(tfr.getIs());
        while (s.hasNext()) {
            String str = s.next();
            wordsT.add(str);
        }
        return wordsT;
    }
    public FileReader(String filePath) {
        this.filePath = filePath;
        words = separateText();
//        for (String s: words) {
//            System.out.println(s);
//        }
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<String> getWords() {
        return words;
    }
}
