package com.assignment.wordSearch.WordFinder;


public class IndividualSearchResult implements Comparable<IndividualSearchResult> {
    private String word;
    private String fileName;
    private int lineNumber;
    private int position;

    public IndividualSearchResult(String word, String fileName, int lineNumber, int position) {
        this.word = word;
        this.fileName = fileName;
        this.lineNumber = lineNumber;
        this.position = position;
    }

    public IndividualSearchResult() {

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
        return "Word=" + word
                + " filename=" + fileName +
                ", lineNumber=" + lineNumber +
                ", position=" + position;

    }

    @Override
    public int hashCode() {
        return this.word.hashCode() + this.fileName.hashCode() + this.lineNumber + this.position;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof IndividualSearchResult))
            return false;

        IndividualSearchResult individualSearchResult = (IndividualSearchResult) obj;
        return (individualSearchResult.getWord().equals(this.word) &&
                individualSearchResult.getFileName().equals(this.fileName) &&
                individualSearchResult.getLineNumber() == this.lineNumber &&
                individualSearchResult.getPosition() == this.position
        );
    }

    @Override
    public int compareTo(IndividualSearchResult o) {
        if (this.getFileName().equals(o.getFileName())) {
            if (this.getLineNumber() == o.getLineNumber()) {
                return Integer.compare(this.getPosition(), o.getPosition());
            } else {
                return Integer.compare(this.getLineNumber(), o.getLineNumber());
            }
        } else
            return this.getFileName().compareTo(o.getFileName());
    }
}
