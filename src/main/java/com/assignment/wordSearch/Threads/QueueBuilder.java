package com.assignment.wordSearch.Threads;

import com.assignment.wordSearch.Threads.SearchInput;

import java.io.File;
import java.util.Queue;

public class QueueBuilder implements Runnable {
    private Queue<File> queue;
    private SearchInput searchInput;

    public QueueBuilder(Queue<File> queue, SearchInput searchInput) {
        this.queue = queue;
        this.searchInput = searchInput;
    }

    public Queue<File> getQueue() {
        return queue;
    }

    public void setQueue(Queue<File> queue) {
        this.queue = queue;
    }

    public SearchInput getSearchInput() {
        return searchInput;
    }

    public void setSearchInput(SearchInput searchInput) {
        this.searchInput = searchInput;
    }

    @Override
    public void run() {
        getAllFilesInDirectory(this.searchInput.getFolderPath());
    }

    private void getAllFilesInDirectory(String folderPath) {
        File folder = new File(folderPath);
        File[] fileList = folder.listFiles();
        if (fileList != null) {
            for (File file : fileList) {
                if (file.isFile()) {
                    this.queue.add(file);
                } else if (file.isDirectory()) {
                    getAllFilesInDirectory(file.getAbsolutePath());
                }
            }
        }

    }
}
