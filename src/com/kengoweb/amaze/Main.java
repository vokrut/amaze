package com.kengoweb.amaze;

import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Main {

    private static DocumentBuilder documentBuilder;

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

        createDocumentBuilder();
        if (documentBuilder == null) {
            System.out.println("Can't create document builder for xml files");
            return;
        }

        Arrays.sort(levelFiles);
        for (File fileLevel : levelFiles) {
            if (fileLevel.isDirectory()) {
                continue;
            }
            try {
                Level level = new Level(fileLevel, documentBuilder);
                String solution = level.decide();
                if (solution.isEmpty()) {
                    System.out.println("Can't find solution for " + level);
                } else {
                    System.out.println(level + " is DONE!");
                }
            } catch (IOException | SAXException e) {
                e.printStackTrace();
                System.out.println("Can't parse level from file " + fileLevel.getName());
            }
        }
    }

    private static void createDocumentBuilder() {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
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
