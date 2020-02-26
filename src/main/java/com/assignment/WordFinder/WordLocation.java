package com.assignment.WordFinder;

public class WordLocation {
    private String fileName;
    private int lineNumber;
    private int position;

    public WordLocation(String fileName, int lineNumber, int position) {
        this.fileName = fileName;
        this.lineNumber = lineNumber;
        this.position = position;
    }
    public WordLocation(){

    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return  "filename=" + fileName  +
                ", lineNumber=" + lineNumber +
                ", position=" + position ;

    }
}
