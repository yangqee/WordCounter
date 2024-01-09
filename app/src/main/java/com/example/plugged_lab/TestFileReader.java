package com.example.plugged_lab;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

public class TestFileReader {
    private Context mContext;
    private InputStream is = null;

    public InputStream getIs() {
        return is;
    }

    public TestFileReader(Context context, String fileName) {
        mContext = context;
        MainActivity.doesFileExist = true;
        try {
            is = mContext.getAssets().open(fileName);
            System.out.println(is.read());
        } catch (IOException e) {
            System.out.println("oh no");
            MainActivity.doesFileExist = false;
        }
    }
}
