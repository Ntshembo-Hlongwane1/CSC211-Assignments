/*

  * Initials & Surname: NRW HLONGWANE
  * Student No: 3944789
  * Practical: 2

*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;
import java.io.FileWriter;
import java.io.IOException;

public class Part1_solution {

  public static void main(String[] args) throws FileNotFoundException {
    fileReader();
  }

  public static void fileReader() throws FileNotFoundException {

    // Reading file in the same directory
    File file = new File("data1.txt");
    Scanner scan = new Scanner(file);
    HashMap<Character, Boolean> seenMap = new HashMap<Character, Boolean>();
    String sanitizedString = "";
    String finalString = "";

    while (scan.hasNextLine()) {
      String removedSpecialCharacters = scan.nextLine().replaceAll("\\W+", "");
      String removedDigits = removedSpecialCharacters.replaceAll("[\\d]", "");

      for (int i = 0; i < removedDigits.length(); i++) {
        char character = removedDigits.charAt(i);
        if (!seenMap.containsKey(character)) {
          sanitizedString += character;
          seenMap.put(character, true);
        }
      }
      finalString += sanitizedString + "\n";
      sanitizedString = "";
      seenMap.clear();
    }
    try {
      // Writting on file in the same directory
      FileWriter writer = new FileWriter("data2.txt");
      writer.write(finalString);
      writer.close();
    } catch (IOException e) {
      System.out.println("Error occured: ");
      e.printStackTrace();
    }
    scan.close();
  }

}