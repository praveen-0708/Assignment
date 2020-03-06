package com.assignment.wordSearch.Threads;

import com.assignment.wordSearch.Threads.models.SearchInput;
import com.assignment.wordSearch.Threads.models.SearchResult;
import com.assignment.wordSearch.WordFinder.FindWord;
import com.assignment.wordSearch.WordFinder.IndividualSearchResult;
import org.apache.log4j.Logger;
import java.util.*;



public class WordSearchSingleThread implements WordSearch {
    private Logger logger = Logger.getLogger(WordSearchSingleThread.class);

    @Override
    public SearchResult findWordInDirectory(SearchInput searchInput) {
        try {
            long startTime = System.currentTimeMillis();
            FindWord findWord=new FindWord();
            SearchResult searchResult = findWord.getLocationOfWord(searchInput);
            Collections.sort(searchResult.getIndividualSearchResults());
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
