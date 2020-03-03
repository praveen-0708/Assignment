package com.assignment.wordSearch.Threads;

import com.assignment.wordSearch.WordFinder.FindWord;
import com.assignment.wordSearch.WordFinder.WordLocation;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class QueueProcessor implements Runnable {
    private Queue<File> queue;
    private SearchResult searchResult;
    private SearchInput searchInput;

    public QueueProcessor() {
    }

    public QueueProcessor(Queue<File> queue, SearchInput searchInput) {
        this.queue = queue;
        this.searchInput = searchInput;
        this.searchResult = new SearchResult();
    }

    public SearchInput getSearchInput() {
        return searchInput;
    }

    public void setSearchInput(SearchInput searchInput) {
        this.searchInput = searchInput;
    }

    public Queue<File> getQueue() {
        return queue;
    }

    public void setQueue(Queue<File> queue) {
        this.queue = queue;
    }

    public SearchResult getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(SearchResult searchResult) {
        this.searchResult = searchResult;
    }

    @Override
    public void run() {
        try {
            FindWord findWord = new FindWord();
            List<WordLocation> wordLocations = new ArrayList<>();
            while (!queue.isEmpty()) {
                wordLocations.addAll(findWord.checkFileForWord(queue.remove(), this.searchInput.getWord()));
            }
            Collections.sort(wordLocations);
            this.searchResult.setWordLocations(wordLocations);
            this.searchResult.setNumberOfResults(wordLocations.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
