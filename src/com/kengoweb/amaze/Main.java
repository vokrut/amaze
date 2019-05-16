package com.kengoweb.amaze;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
	// write your code here
        File amazeDir = new File("src/amaze_levels");
        if (!amazeDir.exists()) {
            System.out.println("Folder amaze_levels not found");
        } else {
            processLevelFiles(amazeDir.listFiles());
        }
    }

    private static void processLevelFiles(File[] levelFiles) {
        System.out.println("processLevelFiles");
        if (!checkLevelsFolder(levelFiles)) {
            return;
        }
        Arrays.sort(levelFiles);
        for (File fileLevel : levelFiles) {
            if (fileLevel.isDirectory()) {
                continue;
            }
            System.out.println(fileLevel.getName());
        }
    }

    private static boolean checkLevelsFolder(File[] levelFiles) {
        if (levelFiles == null) {
            System.out.println("Can't get levels from folder");
            return false;
        } else if (levelFiles.length == 0) {
            System.out.println("Level folders is empty");
            return false;
        }
        return true;
    }
}
