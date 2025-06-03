package Bodlogo1;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    /*
     * Complete the 'extraLongFactorials' function below.
     *
     * The function accepts INTEGER n as parameter.
     */

    public static void extraLongFactorials(int n) {
    // Write your code here
        if(1 <= n && n <= 100) {
            BigInteger result = BigInteger.ONE;
            for(int i = 2; i <= n; i++) {
                result = result.multiply(BigInteger.valueOf(i));
            }
            System.out.println(result);
        }
    }

}

public class Bodlogo1 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        Result.extraLongFactorials(n);

        bufferedReader.close();
    }
}
