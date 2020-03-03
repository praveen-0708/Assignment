package com.assignment.wordSearch.Threads;

import com.assignment.wordSearch.WordFinder.WordLocation;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

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

        Queue<File> queue = new LinkedList<>();
        QueueBuilder queueBuilder = new QueueBuilder(queue, searchInput);
        QueueProcessor queueProcessor = new QueueProcessor(queue, searchInput);

        Thread thread1 = new Thread(queueBuilder);
        Thread thread2 = new Thread(queueProcessor);

        thread1.start();
        Thread.sleep(1000);
        thread2.start();

        thread1.join();
        thread2.join();

        SearchResult searchResult = queueProcessor.getSearchResult();
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
