package Bodlogo26;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    /*
     * Complete the 'isValid' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String isValid(String s) {
    // Write your code here
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            if (freqMap.containsKey(c)) {
                freqMap.put(c, freqMap.get(c) + 1);
            } else {
                freqMap.put(c, 1);
            }
        }
        
        Map<Integer, Integer> countFreq = new HashMap<>();
        for (int freq : freqMap.values()) {
            if (countFreq.containsKey(freq)) {
                countFreq.put(freq, countFreq.get(freq) + 1);
            } else {
                countFreq.put(freq, 1);
            }
        }
        
        if (countFreq.size() == 1) {
            return "YES";
        } else if (countFreq.size() == 2) {
            List<Integer> keys = new ArrayList<>(countFreq.keySet());
            int freq1 = keys.get(0);
            int freq2 = keys.get(1);
            int count1 = countFreq.get(freq1);
            int count2 = countFreq.get(freq2);

            if ((freq1 == 1 && count1 == 1) || (freq2 == 1 && count2 == 1)) {
                return "YES";
            }
            if ((Math.abs(freq1 - freq2) == 1) && ((count1 == 1 && freq1 > freq2) || (count2 == 1 && freq2 > freq1))) {
                return "YES";
            }
        }
        return "NO";
    }   

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        String result = Result.isValid(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
