package Bodlogo27;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    /*
     * Complete the 'highestValuePalindrome' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING s
     *  2. INTEGER n
     *  3. INTEGER k
     */

    public static String highestValuePalindrome(String s, int n, int k) {
    // Write your code here
        char[] arr = s.toCharArray();
    boolean[] changed = new boolean[n];
    int minChanges = 0;
    for (int i = 0; i < n / 2; i++) {
        int j = n - 1 - i;
        if (arr[i] != arr[j]) {
            char maxChar = (char) Math.max(arr[i], arr[j]);
            arr[i] = maxChar;
            arr[j] = maxChar;
            changed[i] = true;
            changed[j] = true;
            minChanges++;
        }
    }

    if (minChanges > k) return "-1";
    int remainingChanges = k - minChanges;
    for (int i = 0; i < n / 2 && remainingChanges > 0; i++) {
        int j = n - 1 - i;

        if (arr[i] != '9') {
            if (changed[i] || changed[j]) {
                if (remainingChanges >= 1) {
                    arr[i] = '9';
                    arr[j] = '9';
                    remainingChanges--;
                }
            } 
            else {
                if (remainingChanges >= 2) {
                    arr[i] = '9';
                    arr[j] = '9';
                    remainingChanges -= 2;
                }
            }
        }
    }

    if (n % 2 == 1 && remainingChanges > 0 && arr[n / 2] != '9') {
        arr[n / 2] = '9';
        remainingChanges--;
    }

    return new String(arr);
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        String s = bufferedReader.readLine();

        String result = Result.highestValuePalindrome(s, n, k);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
