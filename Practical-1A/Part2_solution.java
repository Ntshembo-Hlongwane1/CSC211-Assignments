import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class Part2_solution {
  public static void main(String[] args) throws FileNotFoundException {
    System.out.println(matchFinder());
  }

  public static String matchFinder() throws FileNotFoundException {
    File file = new File("data2.txt");
    Scanner scan = new Scanner(file);

    Scanner input = new Scanner(System.in);
    System.out.println("Enter in an English word see if you can find a match: ");
    String userInput = input.nextLine();

    while (scan.hasNextLine()) {
      // System.out.println(scan.nextLine());
      Boolean isMatched = wordMatcher(scan.nextLine(), userInput);

      if (isMatched) {
        return "Matched";
      }
    }
    String result = "Unmatched! " + "'" + userInput + "'" + " does not exist";
    scan.close();
    input.close();
    return result;
  }

  public static boolean wordMatcher(String sentence, String userInput) {
    Boolean isMatched = false;

    HashMap<Character, Integer> seenMap = new HashMap<Character, Integer>();
    int counter = 0;
    int wordMatchCount = 0;
    while (counter < sentence.length()) {
      char character = sentence.charAt(counter);
      if (!seenMap.containsKey(character)) {
        seenMap.put(character, 1);
      }
      counter++;
    }

    for (int i = 0; i < userInput.length(); i++) {
      char character = userInput.charAt(i);
      if (seenMap.containsKey(character)) {
        wordMatchCount++;
      }
    }

    isMatched = wordMatchCount == userInput.length() ? true : false;
    return isMatched;
  }

}
