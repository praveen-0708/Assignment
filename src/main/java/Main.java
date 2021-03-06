import com.assignment.wordsearch.threads.WordSearchSingleThread;
import com.assignment.wordsearch.threads.models.SearchInput;
import com.assignment.wordsearch.threads.models.SearchResult;
import com.assignment.wordsearch.threads.WordSearch;
import com.assignment.wordsearch.wordfinder.IndividualSearchResult;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static Logger logger = Logger.getLogger(WordSearchSingleThread.class);

    public static void main(String[] args) throws IOException {

        String folderPath = "/home/praveenc/Downloads/SampleProject/src/main/webapp/files";
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Folder Path:");
/*        folderPath = input.nextLine();
        System.out.println("Enter word:");*/
        String word = input.nextLine();
        SearchInput searchInput = new SearchInput();
        searchInput.setFolderPath(folderPath);
        searchInput.setWord(word);
        WordSearch wordSearch = new WordSearchSingleThread();
        SearchResult searchResult = wordSearch.findWordInDirectory(searchInput);
        if (searchResult.getIndividualSearchResults().isEmpty()) {
            //System.out.println("Word Not Found!!");
            logger.info("Word Not Found!!");
        } else {
            for (IndividualSearchResult individualSearchResult : searchResult.getIndividualSearchResults()) {
                //System.out.println(wordLocation.toString());
                logger.info(individualSearchResult.toString());
            }
        }
    }
}


