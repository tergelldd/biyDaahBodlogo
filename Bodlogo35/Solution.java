package Bodlogo35;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    /*
     * Complete the 'minimumBribes' function below.
     *
     * The function accepts INTEGER_ARRAY q as parameter.
     */

    public static void minimumBribes(List<Integer> q) {
    int totalBribes = 0;

    for (int i = 0; i < q.size(); i++) {
        int current = q.get(i);
        int originalPos = current - 1;
        int currentPos = i;

        if (originalPos - currentPos > 2) {
            System.out.println("Too chaotic");
            return;
        }
        for (int j = Math.max(0, originalPos - 1); j < i; j++) {
            if (q.get(j) > current) {
                totalBribes++;
            }
        }
    }

    System.out.println(totalBribes);
}


}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        for (int tItr = 0; tItr < t; tItr++) {
            int n = Integer.parseInt(bufferedReader.readLine().trim());

            String[] qTemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            List<Integer> q = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                int qItem = Integer.parseInt(qTemp[i]);
                q.add(qItem);
            }

            Result.minimumBribes(q);
        }

        bufferedReader.close();
    }
}
