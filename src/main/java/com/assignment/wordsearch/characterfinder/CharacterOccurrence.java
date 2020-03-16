package com.assignment.wordsearch.characterfinder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.Map.Entry;

public class CharacterOccurrence {
    public Map<Character, Integer> characterOccurrence(String filePath) throws Exception {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            Map<Character, Integer> characterCount = new HashMap<Character, Integer>();
            File file = new File(filePath);
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
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
            characterCount.clear();
            for (Entry<Character, Integer> entry : entryList)
                characterCount.put(entry.getKey(), entry.getValue());
            return characterCount;
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            fileReader.close();
            bufferedReader.close();
        }
    }

}
