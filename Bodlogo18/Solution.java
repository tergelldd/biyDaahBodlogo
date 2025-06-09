package Bodlogo18;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    /*
     * Complete the 'countSort' function below.
     *
     * The function accepts 2D_STRING_ARRAY arr as parameter.
     */

    public static void countSort(List<List<String>> arr) {
        int n = arr.size();
        int maxKey = 100;

        List<List<String>> buckets = new ArrayList<List<String>>();

        for (int i = 0; i < maxKey; i++) {
            buckets.add(new ArrayList<String>());
        }

        for (int i = 0; i < n; i++) {
            int key = Integer.parseInt(arr.get(i).get(0));
            String value = (i < n / 2) ? "-" : arr.get(i).get(1);
            buckets.get(key).add(value);
        }

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < maxKey; i++) {
            for (String val : buckets.get(i)) {
                output.append(val).append(" ");
            }
        }

        System.out.println(output.toString().trim());
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<String>> arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            arr.add(Arrays.asList(bufferedReader.readLine().replaceAll("\\s+$", "").split(" ")));
        }

        Result.countSort(arr);

        bufferedReader.close();
    }
}
