package Bodlogo17;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    /*
     * Complete the 'almostSorted' function below.
     *
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static void almostSorted(List<Integer> arr) {
    // Write your code here
        int n = arr.size();
        List<Integer> sorted = new ArrayList<>(arr);
        Collections.sort(sorted);

        if (arr.equals(sorted)) {
            System.out.println("yes");
            return;
        }
        List<Integer> diffIndices = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!arr.get(i).equals(sorted.get(i))) {
                diffIndices.add(i);
            }
        }
        if (diffIndices.size() == 2) {
            System.out.println("yes");
            System.out.println("swap " + (diffIndices.get(0) + 1) + " " + (diffIndices.get(1) + 1));
            return;
        }
        int l = diffIndices.get(0);
        int r = diffIndices.get(diffIndices.size() - 1);

        List<Integer> subList = new ArrayList<>(arr.subList(l, r + 1));
        Collections.reverse(subList);

        List<Integer> arrCopy = new ArrayList<>(arr);
        for (int i = l; i <= r; i++) {
            arrCopy.set(i, subList.get(i - l));
        }
        if (arrCopy.equals(sorted)) {
            System.out.println("yes");
            System.out.println("reverse " + (l + 1) + " " + (r + 1));
            return;
        }
        System.out.println("no");
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        String[] arrTemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        List<Integer> arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrTemp[i]);
            arr.add(arrItem);
        }

        Result.almostSorted(arr);

        bufferedReader.close();
    }
}
