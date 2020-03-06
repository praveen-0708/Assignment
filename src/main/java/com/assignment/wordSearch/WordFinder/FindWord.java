package com.assignment.wordSearch.WordFinder;

import com.assignment.wordSearch.Threads.models.SearchInput;
import com.assignment.wordSearch.Threads.models.SearchResult;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindWord {

    public List<IndividualSearchResult> checkFileForWord(File file, String word) {
        List<IndividualSearchResult> wordLocationsPerFile = new ArrayList<>();
        FileReader fileReader = null;
        LineNumberReader lineNumberReader = null;
        try {
            fileReader = new FileReader(file);
            lineNumberReader = new LineNumberReader(fileReader);
            String line;
            Pattern compile = Pattern.compile(word);
            while ((line = lineNumberReader.readLine()) != null) {
                Matcher matcher = compile.matcher(line);
                while (matcher.find()) {
                    IndividualSearchResult individualSearchResult = new IndividualSearchResult();
                    individualSearchResult.setWord(matcher.group());
                    individualSearchResult.setFileName(file.getName());
                    individualSearchResult.setPosition(matcher.start() + 1);
                    individualSearchResult.setLineNumber(lineNumberReader.getLineNumber());
                    wordLocationsPerFile.add(individualSearchResult);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("error in FindWord");
        }
        finally {
            try {
                if(fileReader!=null)
                    fileReader.close();
                if(lineNumberReader!=null)
                    lineNumberReader.close();
            } catch (IOException e) {
                throw new RuntimeException("Error in closing readers");
            }
        }
        return wordLocationsPerFile;
    }
    public SearchResult getLocationOfWord(SearchInput searchInput) throws Exception {
        List<IndividualSearchResult> wordLocations = new ArrayList<>();
        wordLocations = getAllFilesInDirectory(searchInput.getFolderPath(), wordLocations, searchInput.getWord());
        SearchResult searchResult=new SearchResult();
        searchResult.setIndividualSearchResults(wordLocations);
        searchResult.setNumberOfResults(wordLocations.size());
        return searchResult;
    }

    private List<IndividualSearchResult> getAllFilesInDirectory(String folderPath, List<IndividualSearchResult> wordLocationList, String word) throws Exception {
        File folder = new File(folderPath);
        File[] fileList = folder.listFiles();
        if (fileList != null) {
            for (File file : fileList) {
                if (file.isFile()) {
                    wordLocationList.addAll(checkFileForWord(file, word));
                } else if (file.isDirectory()) {
                    wordLocationList=getAllFilesInDirectory(file.getAbsolutePath(), wordLocationList, word);
                }
            }
        }
        return wordLocationList;
    }
}
