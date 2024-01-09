package com.example.plugged_lab;

import java.util.ArrayList;

public class WordProcessors {
    // Variable Declaration
    private ArrayList<String> unprocessedWords;
    private ArrayList<String> processedWords;
    private ArrayList<String> commonWords;

    /**
     * turns every element of an ArrayList lowercase
     * @param al ArrayList of Strings
     * @return ArrayList of Strings
     */
    private ArrayList<String> lowercase(ArrayList<String> al) {
        ArrayList<String> temp = new ArrayList<>();
        for (String s : al) {
            temp.add(s.toLowerCase());
        }
        return temp;
    }
    /**
     * removes punctuation from every element of an ArrayList
     * @param al ArrayList of Strings
     * @return ArrayList of Strings
     */
    private ArrayList<String> removePunctuation(ArrayList<String> al) {
        ArrayList<String> temp = new ArrayList<>();
        for (String s : al) {
            String t = s.replace(",", "");
            t = t.replace(".", "");
            t = t.replace("!", "");
            t = t.replace("?", "");
            t = t.replace("\"", "");
            t = t.replace(";", "");
            t = t.replace(":", "");
            t = t.replace("(", "");
            t = t.replace(")", "");
            temp.add(t);
        }
        return temp;
    }

    /**
     * removes common words from an ArrayList
     * @param al ArrayList of Strings
     * @return ArrayList of Strings
     */
    private ArrayList<String> removeCommonWords(ArrayList<String> al) {
        ArrayList<String> temp = new ArrayList<>();
        for (String s : al) {
            if (!commonWords.contains(s)) {
                temp.add(s);
            }
        }
        return temp;
    }

    /**
     * constructor
     */
    public WordProcessors(String commonWordsFilePath, String textFilePath) {
        FileReader textFile = new FileReader(textFilePath);
        FileReader commonWordsFile = new FileReader(commonWordsFilePath);
        unprocessedWords = textFile.getWords();
        commonWords = removePunctuation(lowercase(commonWordsFile.getWords()));
        processedWords = removeCommonWords(removePunctuation(lowercase(unprocessedWords)));
    }

    public ArrayList<String> getProcessedWords() {
        return processedWords;
    }

    public ArrayList<String> getUnprocessedWords() {
        return unprocessedWords;
    }

    public ArrayList<String> getCommonWords() {
        return commonWords;
    }
}
