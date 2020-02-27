package com.assignment.wordSearch.WordFinder;

public class WordLocation {
    private String word;
    private String fileName;
    private int lineNumber;
    private int position;

    public WordLocation(String word,String fileName, int lineNumber, int position) {
        this.word=word;
        this.fileName = fileName;
        this.lineNumber = lineNumber;
        this.position = position;
    }
    public WordLocation(){

    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
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
        return  "Word=" + word
                +" filename=" + fileName  +
                ", lineNumber=" + lineNumber +
                ", position=" + position ;

    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        if(!(obj instanceof WordLocation))
            return false;

        WordLocation wordLocation=(WordLocation)obj;
        return (wordLocation.getWord().equals(this.word) &&
                wordLocation.getFileName().equals(this.fileName) &&
                wordLocation.getLineNumber()==this.lineNumber &&
                wordLocation.getPosition()==this.position
        );
    }
}
