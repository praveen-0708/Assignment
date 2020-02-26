package com.assignment.CharacterFinder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.Map.Entry;

public class CharacterOccurrence {

    public List<Entry<Character, Integer>> characterOccurrence(String filePath) throws Exception {
        try {
            Map<Character, Integer> characterCount = new HashMap<Character, Integer>();
            File file = new File(filePath);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int asciiValue;
            while ((asciiValue = bufferedReader.read()) != -1) {
                char character = (char) asciiValue;
                Integer charCount = characterCount.get(character);
                if (charCount == null) {
                    characterCount.put(character, 0);
                }
                characterCount.put(character, characterCount.get(character) + 1);
            }

            List<Entry<Character, Integer>> entryList = new ArrayList<>(characterCount.entrySet());
            Collections.sort(entryList, new Comparator<Entry<Character, Integer>>() {
                @Override
                public int compare(Entry<Character, Integer> o1, Entry<Character, Integer> o2) {
                    return o2.getValue().compareTo(o1.getValue());
                }
            });
            return entryList;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

}
