import java.util.Scanner;
import java.util.Arrays;

public class customSequence_3944789 {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    System.out.println("------------------------------------------------------------\n");
    System.out.println("Welcom to CSC211 Custom Sequence");
    System.out.println("------------------------------------------------------------\n");
    System.out.println("Enter the length of the sequence: ");
    int length = scan.nextInt();
    int[] results = new int[length];

    for (int i = 0; i < length; i++) {
      results[i] = sequence(i);
    }
    System.out.println(Arrays.toString(results));
  }

  public static int sequence(int number) {
    if (number == 0) {
      return 4;
    } else if (number == 1) {
      return 2;
    } else {
      return sequence(number - 1) * sequence(number - 2) - 4;
    }
  }
}
