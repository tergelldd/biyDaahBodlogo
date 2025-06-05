package Bodlogo2;

import java.io.*;
class Result {

    /*
     * Complete the 'timeInWords' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. INTEGER h
     *  2. INTEGER m
     */

    public static String timeInWords(int h, int m) {
    String[] numbers = {
        "", "one", "two", "three", "four", "five", "six", "seven", "eight",
        "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen",
        "sixteen", "seventeen", "eighteen", "nineteen", "twenty", "twenty one",
        "twenty two", "twenty three", "twenty four", "twenty five", "twenty six",
        "twenty seven", "twenty eight", "twenty nine"
    };

    if (1 <= h && h <= 12 && 0 <= m && m < 60) {
        if (m == 0) {
            return numbers[h] + " o' clock";
        } else if (m == 15) {
            return "quarter past " + numbers[h];
        } else if (m == 30) {
            return "half past " + numbers[h];
        } else if (m == 45) {
            int nextHour;
            if (h == 12) {
                nextHour = 1;
            }
            else {
                nextHour = h + 1;
            }
            return "quarter to " + numbers[nextHour];
        } else if (m < 30) {
            String minuteWord;
            if (m == 1){
                minuteWord = " minute";
            }
            else {
                minuteWord = " minutes";
            }
            return numbers[m] + minuteWord + " past " + numbers[h];
        } else {
            int minutesTo = 60 - m;
            int nextHour;
            if (h == 12) {
                nextHour = 1;
            }
            else {
                nextHour = h + 1;
            }
            String minuteWord;
            if(minutesTo == 1){
                minuteWord = " minute";
            }
            else {
                minuteWord = " minutes";
            }
            return numbers[minutesTo] + minuteWord + " to " + numbers[nextHour];
        }
    }
    return "wrong input";
}
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int h = Integer.parseInt(bufferedReader.readLine().trim());

        int m = Integer.parseInt(bufferedReader.readLine().trim());

        String result = Result.timeInWords(h, m);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}