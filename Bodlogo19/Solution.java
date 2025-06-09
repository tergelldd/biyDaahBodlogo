package Bodlogo19;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    /*
     * Complete the 'activityNotifications' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY expenditure
     *  2. INTEGER d
     */

    public static int activityNotifications(List<Integer> expenditure, int d) {
    // Write your code here
        int notifications = 0;
        int[] freq = new int[201];

        for (int i = 0; i < d; i++) {
            freq[expenditure.get(i)]++;
        }

        for (int i = d; i < expenditure.size(); i++) {
            double median = getMedian(freq, d);
            if (expenditure.get(i) >= 2 * median) {
                notifications++;
            }

            freq[expenditure.get(i - d)]--;
            freq[expenditure.get(i)]++;
        }

        return notifications;
    }
    private static double getMedian(int[] freq, int d) {
        int count = 0;
        int median1 = 0;
        int median2 = 0;

        if (d % 2 == 1) {
            int mid = d / 2 + 1;
            for (int i = 0; i < freq.length; i++) {
                count += freq[i];
                if (count >= mid) {
                    return i;
                }
            }
        } 
        else {
            int mid1 = d / 2;
            int mid2 = mid1 + 1;
            for (int i = 0; i < freq.length; i++) {
                count += freq[i];
                if (count >= mid1 && median1 == 0) {
                    median1 = i;
                }
                if (count >= mid2) {
                    median2 = i;
                    break;
                }
            }
            return (median1 + median2) / 2.0;
        }

        return 0;
    }
        
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int d = Integer.parseInt(firstMultipleInput[1]);

        String[] expenditureTemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        List<Integer> expenditure = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int expenditureItem = Integer.parseInt(expenditureTemp[i]);
            expenditure.add(expenditureItem);
        }

        int result = Result.activityNotifications(expenditure, d);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
