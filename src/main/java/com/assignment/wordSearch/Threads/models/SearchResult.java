package com.assignment.wordSearch.Threads.models;

import com.assignment.wordSearch.WordFinder.IndividualSearchResult;

import java.util.ArrayList;
import java.util.List;

public class SearchResult {
    private int numberOfResults;
    private List<IndividualSearchResult> individualSearchResults;

    public SearchResult() {
        individualSearchResults =new ArrayList<>();
    }

    public SearchResult(List<IndividualSearchResult> individualSearchResults, int numberOfResults) {
        this.individualSearchResults = individualSearchResults;
        this.numberOfResults = numberOfResults;
    }
    public int getNumberOfResults() {
        return numberOfResults;
    }

    public void setNumberOfResults(int numberOfResults) {
        this.numberOfResults = numberOfResults;
    }

    public List<IndividualSearchResult> getIndividualSearchResults() {
        return individualSearchResults;
    }

    public void setIndividualSearchResults(List<IndividualSearchResult> individualSearchResults) {
        this.individualSearchResults = individualSearchResults;
    }
    public void appendWordLocations(List<IndividualSearchResult> individualSearchResults) {
        this.individualSearchResults.addAll(individualSearchResults);
        this.numberOfResults=this.individualSearchResults.size();
    }

}
