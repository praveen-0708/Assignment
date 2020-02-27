import com.assignment.wordSearch.CharacterFinder.CharacterOccurrence;
import com.assignment.wordSearch.WordFinder.FindWord;
import com.assignment.wordSearch.WordFinder.WordLocation;
import org.apache.log4j.Logger;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Logger logger= Logger.getLogger(Main.class);
        String folderPath = "/home/praveenc/Downloads/SampleProject/src/main/webapp/files";
        Scanner input=new Scanner(System.in);
        /*System.out.println("Enter Folder Path:");
        folderPath=input.nextLine();*/
        System.out.println("Enter word:");
        String word=input.nextLine();
        FindWord findWord = new FindWord();
        List<WordLocation> wordLocations = findWord.getLocationOfWord(word, folderPath);
        if(wordLocations.isEmpty()){
            System.out.println("Word Not Found!!");
            logger.info("Word Not Found!!");
        }
        else{
            for (WordLocation wordLocation : wordLocations){
                System.out.println(wordLocation.toString());
                logger.info(wordLocation.toString());
            }
        }

//        Sample sample=new Sample();
//        sample.testEqual();
    }
}

