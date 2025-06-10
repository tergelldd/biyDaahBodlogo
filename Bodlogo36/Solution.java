package Bodlogo36;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    public static BigInteger fibonacciModified(int t1, int t2, int n) {
        BigInteger a = BigInteger.valueOf(t1);
        BigInteger b = BigInteger.valueOf(t2);
        BigInteger c = BigInteger.ZERO;

        for (int i = 3; i <= n; i++) {
            c = a.add(b.multiply(b));
            a = b;
            b = c;
        }

        return c;
    }
}


public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int t1 = Integer.parseInt(firstMultipleInput[0]);
        int t2 = Integer.parseInt(firstMultipleInput[1]);
        int n = Integer.parseInt(firstMultipleInput[2]);

        BigInteger result = Result.fibonacciModified(t1, t2, n);

        bufferedWriter.write(result.toString());
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

