package com.kengoweb.amaze;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import java.io.File;
import java.io.IOException;

public class Level {

    private String fileName;
    private int[][] squares;
    private int ballStartLocationX;
    private int ballStartLocationY;
    private int height;
    private int width;

    Level(File fileLevel, DocumentBuilder documentBuilder) throws IOException, SAXException {
        Document document = documentBuilder.parse(fileLevel);
        fileName = fileLevel.getName();
        NamedNodeMap layerAttributes = document.getElementsByTagName("layer").item(0).getAttributes();
        height = Integer.parseInt(layerAttributes.getNamedItem("height").getNodeValue());
        width = Integer.parseInt(layerAttributes.getNamedItem("width").getNodeValue());
        squares = new int[height][width];

        Node dataNode = document.getElementsByTagName("data").item(0);
        String data = dataNode.getTextContent();
        String[] dataRows = data.split("\n");
        boolean ballPositionIsFounded = false;
        for (int i = 1; i <= height; i++) {
            String[] cells = dataRows[i].split(",");
            for (int j = 0; j < cells.length; j++) {
                int cell = Integer.parseInt(cells[j]);
                squares[i-1][j] = cell;
                if (!ballPositionIsFounded && cell != 0) {
                    ballPositionIsFounded = true;
                    ballStartLocationX = j;
                    ballStartLocationY = i-1;
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Level " + fileName +
                " height=" + height + ", width=" + width +
                " ball=(" + ballStartLocationX + ", " + ballStartLocationY+ ")";
    }

    public String decide() {
        String solution = "";
        return solution;
    }
}
