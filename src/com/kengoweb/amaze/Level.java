package com.kengoweb.amaze;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import java.io.File;
import java.io.IOException;

public class Level {

    private String fileName;
    private int[][] squares;
    private int ballLocationColumn;
    private int ballLocationRow;
    private int height;
    private int width;
    private int freeCells;

    Level(File fileLevel, DocumentBuilder documentBuilder) throws IOException, SAXException {
        Document document = documentBuilder.parse(fileLevel);
        fileName = fileLevel.getName();
        NamedNodeMap layerAttributes = document.getElementsByTagName("layer").item(0).getAttributes();
        height = Integer.parseInt(layerAttributes.getNamedItem("height").getNodeValue());
        width = Integer.parseInt(layerAttributes.getNamedItem("width").getNodeValue());
        squares = new int[height][width];
        freeCells = 0;

        Node dataNode = document.getElementsByTagName("data").item(0);
        String data = dataNode.getTextContent();
        String[] dataRows = data.split("\n");
        boolean ballPositionIsFounded = false;
        for (int i = 1; i <= height; i++) {
            String[] cells = dataRows[i].split(",");
            for (int j = 0; j < cells.length; j++) {
                int cell = Integer.parseInt(cells[j]);
                squares[i-1][j] = cell;
                if (cell != 0) {
                    if (!ballPositionIsFounded) {
                        ballPositionIsFounded = true;
                        ballLocationColumn = j;
                        ballLocationRow = i - 1;
                    }
                    freeCells++;
                }
            }
        }
    }

    String getFileName() {
        return fileName;
    }

    @Override
    public String toString() {
        return "Level " + fileName +
                " height=" + height + ", width=" + width +
                " ball=(" + ballLocationColumn + ", " + ballLocationRow + ")";
    }

    String decide() {
        String solution = "";

        fillCell(ballLocationRow, ballLocationColumn);
        if (freeCells == 0) {
            return solution;
        }

        return solution;
    }

    private void fillCell(int row, int column) {
        if (squares[row][column] != -1) {
            freeCells--;
            squares[row][column] = -1;
        }
    }
}
