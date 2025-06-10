package Bodlogo28;

import java.io.*;
import java.util.*;

public class Solution {

    private static final int MOD = 1000000007;
    private static final int MAX_N = 100000;
    private static long[] fact = new long[MAX_N + 1];
    private static long[] invFact = new long[MAX_N + 1];
    private static int[][] prefixFreq = new int[MAX_N + 1][26];

    public static void initialize(String s) {
        int n = s.length();
        fact[0] = 1;
        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }

        invFact[n] = modInverse(fact[n], MOD);
        for (int i = n - 1; i >= 0; i--) {
            invFact[i] = invFact[i + 1] * (i + 1) % MOD;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 26; j++) {
                prefixFreq[i + 1][j] = prefixFreq[i][j];
            }
            prefixFreq[i + 1][s.charAt(i) - 'a']++;
        }
    }

    public static int answerQuery(int l, int r) {
        int[] freq = new int[26];
        for (int i = 0; i < 26; i++) {
            freq[i] = prefixFreq[r][i] - prefixFreq[l - 1][i];
        }

        int halfLength = 0;
        int oddCount = 0;
        long result = 1;

        for (int f : freq) {
            if (f % 2 == 1) {
                oddCount++;
            }
            halfLength += f / 2;
            if (f > 1) {
                result = result * invFact[f / 2] % MOD;
            }
        }

        result = result * fact[halfLength] % MOD;
        if (oddCount > 0) {
            result = result * oddCount % MOD;
        }

        return (int) result;
    }

    private static long modInverse(long a, int mod) {
        return pow(a, mod - 2, mod);
    }

    private static long pow(long base, long exp, int mod) {
        long result = 1;
        while (exp > 0) {
            if (exp % 2 == 1) {
                result = result * base % mod;
            }
            base = base * base % mod;
            exp /= 2;
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        initialize(s);

        int q = Integer.parseInt(reader.readLine().trim());
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < q; i++) {
            String[] parts = reader.readLine().split(" ");
            int l = Integer.parseInt(parts[0]);
            int r = Integer.parseInt(parts[1]);
            writer.write(answerQuery(l, r) + "\n");
        }

        reader.close();
        writer.close();
    }
}