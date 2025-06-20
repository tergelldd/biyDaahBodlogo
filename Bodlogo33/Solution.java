package Bodlogo33;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    /*
     * Complete the 'flippingMatrix' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY matrix as parameter.
     */

    public static int flippingMatrix(List<List<Integer>> matrix) {
        int n = matrix.size() / 2;
        int maxSum = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int topLeft = matrix.get(i).get(j);
                int topRight = matrix.get(i).get(2 * n - 1 - j);
                int bottomLeft = matrix.get(2 * n - 1 - i).get(j);
                int bottomRight = matrix.get(2 * n - 1 - i).get(2 * n - 1 - j);

                int max = Math.max(Math.max(topLeft, topRight), Math.max(bottomLeft, bottomRight));
                maxSum += max;
            }
        }

        return maxSum;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        for (int qItr = 0; qItr < q; qItr++) {
            int n = Integer.parseInt(bufferedReader.readLine().trim());

            List<List<Integer>> matrix = new ArrayList<>();

            for (int i = 0; i < 2 * n; i++) {
                String[] matrixRowTempItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                List<Integer> matrixRowItems = new ArrayList<>();

                for (int j = 0; j < 2 * n; j++) {
                    int matrixItem = Integer.parseInt(matrixRowTempItems[j]);
                    matrixRowItems.add(matrixItem);
                }

                matrix.add(matrixRowItems);
            }

            int result = Result.flippingMatrix(matrix);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedReader.close();
        bufferedWriter.close();
    }
}
