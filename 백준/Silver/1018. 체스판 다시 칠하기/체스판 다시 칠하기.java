import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] board = new char[N][M];
        for (int r = 0; r < N; r++) {
            board[r] = br.readLine().toCharArray();
        }

        int min = 64;
        for (int r = 0; r <= N - 8; r++) {
            for (int c = 0; c <= M - 8; c++) {
                int curMin = getMinRepaint(board, r, c);
                min = Math.min(curMin, min);
            }
        }

        System.out.println(min);
    }

    static int getMinRepaint(char[][] board, int startRow, int startCol) {
        int minCaseW = 0;
        int minCaseB = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                char cur = board[i + startRow][j + startCol];
                if ((i + j) % 2 == 0) {
                    // W case
                    if (cur != 'W') minCaseW++;
                    // B case
                    if (cur != 'B') minCaseB++;
                } else {
                    // W case
                    if (cur != 'B') minCaseW++;
                    // B case
                    if (cur != 'W') minCaseB++;
                }
            }
        }
        return Math.min(minCaseW, minCaseB);
    }
}