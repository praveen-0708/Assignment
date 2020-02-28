import com.assignment.wordSearch.Threads.WordSearchThread;

public class Main {
    public static void main(String[] args) throws Exception {
        Thread thread=new Thread(new WordSearchThread());
        thread.start();
//        Sample sample=new Sample();
//        sample.testEqual();
    }
}

