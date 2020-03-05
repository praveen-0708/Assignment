package com.assignment.wordSearch.Threads.models;

public class SearchInput {
    private String word;
    private String folderPath;

    public SearchInput() {
    }

    public SearchInput(String word, String folderPath) {
        this.word = word;
        this.folderPath = folderPath;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }
}
