package com.company.test;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.company.comm.FileDownloaderListener;

///home/abrahem/IdeaProjects/untitled3/src/com.company.comm.ListenerT.java
public class MainForTesting {

    public static void main(String[] args) {
        try {
             new FileDownloaderListener(2022).start();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void readBashScript() {
        try {
            Process proc = Runtime.getRuntime().exec("/home/abrahem/IdeaProjects/untitled3/src/com/company/a.sh /"); //Whatever you want to execute
            BufferedReader read = new BufferedReader(new InputStreamReader(
                    proc.getInputStream()));
            try {
                proc.waitFor();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            while (read.ready()) {
                System.out.println(read.readLine());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void executeScript() throws IOException, InterruptedException {
        Process p = Runtime.getRuntime().exec("sh /home/abrahem/IdeaProjects/untitled3/src/com/company/a.sh");
        // Process p = Runtime.getRuntime().exec("sh /home/abrahem/Desktop/a.sh");
        p.waitFor();

        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(p.getErrorStream()));


        String line = "";
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        line = "";
        while ((line = errorReader.readLine()) != null) {
            System.out.println(line);
        }
    }
}
