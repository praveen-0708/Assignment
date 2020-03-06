import com.assignment.wordSearch.Threads.*;
import com.assignment.wordSearch.Threads.models.SearchInput;
import com.assignment.wordSearch.Threads.models.SearchResult;
import com.assignment.wordSearch.WordFinder.IndividualSearchResult;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.junit.Assert;
import org.junit.Test;
import java.io.*;
import java.util.Arrays;
import java.util.List;

public class WordSearchTest {

    @Test
    public void wordSearchTest() throws IOException {
        String folderPath = "/home/praveenc/Downloads/SampleProject/src/main/webapp/files";
        SearchInput searchInput=new SearchInput("def",folderPath);
        Gson gson=new Gson();
        FileReader fileReader= null;
        try {
            fileReader = new FileReader("/home/praveenc/Desktop/result.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Error in reading the file");
        }
        JsonReader jsonReader=new JsonReader(fileReader);
        IndividualSearchResult[] individualSearchResults=gson.fromJson(jsonReader,IndividualSearchResult[].class);
        List<IndividualSearchResult> expectedResult= Arrays.asList(individualSearchResults);

        WordSearch wordSearch=new WordSearchMultiThread();
        SearchResult searchResult=wordSearch.findWordInDirectory(searchInput);
        Assert.assertEquals(expectedResult,searchResult.getIndividualSearchResults());
        fileReader.close();
        jsonReader.close();
    }
}
