import com.assignment.wordSearch.Threads.*;
import com.assignment.wordSearch.Threads.models.SearchInput;
import com.assignment.wordSearch.Threads.models.SearchResult;
import com.assignment.wordSearch.WordFinder.FindWord;
import com.assignment.wordSearch.WordFinder.IndividualSearchResult;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class WordSearchTest {
    @Test
    public void checkFileForWordTest(){
        List<IndividualSearchResult> wordLocationsExpected=new ArrayList<>();
        wordLocationsExpected.add(new IndividualSearchResult("def","file1.txt",1,5));
        wordLocationsExpected.add(new IndividualSearchResult("def","file1.txt",6,4));
        FindWord findWord=new FindWord();
        String filePath="/home/praveenc/Downloads/SampleProject/src/main/webapp/files/file1.txt";
        List<IndividualSearchResult> wordLocationsActual=findWord.checkFileForWord(new File(filePath),"def");
        Assert.assertEquals(wordLocationsExpected,wordLocationsActual);
    }
    @Test
    public void queueBuilderTest(){
        String folderPath = "/home/praveenc/Downloads/SampleProject/src/main/webapp/files";
        BlockingQueue<File> queueExpected = new LinkedBlockingDeque<>();
        queueExpected.add(new File(folderPath+"/file1.txt"));
        queueExpected.add(new File(folderPath+"/SubFiles1/file3.txt"));
        queueExpected.add(new File(folderPath+"/SubFiles2/file4.txt"));
        queueExpected.add(new File(folderPath+"/file2.txt"));
        SearchInput searchInput=new SearchInput("def",folderPath);
        BlockingQueue<File> queueActual = new LinkedBlockingDeque<>();
        QueueBuilder queueBuilder=new QueueBuilder(queueActual,searchInput);
        queueBuilder.run();
        Assert.assertEquals(queueActual.size(),queueExpected.size());
        while(!queueActual.isEmpty() && !queueExpected.isEmpty())
            Assert.assertEquals(queueExpected.remove(),queueActual.remove());
    }
    @Test
    public void queueProcessorTest(){
        String folderPath = "/home/praveenc/Downloads/SampleProject/src/main/webapp/files";
        BlockingQueue<File> queue = new LinkedBlockingDeque<>();
        queue.add(new File(folderPath+"/file1.txt"));
        queue.add(new File(folderPath+"/SubFiles1/file3.txt"));
        queue.add(new File(folderPath+"/SubFiles2/file4.txt"));
        queue.add(new File(folderPath+"/file2.txt"));
        SearchInput searchInput=new SearchInput("def",folderPath);
        QueueProcessor queueProcessor=new QueueProcessor(queue,searchInput);
        queueProcessor.run();
        List<IndividualSearchResult> wordLocationsExpected=new ArrayList<>();
        wordLocationsExpected.add(new IndividualSearchResult("def","file1.txt",1,5));
        wordLocationsExpected.add(new IndividualSearchResult("def","file1.txt",6,4));
        wordLocationsExpected.add(new IndividualSearchResult("def","file2.txt",1,1));
        wordLocationsExpected.add(new IndividualSearchResult("def","file3.txt",1,1));
        wordLocationsExpected.add(new IndividualSearchResult("def","file4.txt",1,1));
        wordLocationsExpected.add(new IndividualSearchResult("def","file4.txt",4,13));
        wordLocationsExpected.add(new IndividualSearchResult("def","file4.txt",4,28));
        wordLocationsExpected.add(new IndividualSearchResult("def","file4.txt",5,1));
        List<IndividualSearchResult> wordLocationsActual=queueProcessor.getSearchResult().getIndividualSearchResults();
        Collections.sort(wordLocationsActual);
        Assert.assertEquals(wordLocationsExpected,wordLocationsActual);
    }
    @Test
    public void singleThreadTest(){
        String folderPath = "/home/praveenc/Downloads/SampleProject/src/main/webapp/files";
        SearchInput searchInput=new SearchInput("def",folderPath);
        List<IndividualSearchResult> wordLocationsExpected=new ArrayList<>();
        wordLocationsExpected.add(new IndividualSearchResult("def","file1.txt",1,5));
        wordLocationsExpected.add(new IndividualSearchResult("def","file1.txt",6,4));
        wordLocationsExpected.add(new IndividualSearchResult("def","file2.txt",1,1));
        wordLocationsExpected.add(new IndividualSearchResult("def","file3.txt",1,1));
        wordLocationsExpected.add(new IndividualSearchResult("def","file4.txt",1,1));
        wordLocationsExpected.add(new IndividualSearchResult("def","file4.txt",4,13));
        wordLocationsExpected.add(new IndividualSearchResult("def","file4.txt",4,28));
        wordLocationsExpected.add(new IndividualSearchResult("def","file4.txt",5,1));
        WordSearch wordSearch = new WordSearchSingleThread();
        SearchResult searchResult=wordSearch.findWordInDirectory(searchInput);
        List<IndividualSearchResult> wordLocationsActual=searchResult.getIndividualSearchResults();
        Assert.assertEquals(wordLocationsExpected,wordLocationsActual);
        Assert.assertEquals(8,searchResult.getNumberOfResults());
    }
    @Test
    public void multiThreadTest(){
        String folderPath = "/home/praveenc/Downloads/SampleProject/src/main/webapp/files";
        SearchInput searchInput=new SearchInput("def",folderPath);
        List<IndividualSearchResult> wordLocationsExpected=new ArrayList<>();
        wordLocationsExpected.add(new IndividualSearchResult("def","file1.txt",1,5));
        wordLocationsExpected.add(new IndividualSearchResult("def","file1.txt",6,4));
        wordLocationsExpected.add(new IndividualSearchResult("def","file2.txt",1,1));
        wordLocationsExpected.add(new IndividualSearchResult("def","file3.txt",1,1));
        wordLocationsExpected.add(new IndividualSearchResult("def","file4.txt",1,1));
        wordLocationsExpected.add(new IndividualSearchResult("def","file4.txt",4,13));
        wordLocationsExpected.add(new IndividualSearchResult("def","file4.txt",4,28));
        wordLocationsExpected.add(new IndividualSearchResult("def","file4.txt",5,1));
        WordSearch wordSearch = new WordSearchMultiThread();
        SearchResult searchResult=wordSearch.findWordInDirectory(searchInput);
        List<IndividualSearchResult> wordLocationsActual=searchResult.getIndividualSearchResults();
        Assert.assertEquals(wordLocationsExpected,wordLocationsActual);
        Assert.assertEquals(8,searchResult.getNumberOfResults());
    }

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
