package com.assignment.wordSearch.Threads;

import com.assignment.wordSearch.WordFinder.WordLocation;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class WordSearch {
    public void findWordInDirectory() throws Exception {
        long startTime = System.currentTimeMillis();
        Logger logger = Logger.getLogger(WordSearch.class);
        SearchInput searchInput = new SearchInput();
        String folderPath = "/home/praveenc/Downloads/SampleProject/src/main/webapp/files";
        Scanner input = new Scanner(System.in);
/*        System.out.println("Enter Folder Path:");
        folderPath = input.nextLine();*/
        System.out.println("Enter word:");
        String word = input.nextLine();
        searchInput.setFolderPath(folderPath);
        searchInput.setWord(word);

        //Queue<File> queue = new LinkedList<>();
        BlockingQueue<File> queue=new LinkedBlockingDeque<>();

        QueueBuilder queueBuilder = new QueueBuilder(queue, searchInput);
        QueueProcessor queueProcessor = new QueueProcessor(queue, searchInput);

        Thread builderThread = new Thread(queueBuilder);
        builderThread.start();
        System.out.println("Enter number of threads");
        int numberOfThreads=input.nextInt();
        Thread.sleep(1000);
        builderThread.join();

        Thread[] threads=new Thread[numberOfThreads];
        for(int index=0;index<numberOfThreads;index++){
            threads[index]=new Thread(queueProcessor,"Thread-"+(index+1));
        }
        for(int index=0;index<numberOfThreads;index++){
            threads[index].start();
        }
        for(int index=0;index<numberOfThreads;index++){
            threads[index].join();
        }
        SearchResult searchResult = queueProcessor.getSearchResult();
        Collections.sort(searchResult.getWordLocations());
        logger.info("Result Word Locations:");
        if (searchResult.getWordLocations().isEmpty()) {
            //System.out.println("Word Not Found!!");
            logger.info("Word Not Found!!");
        } else {
            for (WordLocation wordLocation : searchResult.getWordLocations()) {
                //System.out.println(wordLocation.toString());
                logger.info(wordLocation.toString());
            }
        }
        long endTime = System.currentTimeMillis();
        long executionTime = (endTime - startTime);
        logger.info("Execution Time(in ms):" + executionTime);
    }
}
