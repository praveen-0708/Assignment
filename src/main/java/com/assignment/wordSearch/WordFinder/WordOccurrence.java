package com.assignment.wordSearch.WordFinder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class WordOccurrence {
    public Map<String, Integer> wordOccurrence(String filePath) throws Exception {
        try {
            HashMap<String, Integer> wordCount = new HashMap<String, Integer>();
            File file = new File(filePath);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] eachLine = line.split(" ");
                for (String word : eachLine) {
                    wordCount.putIfAbsent(word, 0);
                    wordCount.put(word, wordCount.get(word) + 1);
                }
            }

            return wordCount.entrySet().stream().sorted(Entry.comparingByValue(Comparator.reverseOrder()))
                    .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
                            (e1, e2) -> e1, LinkedHashMap::new));
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
