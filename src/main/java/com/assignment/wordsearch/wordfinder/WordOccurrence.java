package com.assignment.wordsearch.wordfinder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class WordOccurrence {
    public Map<String, Integer> wordOccurrence(String filePath) throws Exception {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            HashMap<String, Integer> wordCount = new HashMap<String, Integer>();
            File file = new File(filePath);
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
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
        finally {
            try {
                if(fileReader!=null)
                    fileReader.close();
                if(bufferedReader!=null)
                    bufferedReader.close();
            } catch (IOException e) {
                throw new RuntimeException("Error in closing readers");
            }
        }
    }
}
