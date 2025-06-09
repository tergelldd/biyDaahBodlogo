package Bodlogo15;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    /*
     * Complete the 'twoPluses' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING_ARRAY grid as parameter.
     */

    public static int twoPluses(List<String> grid) {
    // Write your code here
        int n = grid.size();
        int m = grid.get(0).length();

        char[][] g = new char[n][m];
        for (int i = 0; i < n; i++) {
            g[i] = grid.get(i).toCharArray();
        }
        List<int[]> pluses = new ArrayList<>();
    
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int len = 0;
                while (isValid(g, i, j, len)) {
                    pluses.add(new int[]{i, j, len});
                    len++;
                }
            }
        }
        int maxArea = 0;
        for (int i = 0; i < pluses.size(); i++) {
            for (int j = i + 1; j < pluses.size(); j++) {
                int[] p1 = pluses.get(i);
                int[] p2 = pluses.get(j);
                Set<String> occupied = getCells(p1);
                Set<String> other = getCells(p2);
                boolean overlap = false;
                for (String cell : other) {
                    if (occupied.contains(cell)) {
                        overlap = true;
                        break;
                    }
                }
                if (!overlap) {
                    int area1 = p1[2] * 4 + 1;
                    int area2 = p2[2] * 4 + 1;
                    maxArea = Math.max(maxArea, area1 * area2);
                }
            }
        }

        return maxArea;
    }

    private static boolean isValid(char[][] g, int x, int y, int len) {
        int n = g.length, m = g[0].length;
        if (x - len < 0 || x + len >= n || y - len < 0 || y + len >= m) {
            return false;
        }
        for (int i = 0; i <= len; i++) {
            if (g[x + i][y] != 'G' || g[x - i][y] != 'G' || g[x][y + i] != 'G' || g[x][y - i] != 'G') {
                return false;
            }
        }
        return true;
    }

    private static Set<String> getCells(int[] p) {
        int x = p[0], y = p[1], len = p[2];
        Set<String> s = new HashSet<>();
        for (int i = 0; i <= len; i++) {
            s.add((x + i) + "," + y);
            s.add((x - i) + "," + y);
            s.add(x + "," + (y + i));
            s.add(x + "," + (y - i));
        }
        return s;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        List<String> grid = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String gridItem = bufferedReader.readLine();
            grid.add(gridItem);
        }

        int result = Result.twoPluses(grid);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}