package com.assignment.wordsearch.threads;

import com.assignment.wordsearch.threads.models.SearchInput;
import com.assignment.wordsearch.threads.models.SearchResult;

public interface WordSearch {

    SearchResult findWordInDirectory(SearchInput searchInput);

}
