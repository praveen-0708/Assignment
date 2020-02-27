package com.assignment.wordSearch.WordFinder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindWord {
    public List<WordLocation> getLocationOfWord(String word, String folderPath) throws Exception {
        List<WordLocation> wordLocations = new ArrayList<>();
        wordLocations = getAllFilesInDirectory(folderPath, wordLocations, word);
        return wordLocations;
    }

    public List<WordLocation> getAllFilesInDirectory(String folderPath, List<WordLocation> wordLocationList, String word) throws Exception {
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

    public List<WordLocation> checkFileForWord(File file, String word) throws Exception {
        List<WordLocation> wordLocationsPerFile = new ArrayList<>();
        FileReader fileReader = new FileReader(file);
        LineNumberReader lineNumberReader = new LineNumberReader(fileReader);
        String line;
        while ((line = lineNumberReader.readLine()) != null) {
            Matcher matcher = Pattern.compile(word).matcher(line);
            while (matcher.find()) {
                WordLocation wordLocation = new WordLocation();
                wordLocation.setWord(matcher.group());
                wordLocation.setFileName(file.getName());
                wordLocation.setPosition(matcher.start() + 1);
                wordLocation.setLineNumber(lineNumberReader.getLineNumber());
                wordLocationsPerFile.add(wordLocation);
            }
        }
        return wordLocationsPerFile;
    }
}
