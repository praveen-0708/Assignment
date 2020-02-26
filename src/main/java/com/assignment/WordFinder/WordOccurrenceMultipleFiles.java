package com.assignment.WordFinder;

import com.assignment.WordFinder.WordOccurrence;

import java.io.File;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class WordOccurrenceMultipleFiles {
    public Map<String, Integer> wordOccurrence(String folderPath) throws Exception {
        try {
            File file = new File(folderPath);
            Map<String, Integer> wordCount = new HashMap<>();
            WordOccurrence wordOccurrence = new WordOccurrence();
            File[] listOfFiles = file.listFiles();
            if (listOfFiles != null) {
                for (File eachFile : listOfFiles) {
                    Map<String, Integer> wordCountPerFile = wordOccurrence.wordOccurrence(eachFile.getAbsolutePath());
                    for (Map.Entry<String, Integer> entry : wordCountPerFile.entrySet()) {
                        if (wordCount.containsKey(entry.getKey()))
                            wordCount.put(entry.getKey(), entry.getValue() + wordCount.get(entry.getKey()));
                        else
                            wordCount.put(entry.getKey(), entry.getValue());
                    }
                }
            } else {
                throw new Exception("No files in the directory");
            }
            return wordCount.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                            (e1, e2) -> e1, LinkedHashMap::new));
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
