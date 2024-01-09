package com.example.plugged_lab;

import java.util.ArrayList;

public class Counter {
    // Variable Declaration
    private ArrayList<String> textWords;
    private ArrayList<String> uniqueWords;
    private ArrayList<Integer> occurrences;
    public String[] topFiveWords;
    public int[] topFiveFrequencies;


    /**
     * counts unique occurrences in the textWords ArrayList
     */
    private void countUniqueOccurrences() {
        for (String s : textWords) {
            int idx = uniqueWords.indexOf(s);
            if (idx != -1) {
                occurrences.set(idx, occurrences.get(idx) + 1);
            } else {
                uniqueWords.add(s);
                occurrences.add(1);
            }
        }
    }

    /**
     * Calculates the top-N from uniqueWords and occurrences variables
     */
    private void calcTopFive() {
        ArrayList<String> tTopFiveWords = new ArrayList<>();
        ArrayList<Integer> tTopFiveFrequencies = new ArrayList<>();
        ArrayList<Integer> topFiveIndexes = new ArrayList<>();

        int i = 0, lastMax = -1;
        boolean shouldContinue = true;
        while (true) {
            int loopMaxValue = 0, t = -1;
            for (int j = 0; j < occurrences.size(); ++j) {
                if (topFiveIndexes.contains(j)) {
                    continue;
                }
                if (occurrences.get(j) >= loopMaxValue) {
                    t = j;
                    loopMaxValue = occurrences.get(j);
                }
            }
            if (i >= 5) shouldContinue = loopMaxValue == lastMax;
            if (!shouldContinue) {
                break;
            } else {
                topFiveIndexes.add(t);
                tTopFiveWords.add(uniqueWords.get(topFiveIndexes.get(i)));
                tTopFiveFrequencies.add(occurrences.get(topFiveIndexes.get(i)));
                ++i;
                lastMax = loopMaxValue;
            }
        }
        topFiveWords = new String[tTopFiveWords.size()];
        for (int p = 0; p < topFiveWords.length; ++p) {
            topFiveWords[p] = tTopFiveWords.get(p);
        }
        topFiveFrequencies = new int[tTopFiveFrequencies.size()];
        for (int p = 0; p < topFiveFrequencies.length; ++p) {
            topFiveFrequencies[p] = tTopFiveFrequencies.get(p);
        }
    }

    /**
     * @param commonWordsFilePath String value of the file path to the common words .txt file
     * @param textFilePath String value of the file path to the text .txt file
     */
    public Counter(String commonWordsFilePath, String textFilePath) {
        WordProcessors textProcessor = new WordProcessors(commonWordsFilePath, textFilePath);
        textWords = textProcessor.getProcessedWords();

        uniqueWords = new ArrayList<>();
        occurrences = new ArrayList<>();

        countUniqueOccurrences();

        calcTopFive();
    }

    /**
     * @return int[] array: topFiveFrequencies
     */
    public int[] getTopFiveFrequencies() {
        return topFiveFrequencies;
    }

    /**
     * @return String[] array: topFiveWords
     */
    public String[] getTopFiveWords() {
        return topFiveWords;
    }
}
