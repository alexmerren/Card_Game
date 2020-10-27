import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class CardGame {

    //int players = 4;
    ArrayList<Integer> deck = new ArrayList<>();

    public ArrayList<Integer> createDeck(String filename) {


        try {
            //File pack = new File(filename);
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNextInt()) {
                deck.add(scanner.nextInt());
                //System.out.println(deck);
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return deck;
    }

    public void shuffle() {
        for ( int i = deck.length-1; i > 0; i-- ) {
            int rand = (int)(Math.random()*(i+1));
            Card temp = deck[i];
            deck[i] = deck[rand];
            deck[rand] = temp;
        }
    }

    public static void main(String[] args) {
        CardGame example = new CardGame();
        System.out.println(example.createDeck("src/four.txt"));
        //System.out.println(deck);
    }
}