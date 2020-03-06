import com.assignment.wordSearch.Threads.WordSearchSingleThread;
import com.assignment.wordSearch.Threads.models.SearchInput;
import com.assignment.wordSearch.Threads.models.SearchResult;
import com.assignment.wordSearch.Threads.WordSearch;
import com.assignment.wordSearch.Threads.WordSearchMultiThread;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String folderPath = "/home/praveenc/Downloads/SampleProject/src/main/webapp/files";
        Scanner input = new Scanner(System.in);
/*          System.out.println("Enter Folder Path:");
            folderPath = input.nextLine();*/
        System.out.println("Enter word:");
        String word = input.nextLine();
        SearchInput searchInput = new SearchInput();
        searchInput.setFolderPath(folderPath);
        searchInput.setWord(word);
        WordSearch wordSearch = new WordSearchSingleThread();
        SearchResult searchResult=wordSearch.findWordInDirectory(searchInput);
    }
}
//todo arraylist

