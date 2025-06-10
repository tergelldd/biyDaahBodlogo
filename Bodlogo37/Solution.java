package Bodlogo37;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    /*
     * Complete the 'counterGame' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts LONG_INTEGER n as parameter.
     */

    public static String counterGame(long n) {
    // Write your code here
        boolean louiseTurn = true;

        while (n > 1) {
            if (isPowerOfTwo(n)) {
                n = n / 2;
            } 
            else {
                long highestPower = highestPowerOfTwoLessThan(n);
                n = n - highestPower;
            }
            louiseTurn = !louiseTurn;
        }
        return louiseTurn ? "Richard" : "Louise";
    }
    private static boolean isPowerOfTwo(long n) {
        return (n & (n - 1)) == 0;
    }
    private static long highestPowerOfTwoLessThan(long n) {
        long power = 1;
        while (power <= n) {
            power <<= 1;
        }
        return power >> 1;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        for (int tItr = 0; tItr < t; tItr++) {
            long n = Long.parseLong(bufferedReader.readLine().trim());

            String result = Result.counterGame(n);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedReader.close();
        bufferedWriter.close();
    }
}
