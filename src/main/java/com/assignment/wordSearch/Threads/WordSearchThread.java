package com.assignment.wordSearch.Threads;

import com.assignment.wordSearch.WordFinder.FindWord;
import com.assignment.wordSearch.WordFinder.WordLocation;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Scanner;

public class WordSearchThread implements Runnable{
    @Override
    public void run() {
        try{
            long startTime = System.currentTimeMillis();
            Logger logger= Logger.getLogger(WordSearchThread.class);
            logger.info("Thread name:"+Thread.currentThread().getName());
            String folderPath = "/home/praveenc/Downloads/SampleProject/src/main/webapp/files";
            Scanner input=new Scanner(System.in);
            /*System.out.println("Enter Folder Path:");
            folderPath=input.nextLine();*/
            System.out.println("Enter word:");
            String word=input.nextLine();
            FindWord findWord = new FindWord();
            List<WordLocation> wordLocations = findWord.getLocationOfWord(word, folderPath);
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
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
