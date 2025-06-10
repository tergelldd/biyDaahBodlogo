package Bodlogo25;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    public static List<Integer> waiter(List<Integer> number, int q) {
        // Handle edge cases
        if (number == null || number.isEmpty() || q < 0) {
            return new ArrayList<>();
        }
        
        List<Integer> answer = new ArrayList<>();
        Stack<Integer> currentStack = new Stack<>();
        // Push plates in original order
        for (int plate : number) {
            currentStack.push(plate);
        }
        
        List<Integer> primes = generatePrimes(q);
        
        // Perform q iterations
        for (int i = 0; i < q; i++) {
            int prime = primes.get(i);
            Stack<Integer> divisibleStack = new Stack<>();
            Stack<Integer> nonDivisibleStack = new Stack<>();
            
            // Divide plates based on divisibility
            while (!currentStack.isEmpty()) {
                int plate = currentStack.pop();
                if (plate % prime == 0) {
                    divisibleStack.push(plate);
                } else {
                    nonDivisibleStack.push(plate);
                }
            }
            
            // Add divisible plates to answer
            while (!divisibleStack.isEmpty()) {
                answer.add(divisibleStack.pop());
            }
            
            // Use non-divisible plates for next iteration
            currentStack = nonDivisibleStack;
        }
        
        // Add remaining plates to answer
        while (!currentStack.isEmpty()) {
            answer.add(currentStack.pop());
        }
        
        return answer;
    }

    private static List<Integer> generatePrimes(int q) {
        if (q <= 0) return new ArrayList<>();
        
        List<Integer> primes = new ArrayList<>();
        // Use Sieve of Eratosthenes for efficiency
        int max = 10000; // Reasonable upper bound for typical constraints
        boolean[] isNotPrime = new boolean[max + 1];
        isNotPrime[0] = isNotPrime[1] = true;
        
        for (int i = 2; i <= max && primes.size() < q; i++) {
            if (!isNotPrime[i]) {
                primes.add(i);
                for (int j = i * i; j <= max; j += i) {
                    isNotPrime[j] = true;
                }
            }
        }
        return primes;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);
        int q = Integer.parseInt(firstMultipleInput[1]);

        String[] numberTemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        List<Integer> number = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int numberItem = Integer.parseInt(numberTemp[i]);
            number.add(numberItem);
        }

        List<Integer> result = Result.waiter(number, q);

        for (int i = 0; i < result.size(); i++) {
            bufferedWriter.write(String.valueOf(result.get(i)));
            if (i != result.size() - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();
        bufferedReader.close();
        bufferedWriter.close();
    }
}