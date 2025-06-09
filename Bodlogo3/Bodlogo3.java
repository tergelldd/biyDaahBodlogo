package Bodlogo3;

import java.io.*;

class Result {

    /*
     * Complete the 'encryption' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String encryption(String s) {
    // Write your code here
        int L = s.length();
        int r = (int) Math.floor(Math.sqrt(L));
        int c = (int) Math.ceil(Math.sqrt(L));
        if (r * c < L){
            r += 1;
        }
        StringBuilder encrypted = new StringBuilder();
        for (int col = 0; col < c; col++) {
            for (int row = 0; row < r; row++) {
                int idx = row * c + col;
                if (idx < L) {
                    encrypted.append(s.charAt(idx));
                }
            }
            encrypted.append(' ');
        }
        return encrypted.toString().trim();
    }

}

public class Bodlogo3 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        String result = Result.encryption(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
