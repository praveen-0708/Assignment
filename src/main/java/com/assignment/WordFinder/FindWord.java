package com.assignment.WordFinder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindWord {
    public List<WordLocation> getLocationOfWord(String word, String folderPath) throws Exception {
        List<File> listOfFiles = new ArrayList<>();
        getAllFilesInDirectory(folderPath, listOfFiles);
        List<WordLocation> wordLocations = new ArrayList<>();
        if (!listOfFiles.isEmpty()) {
            for (File eachFile : listOfFiles) {
                FileReader fileReader = new FileReader(eachFile);
                LineNumberReader lineNumberReader = new LineNumberReader(fileReader);
                String line;
                while ((line = lineNumberReader.readLine()) != null) {
                    if (line.contains(word)) {
                        Matcher matcher = Pattern.compile(word).matcher(line);
                        while (matcher.find()) {
                            WordLocation wordLocation = new WordLocation();
                            wordLocation.setFileName(eachFile.getName());
                            wordLocation.setPosition(matcher.start() + 1);
                            wordLocation.setLineNumber(lineNumberReader.getLineNumber());
                            wordLocations.add(wordLocation);
                        }
                    }
                }
            }
        } else {
            throw new Exception("No files in the directory");
        }
        return wordLocations;
    }

    public void getAllFilesInDirectory(String folderPath, List<File> files) {
        File folder = new File(folderPath);
        File[] fileList = folder.listFiles();
        if (fileList != null) {
            for (File file : fileList) {
                if (file.isFile()) {
                    files.add(file);
                } else if (file.isDirectory()) {
                    getAllFilesInDirectory(file.getAbsolutePath(), files);
                }
            }
        }

    }
}
