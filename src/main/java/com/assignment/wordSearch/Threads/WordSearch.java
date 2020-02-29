package com.assignment.wordSearch.Threads;

import com.assignment.wordSearch.WordFinder.WordLocation;
import org.apache.log4j.Logger;
import java.util.List;
import java.util.Scanner;

public class WordSearch{
    List<WordLocation> wordLocations;
    public void findWordInDirectory() throws Exception {
        long startTime = System.currentTimeMillis();
        Logger logger= Logger.getLogger(WordSearch.class);
        String folderPath = "/home/praveenc/Downloads/SampleProject/src/main/webapp/files";
        QueueThread queueThread = new QueueThread();
        Scanner input = new Scanner(System.in);
//        System.out.println("Enter Folder Path:");
//        folderPath = input.nextLine();
        System.out.println("Enter word:");
        String word = input.nextLine();
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                queueThread.add(folderPath);
            }
        };
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                try {
                    wordLocations = queueThread.delete(word);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread1.start();
        Thread.sleep(1000);
        thread2.start();

        thread1.join();
        thread2.join();
        if(wordLocations.isEmpty()){
            //System.out.println("Word Not Found!!");
            logger.info("Word Not Found!!");
        }
        else{
            for (WordLocation wordLocation : wordLocations){
                //System.out.println(wordLocation.toString());
                logger.info(wordLocation.toString());
            }
        }
        long endTime = System.currentTimeMillis();
        long executionTime = (endTime - startTime);
        logger.info("Execution Time(in ms):"+executionTime);
    }
}
