package com.example.abevillalobos.lakbayph;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
/**
 * Created by Agassi on 29/04/2016.
 */
public class FSUtil {
    public static boolean isStorageReady() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    public static String getStorage() {
        return Environment.getExternalStorageDirectory().toString() + "/Qwitter/messages/";
    }

    public static void  write(String filename, byte[] data) {
        File fOutputDir = new File(getStorage());
        File fOutput = new File(getStorage(), filename + ".txt");

        FileOutputStream fileOut = null;
        try {
            if (!fOutputDir.exists()) {
                fOutputDir.mkdirs();
            }

            if (!fOutput.exists()) {
                fOutput.createNewFile();
            }

            fileOut = new FileOutputStream(fOutput, false);
            fileOut.write(data);
            fileOut.close();
        } catch (FileNotFoundException e) {
            Log.e("ERROR", "File not found: " + fOutput.toString());
        } catch (Exception e) {
            Log.e("ERROR", "Exception occurred: " + e.getMessage());
        }

        return;
    }

    public static FileInputStream getFileInputStream(String filename) {
        File fInput = new File(getStorage(), filename + ".txt");

        if (!fInput.exists()) {
            return null;
        }

        Log.i("INFO", "Acccessing file:" + fInput.toString());
        Log.i("INFO", "Exists: " + fInput.exists());

        FileInputStream fileIn = null;
        try {
            fileIn = new FileInputStream(fInput);
        } catch (Exception e) {
            Log.e("ERROR", "Exception occurred: " + e.getMessage());
        }

        return fileIn;
    }

//    public static String getFileData(String filename) {
//        FileInputStream fis = getFileInputStream(filename);
//    }
}
