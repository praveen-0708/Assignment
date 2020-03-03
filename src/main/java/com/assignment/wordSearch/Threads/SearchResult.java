package com.assignment.wordSearch.Threads;

import com.assignment.wordSearch.WordFinder.WordLocation;

import java.util.ArrayList;
import java.util.List;

public class SearchResult {
    private List<WordLocation> wordLocations;
    private int numberOfResults;
    public SearchResult() {
        wordLocations=new ArrayList<>();
    }

    public SearchResult(List<WordLocation> wordLocations, int numberOfResults) {
        this.wordLocations = wordLocations;
        this.numberOfResults = numberOfResults;
    }
    public int getNumberOfResults() {
        return numberOfResults;
    }

    public void setNumberOfResults(int numberOfResults) {
        this.numberOfResults = numberOfResults;
    }
    public void addNumberOfResults(int numberOfResults){
        this.numberOfResults+=numberOfResults;
    }

    public List<WordLocation> getWordLocations() {
        return wordLocations;
    }

    public void setWordLocations(List<WordLocation> wordLocations) {
        this.wordLocations=wordLocations;
    }
    public void appendWordLocations(List<WordLocation> wordLocations) {
        this.wordLocations.addAll(wordLocations);
    }

}
