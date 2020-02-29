package com.assignment.wordSearch.Threads;

import com.assignment.wordSearch.WordFinder.FindWord;
import com.assignment.wordSearch.WordFinder.WordLocation;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class QueueThread {
    Queue<File> queue = new LinkedList<>();
    List<WordLocation> wordLocations = new ArrayList<>();
    public synchronized void add(String folderPath){
        getAllFilesInDirectory(folderPath);
    }
    public synchronized List<WordLocation> delete(String word) throws Exception {
        FindWord findWord=new FindWord();
        while (!queue.isEmpty()){
            wordLocations.addAll(findWord.checkFileForWord(queue.remove(),word));
        }
        return wordLocations;
    }
    private void getAllFilesInDirectory(String folderPath){
        File folder = new File(folderPath);
        File[] fileList = folder.listFiles();
        if (fileList != null) {
            for (File file : fileList) {
                if (file.isFile()) {
                    queue.add(file);
                } else if (file.isDirectory()) {
                    getAllFilesInDirectory(file.getAbsolutePath());
                }
            }
        }

    }
}
