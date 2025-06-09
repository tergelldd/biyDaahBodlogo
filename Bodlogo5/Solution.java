package Bodlogo5;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    /*
     * Complete the 'countLuck' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING_ARRAY matrix
     *  2. INTEGER k
     */
    static int targetX, targetY;
    static int answer = -1;

    public static String countLuck(List<String> matrix, int k) {
        int n = matrix.size();
        int m = matrix.get(0).length();
        int startX = 0, startY = 0;
        for (int i = 0; i < n; i++) {
            String row = matrix.get(i);
            for (int j = 0; j < m; j++) {
                if (row.charAt(j) == 'M') {
                    startX = i;
                    startY = j;
                } else if (row.charAt(j) == '*') {
                    targetX = i;
                    targetY = j;
                }
            }
        }

        boolean[][] visited = new boolean[n][m];
        answer = -1;
        dfs(matrix, visited, startX, startY, 0);

        return (answer == k) ? "Impressed" : "Oops!";
    }

    static void dfs(List<String> matrix, boolean[][] visited, int x, int y, int count) {
        if (x == targetX && y == targetY) {
            answer = count;
            return;
        }

        visited[x][y] = true;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        List<int[]> nextMoves = new ArrayList<>();
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx >= 0 && ny >= 0 && nx < matrix.size() && ny < matrix.get(0).length() &&
                !visited[nx][ny] && matrix.get(nx).charAt(ny) != 'X') {
                nextMoves.add(new int[]{nx, ny});
            }
        }

        for (int[] move : nextMoves) {
            dfs(matrix, visited, move[0], move[1], count + (nextMoves.size() > 1 ? 1 : 0));
        }

        visited[x][y] = false;
    }
}

    
    
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        for (int tItr = 0; tItr < t; tItr++) {
            String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            int n = Integer.parseInt(firstMultipleInput[0]);

            int m = Integer.parseInt(firstMultipleInput[1]);

            List<String> matrix = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                String matrixItem = bufferedReader.readLine();
                matrix.add(matrixItem);
            }

            int k = Integer.parseInt(bufferedReader.readLine().trim());

            String result = Result.countLuck(matrix, k);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedReader.close();
        bufferedWriter.close();
    }
}
