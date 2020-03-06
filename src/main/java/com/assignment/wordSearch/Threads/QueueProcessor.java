package com.assignment.wordSearch.Threads;

import com.assignment.wordSearch.Threads.models.SearchInput;
import com.assignment.wordSearch.Threads.models.SearchResult;
import com.assignment.wordSearch.WordFinder.FindWord;
import com.assignment.wordSearch.WordFinder.IndividualSearchResult;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class QueueProcessor implements Runnable {
    private BlockingQueue<File> queue;
    private SearchResult searchResult;
    private SearchInput searchInput;
    private QueueBuilder queueBuilder;
    private static Logger logger = Logger.getLogger(WordSearchMultiThread.class);
    public QueueProcessor() {
    }

    public QueueProcessor(BlockingQueue<File> queue, SearchInput searchInput,QueueBuilder queueBuilder) {
        this.queue = queue;
        this.searchInput = searchInput;
        this.searchResult = new SearchResult();
        this.queueBuilder=queueBuilder;
    }

    public SearchInput getSearchInput() {
        return searchInput;
    }

    public void setSearchInput(SearchInput searchInput) {
        this.searchInput = searchInput;
    }

    public BlockingQueue<File> getQueue() {
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

    public QueueBuilder getQueueBuilder() {
        return queueBuilder;
    }

    public void setQueueBuilder(QueueBuilder queueBuilder) {
        this.queueBuilder = queueBuilder;
    }

    @Override
    public void run() {
        try {
            FindWord findWord = new FindWord();
            List<IndividualSearchResult> individualSearchResults = new ArrayList<>();
            while (!getQueueBuilder().isFileBuilderCompleted() || !queue.isEmpty()) {
                individualSearchResults.addAll(findWord.checkFileForWord(queue.take(), searchInput.getWord()));
            }
            searchResult.appendWordLocations(individualSearchResults);
//            for (IndividualSearchResult individualSearchResult : individualSearchResults) {
//                logger.info(individualSearchResult.toString());
//            }
        } catch (Exception e) {
            throw new RuntimeException("Exception in QueueProcessor");
        }
    }
}
