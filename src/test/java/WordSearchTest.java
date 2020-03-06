import com.assignment.wordSearch.Threads.*;
import com.assignment.wordSearch.Threads.models.SearchInput;
import com.assignment.wordSearch.Threads.models.SearchResult;
import com.assignment.wordSearch.WordFinder.IndividualSearchResult;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;


public class WordSearchTest {

    public SearchResult getExpectedResult(String resultFilePath) {
        Gson gson = new Gson();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(resultFilePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Error in reading the file");
        }
        JsonReader jsonReader = new JsonReader(fileReader);
        SearchResult searchResult=gson.fromJson(jsonReader, SearchResult.class);
        try {
            fileReader.close();
            jsonReader.close();
        } catch (IOException e) {
            throw new RuntimeException("error in closing readers");
        }
        return searchResult;
    }

    @DataProvider(name = "search-data")
    public Object[][] getSearchInput() {
        return new Object[][]{{
                new SearchInput("def", "/home/praveenc/Downloads/SampleProject/src/main/webapp/files"),
                getExpectedResult("/home/praveenc/Desktop/def.json")
        }, {
                new SearchInput("abc", "/home/praveenc/Downloads/SampleProject/src/main/webapp/files"),
                getExpectedResult("/home/praveenc/Desktop/abc.json")
        }, {
                new SearchInput("xyz", "/home/praveenc/Downloads/SampleProject/src/main/webapp/files"),
                getExpectedResult("/home/praveenc/Desktop/xyz.json")
        }};
    }

    @Test(dataProvider = "search-data")
    public void wordSearchTest(SearchInput searchInput, SearchResult searchResultExpected) {
        WordSearch wordSearch = new WordSearchMultiThread();
        SearchResult searchResultActual = wordSearch.findWordInDirectory(searchInput);
        Assert.assertEquals(searchResultExpected.getNumberOfResults(), searchResultActual.getNumberOfResults());
        Assert.assertEquals(searchResultExpected.getIndividualSearchResults(), searchResultActual.getIndividualSearchResults());
    }
}
