package com.assignment.wordsearch.threads;

import com.assignment.wordsearch.threads.models.SearchInput;

import java.io.File;
import java.util.concurrent.BlockingQueue;

public class QueueBuilder implements Runnable {
    private BlockingQueue<File> queue;
    private SearchInput searchInput;
    private boolean fileBuilderCompleted;

    public QueueBuilder(){

    }
    public QueueBuilder(BlockingQueue<File> queue, SearchInput searchInput) {
        this.queue = queue;
        this.searchInput = searchInput;
        this.fileBuilderCompleted =false;
    }

    public boolean isFileBuilderCompleted() {
        return fileBuilderCompleted;
    }

    public void setFileBuilderCompleted(boolean fileBuilderCompleted) {
        this.fileBuilderCompleted = fileBuilderCompleted;
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
        populateAllFilesInDirectory(new File(this.searchInput.getFolderPath()));
        this.fileBuilderCompleted = true;
    }

    private void populateAllFilesInDirectory(File folder) {
        File[] fileList = folder.listFiles();
        if (fileList != null) {
            for (File file : fileList) {
                if (file.isFile()) {
                    this.queue.add(file);
                } else if (file.isDirectory()) {
                    populateAllFilesInDirectory(file);
                }
            }
        }

    }
}
