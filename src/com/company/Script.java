package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Script {
    public static void executeCommand(String filePath) {
        File file = new File(filePath);
        if (!file.isFile()) {
            throw new IllegalArgumentException("The file " + filePath + " does not exist");
        }
        try {
            if (isLinux()) {
                Process p = Runtime.getRuntime().exec("sh " + filePath);
                p.waitFor();
            } else if (isWindows()) {
                Runtime.getRuntime().exec("cmd /c start " + filePath);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isLinux() {
        String os = System.getProperty("os.name");
        return os.toLowerCase().indexOf("linux") >= 0;
    }

    private static boolean isWindows() {
        String os = System.getProperty("os.name");
        return os.toLowerCase().indexOf("windows") >= 0;
    }

}
