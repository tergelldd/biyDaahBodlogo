package Bodlogo14;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    /*
     * Complete the 'bomberMan' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. STRING_ARRAY grid
     */

    public static List<String> bomberMan(int n, List<String> grid) {
    // Write your code here
        if (n == 1){
            return grid;
        }
        int r = grid.size();
        int c = grid.get(0).length();
        char[][] original = new char[r][c];
        for (int i = 0; i < r; i++) {
            original[i] = grid.get(i).toCharArray();
        }

        char[][] full = new char[r][c];
        for (int i = 0; i < r; i++) {
            Arrays.fill(full[i], 'O');
        }
        char[][] firstExplosion = explode(original);
        char[][] secondExplosion = explode(firstExplosion);

        if (n % 2 == 0) {
            return toStringList(full);
        } 
        else if (n % 4 == 3) {
            return toStringList(firstExplosion);
        } 
        else {
            return toStringList(secondExplosion);
        }
    }

    private static char[][] explode(char[][] grid) {
        int r = grid.length;
        int c = grid[0].length;
        char[][] result = new char[r][c];
        for (int i = 0; i < r; i++) {
            Arrays.fill(result[i], 'O');
        }

        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 'O') {
                    result[i][j] = '.';
                    for (int d = 0; d < 4; d++) {
                        int ni = i + dx[d];
                        int nj = j + dy[d];
                        if (ni >= 0 && ni < r && nj >= 0 && nj < c) {
                            result[ni][nj] = '.';
                        }
                    }
                }
            }
        }
        return result;
    }
    
    private static List<String> toStringList(char[][] grid) {
        List<String> result = new ArrayList<>();
        for (char[] row : grid) {
            result.add(new String(row));
        }
        return result;
    }
}


public class Bodlogo14 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int r = Integer.parseInt(firstMultipleInput[0]);

        int c = Integer.parseInt(firstMultipleInput[1]);

        int n = Integer.parseInt(firstMultipleInput[2]);

        List<String> grid = new ArrayList<>();

        for (int i = 0; i < r; i++) {
            String gridItem = bufferedReader.readLine();
            grid.add(gridItem);
        }

        List<String> result = Result.bomberMan(n, grid);

        for (int i = 0; i < result.size(); i++) {
            bufferedWriter.write(result.get(i));

            if (i != result.size() - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}