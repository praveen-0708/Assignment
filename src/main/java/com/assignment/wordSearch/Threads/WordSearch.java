package com.assignment.wordSearch.Threads;

import com.assignment.wordSearch.Threads.models.SearchInput;
import com.assignment.wordSearch.Threads.models.SearchResult;

public interface WordSearch {
    public SearchResult findWordInDirectory(SearchInput searchInput);
}
