package com.example.plugged_lab;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    String fileName;
    static String t;
    TextView fileNamePrompt, fileNotFound, results;
    EditText fileNameEdit;
    Button top, top5;
    public static Context tContext;
    public static boolean doesFileExist = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化 UI 元素
        fileNamePrompt = findViewById(R.id.fileNamePrompt);
        fileNotFound = findViewById(R.id.fileNotFound);
        results = findViewById(R.id.resulting);
        fileNameEdit = findViewById(R.id.fileNameEdit);
        top = findViewById(R.id.top);
        top5 = findViewById(R.id.top5);

        // 设置按钮点击事件
        top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 处理点击事件的逻辑
                handleTopButtonClick();
            }
        });

        top5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 处理点击事件的逻辑
                handleTop5ButtonClick();
            }
        });
    }

    // 点击 Top 按钮的处理逻辑
    private void handleTopButtonClick() {
        fileName = String.valueOf(fileNameEdit.getText());
        System.out.println(fileName);

        // 使用 Counter 类进行单词统计
        Counter counter = new Counter("commonWords.txt", fileName);

        if (doesFileExist) {
            String[] topFiveWords = counter.getTopFiveWords();
            int[] topFiveFrequencies = counter.getTopFiveFrequencies();

            String setText = "The most common word in file " + fileName + " was " + topFiveWords[0] +
                    " with " + topFiveFrequencies[0] + " occurrences.";

            results.setText(setText);
            System.out.println();
        } else {
            fileNotFound.setText("No File Exists!");
            results.setText("");
        }
    }

    // 点击 Top5 按钮的处理逻辑
    private void handleTop5ButtonClick() {
        fileName = String.valueOf(fileNameEdit.getText());
        System.out.println(fileName);

        // 使用 Counter 类进行单词统计
        Counter counter = new Counter("commonWords.txt", fileName);

        if (doesFileExist) {
            String[] topFiveWords = counter.getTopFiveWords();
            int[] topFiveFrequencies = counter.getTopFiveFrequencies();

            t = "The top " + topFiveWords.length + " words in text one and their frequencies are:\n";
            printTopFive(topFiveWords, topFiveFrequencies, 0);

            results.setText(t);
        } else {
            fileNotFound.setText("No File Exists!");
            results.setText("");
        }
    }

    // 打印前五个单词及其频率的辅助方法
    private static void printTopFive(String[] topFiveWords, int[] topFiveFrequencies, int tCnt) {
        for (int i = 0; i < topFiveWords.length; ++i) {
            if (i == 0) {
                t += (i + 1 + ") " + topFiveWords[i] + ": " + topFiveFrequencies[i]);
                t += "\n";
            } else {
                if (topFiveFrequencies[i] == topFiveFrequencies[i - 1]) {
                    tCnt++;
                    t += (i + 1 - tCnt + ") " + topFiveWords[i] + ": " + topFiveFrequencies[i]);
                    t += "\n";
                } else {
                    tCnt = 0;
                    t += (i + 1 + ") " + topFiveWords[i] + ": " + topFiveFrequencies[i]);
                    t += "\n";
                }
            }
        }
    }
}
