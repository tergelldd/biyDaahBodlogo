package Bodlogo23;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    /*
     * Complete the 'swapNodes' function below.
     *
     * The function is expected to return a 2D_INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. 2D_INTEGER_ARRAY indexes
     *  2. INTEGER_ARRAY queries
     */

    public static List<List<Integer>> swapNodes(List<List<Integer>> indexes, List<Integer> queries) {
        int n = indexes.size();
        int[][] tree = new int[n + 1][2];
        for (int i = 0; i < n; i++) {
            tree[i + 1][0] = indexes.get(i).get(0);
            tree[i + 1][1] = indexes.get(i).get(1);
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int k : queries) {
            int depth = 1;
            swapAtDepth(tree, 1, k, depth);
            List<Integer> traversal = new ArrayList<>();
            inorderTraversal(tree, 1, traversal);
            result.add(traversal);
        }
        return result;
}   

private static void swapAtDepth(int[][] tree, int node, int k, int depth) {
    if (node == -1) return;
    if (depth % k == 0) {
        int temp = tree[node][0];
        tree[node][0] = tree[node][1];
        tree[node][1] = temp;
    }

    swapAtDepth(tree, tree[node][0], k, depth + 1);
    swapAtDepth(tree, tree[node][1], k, depth + 1);
}

private static void inorderTraversal(int[][] tree, int node, List<Integer> traversal) {
    if (node == -1) return;

    inorderTraversal(tree, tree[node][0], traversal);
    traversal.add(node);
    inorderTraversal(tree, tree[node][1], traversal);
}


}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> indexes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] indexesRowTempItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            List<Integer> indexesRowItems = new ArrayList<>();

            for (int j = 0; j < 2; j++) {
                int indexesItem = Integer.parseInt(indexesRowTempItems[j]);
                indexesRowItems.add(indexesItem);
            }

            indexes.add(indexesRowItems);
        }

        int queriesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> queries = new ArrayList<>();

        for (int i = 0; i < queriesCount; i++) {
            int queriesItem = Integer.parseInt(bufferedReader.readLine().trim());
            queries.add(queriesItem);
        }

        List<List<Integer>> result = Result.swapNodes(indexes, queries);

        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < result.get(i).size(); j++) {
                bufferedWriter.write(String.valueOf(result.get(i).get(j)));

                if (j != result.get(i).size() - 1) {
                    bufferedWriter.write(" ");
                }
            }

            if (i != result.size() - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
