package com.assignment.wordSearch.Threads;

import com.assignment.wordSearch.Threads.models.SearchInput;
import com.assignment.wordSearch.Threads.models.SearchResult;
import com.assignment.wordSearch.WordFinder.FindWord;
import com.assignment.wordSearch.WordFinder.IndividualSearchResult;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.*;

public class WordSearchSingleThread implements WordSearch {
    private Logger logger = Logger.getLogger(WordSearchSingleThread.class);

    @Override
    public SearchResult findWordInDirectory(SearchInput searchInput) {
        try {
            long startTime = System.currentTimeMillis();
            SearchResult searchResult = getLocationOfWord(searchInput);
            Collections.sort(searchResult.getIndividualSearchResults());
            long endTime = System.currentTimeMillis();
            long executionTime = (endTime - startTime);
            logger.info("Execution Time(in ms):" + executionTime);
            return searchResult;
        } catch (Exception e) {
            logger.error("error", e);
            throw new RuntimeException("Exception in MultiThread");
        }
    }
    private SearchResult getLocationOfWord(SearchInput searchInput) throws Exception {
        List<IndividualSearchResult> wordLocations = new ArrayList<>();
        wordLocations = getAllFilesInDirectory(searchInput.getFolderPath(), wordLocations, searchInput.getWord());
        SearchResult searchResult=new SearchResult();
        searchResult.setIndividualSearchResults(wordLocations);
        searchResult.setNumberOfResults(wordLocations.size());
        return searchResult;
    }

    private List<IndividualSearchResult> getAllFilesInDirectory(String folderPath, List<IndividualSearchResult> wordLocationList, String word) throws Exception {
        FindWord findWord = new FindWord();
        File folder = new File(folderPath);
        File[] fileList = folder.listFiles();
        if (fileList != null) {
            for (File file : fileList) {
                if (file.isFile()) {
                    wordLocationList.addAll(findWord.checkFileForWord(file, word));
                } else if (file.isDirectory()) {
                    wordLocationList=getAllFilesInDirectory(file.getAbsolutePath(), wordLocationList, word);
                }
            }
        }
        return wordLocationList;
    }
}
