package com.thoughtworks.legacycode.exercise;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileManager {
    private static Defragger df;
    private String input = null;
    private BufferedReader br = null;

    public static void run() throws PartitionDoesNotExistException {
        FileManager fm = new FileManager();
        while(!fm.input("Enter disk command").equals("quit")){
            switch(fm.getInput()){
                case "format":
                    DiskUtils.getInstance().format(fm.input("Enter name of drive to format"));
                    break;
                case "print":
                    String fn = fm.input("Enter file name");
                    File file = DiskUtils.getInstance().getFile(fn);
                    FilePrinter fp = new FilePrinter(file);
                    fp.print();
                    break;
                case "defrag":
                    DiskUtils.getInstance().useDefragger(fm, df);
            }
        }
    }

    public String getInput() {
        if (input == null){
            input = input();
        }
        return input;
    }

    private String input() {
        if (br == null){
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        try {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String input(String prompt) {
        System.out.println(prompt);
        if (br == null){
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        try {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void init(String[] partitions) {
        df = new Defragger(partitions);
    }
}
