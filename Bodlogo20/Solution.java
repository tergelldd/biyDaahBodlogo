package Bodlogo20;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    /*
     * Complete the 'lilysHomework' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static int lilysHomework(List<Integer> arr) {
    // Write your code here
        List<Integer> sortedAsc = new ArrayList<>(arr);
        List<Integer> sortedDesc = new ArrayList<>(arr);

        Collections.sort(sortedAsc);
        Collections.sort(sortedDesc, Collections.reverseOrder());

        return Math.min(minSwaps(arr, sortedAsc), minSwaps(arr, sortedDesc));
    }
    private static int minSwaps(List<Integer> original, List<Integer> target) {
    int n = original.size();
    Map<Integer, Integer> indexMap = new HashMap<>();
    for (int i = 0; i < n; i++) {
        indexMap.put(original.get(i), i);
    }

    boolean[] visited = new boolean[n];
    int swaps = 0;

    for (int i = 0; i < n; i++) {
        if (visited[i] || target.get(i).equals(original.get(i))) {
            continue;
        }

        int cycleSize = 0;
        int j = i;

        while (!visited[j]) {
            visited[j] = true;
            int val = target.get(j);
            j = indexMap.get(val);
            cycleSize++;
        }

        if (cycleSize > 0) {
            swaps += (cycleSize - 1);
        }
    }

    return swaps;
}
    
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        String[] arrTemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        List<Integer> arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrTemp[i]);
            arr.add(arrItem);
        }

        int result = Result.lilysHomework(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
