import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Words {
    private String selectedWord;
    private Random random = new Random();
    private char[] letters;

    ArrayList<String> randomWords = new ArrayList<String>();

    public Words() {
        getWordsFromFile("words.txt");
        selectedWord = randomWords.get(random.nextInt(randomWords.size()));
        letters = new char[selectedWord.length()];
    }

    private void getWordsFromFile(String filename) {
        // Read words form file words.txt
        File file = new File(filename);

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                randomWords.add(line);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        StringBuilder text = new StringBuilder();

        for (char letter : letters) {
            text.append(letter == '\u0000' ? '-' : letter);
            text.append(' ');
        }

        return text.toString();
    }

    public boolean guess(char letter) {
        boolean guessedRight = false;

        for (int i = 0; i < selectedWord.length(); i++) {
            if (letter == selectedWord.charAt(i)) {
                letters[i] = letter;
                guessedRight = true;
            }
        }

        return guessedRight;
    }

    public boolean isGuessedRight() {
        for (char letter : letters) {
            if (letter == '\u0000') {
                return false;
            }
        }

        return true;
    }
}
