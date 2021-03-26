import java.util.Scanner;
import java.util.ArrayList;

public class contactingTracing_3944789 {
  public static void main(String[] args) {

    int[][] data = { { 2, 4 }, { 3, 6 }, { 8, 9 }, { 10, 11 }, { 7, 11 } };
    findUniquePairs(data);

  }

  public static void findUniquePairs(int[][] data) {

    for (int i = 0; i < data.length; i++) {
      for (int j = i + 1; j < data.length; j++) {

        if (((data[i][0] >= data[j][0] && data[i][0] <= data[j][1])
            || (data[j][0] > data[i][0] && data[j][0] < data[i][1]))) {
          System.out.println((i + 1) + "," + (j + 1));
        }

      }
    }

  }
}