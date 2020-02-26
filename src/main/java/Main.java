import com.assignment.WordFinder.FindWord;
import com.assignment.WordFinder.WordLocation;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        String folderPath = "/home/praveenc/Downloads/SampleProject/src/main/webapp/files";
//        com.assignment.WordFinder.WordOccurrenceMultipleFiles wordOccurrenceMultipleFiles=new com.assignment.WordFinder.WordOccurrenceMultipleFiles();
//        Map<String,Integer> wordCount=wordOccurrenceMultipleFiles.wordOccurrence(folderPath);
//        System.out.println("Word Count In All Files");
//        for (Map.Entry<String,Integer> entry :wordCount.entrySet())
//            System.out.println(entry);

        Scanner input=new Scanner(System.in);
        System.out.println("Enter Folder Path:");
        folderPath=input.nextLine();
        System.out.println("Enter word:");
        String word=input.next();
        FindWord findWord = new FindWord();
        List<WordLocation> wordLocations = findWord.getLocationOfWord(word, folderPath);
        for (WordLocation wordLocation : wordLocations)
            System.out.println(wordLocation.toString());
    }
}
