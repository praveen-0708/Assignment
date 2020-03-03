package com.assignment.wordSearch.Threads;

import com.assignment.wordSearch.WordFinder.FindWord;
import com.assignment.wordSearch.WordFinder.WordLocation;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;

public class QueueProcessor implements Runnable {
    private BlockingQueue<File> queue;
    private SearchResult searchResult;
    private SearchInput searchInput;
    private static Logger logger = Logger.getLogger(WordSearch.class);
    public QueueProcessor() {
    }

    public QueueProcessor(BlockingQueue<File> queue, SearchInput searchInput) {
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

    public void setQueue(BlockingQueue<File> queue) {
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
                wordLocations.addAll(findWord.checkFileForWord(queue.take(), this.searchInput.getWord()));
            }
            this.searchResult.appendWordLocations(wordLocations);
            this.searchResult.addNumberOfResults(wordLocations.size());
            for (WordLocation wordLocation : wordLocations) {
                //System.out.println(wordLocation.toString());
                logger.info(wordLocation.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
