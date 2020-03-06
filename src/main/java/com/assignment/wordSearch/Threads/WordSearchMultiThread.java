package com.assignment.wordSearch.Threads;

import com.assignment.wordSearch.Threads.models.SearchInput;
import com.assignment.wordSearch.Threads.models.SearchResult;
import com.assignment.wordSearch.WordFinder.IndividualSearchResult;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class WordSearchMultiThread implements WordSearch {

    private Logger logger = Logger.getLogger(WordSearchMultiThread.class);
    private final static int NUMBER_OF_THREADS = 5;

    @Override
    public SearchResult findWordInDirectory(SearchInput searchInput) {
        try {
            long startTime = System.currentTimeMillis();
            BlockingQueue<File> queue = new LinkedBlockingDeque<>();
            QueueBuilder queueBuilder = new QueueBuilder(queue, searchInput);
            QueueProcessor queueProcessor = new QueueProcessor(queue, searchInput,queueBuilder);
            Thread builderThread = new Thread(queueBuilder);
            builderThread.start();
            Thread.sleep(1000);

            int numberOfThreads = NUMBER_OF_THREADS;
            Thread[] threads = new Thread[numberOfThreads];
            for (int index = 0; index < numberOfThreads; index++) {
                threads[index] = new Thread(queueProcessor, "Thread-" + (index + 1));
                threads[index].start();
            }
            for (int index = 0; index < numberOfThreads; index++) {
                threads[index].join();
            }
            SearchResult searchResult = queueProcessor.getSearchResult();
            Collections.sort(searchResult.getIndividualSearchResults());
            logger.info("Result Word Locations:");
            if (searchResult.getIndividualSearchResults().isEmpty()) {
                //System.out.println("Word Not Found!!");
                logger.info("Word Not Found!!");
            } else {
                for (IndividualSearchResult individualSearchResult : searchResult.getIndividualSearchResults()) {
                    //System.out.println(wordLocation.toString());
                    logger.info(individualSearchResult.toString());
                }
            }
            long endTime = System.currentTimeMillis();
            long executionTime = (endTime - startTime);
            logger.info("Execution Time(in ms):" + executionTime);
            return searchResult;
        } catch (Exception e) {
            logger.error("error", e);
            throw new RuntimeException("Exception in MultiThread");
        }
    }
}
