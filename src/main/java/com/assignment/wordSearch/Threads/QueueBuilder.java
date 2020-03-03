package com.assignment.wordSearch.Threads;

import com.assignment.wordSearch.Threads.SearchInput;

import java.io.File;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;

public class QueueBuilder implements Runnable {
    private BlockingQueue<File> queue;
    private SearchInput searchInput;

    public QueueBuilder(BlockingQueue<File> queue, SearchInput searchInput) {
        this.queue = queue;
        this.searchInput = searchInput;
    }

    public BlockingQueue<File> getQueue() {
        return queue;
    }

    public void setQueue(BlockingQueue<File> queue) {
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
        try {
            getAllFilesInDirectory(this.searchInput.getFolderPath());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void getAllFilesInDirectory(String folderPath) throws InterruptedException {
        File folder = new File(folderPath);
        File[] fileList = folder.listFiles();
        if (fileList != null) {
            for (File file : fileList) {
                if (file.isFile()) {
                    this.queue.put(file);
                } else if (file.isDirectory()) {
                    getAllFilesInDirectory(file.getAbsolutePath());
                }
            }
        }

    }
}
