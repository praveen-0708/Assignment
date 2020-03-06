package com.assignment.wordSearch.Threads;

import com.assignment.wordSearch.Threads.models.SearchInput;

import java.io.File;
import java.util.concurrent.BlockingQueue;

public class QueueBuilder implements Runnable {
    private BlockingQueue<File> queue;
    private SearchInput searchInput;
    private boolean flag;

    public QueueBuilder(){

    }
    public QueueBuilder(BlockingQueue<File> queue, SearchInput searchInput) {
        this.queue = queue;
        this.searchInput = searchInput;
        this.flag=false;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
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
        getAllFilesInDirectory(new File(this.searchInput.getFolderPath()));
        setFlag(true);
    }

    private void getAllFilesInDirectory(File folder) {
        File[] fileList = folder.listFiles();
        if (fileList != null) {
            for (File file : fileList) {
                if (file.isFile()) {
                    this.queue.add(file);
                } else if (file.isDirectory()) {
                    getAllFilesInDirectory(file);
                }
            }
        }

    }
}
