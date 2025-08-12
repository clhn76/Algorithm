
import java.io.*;
import java.util.*;

public class Solution {
    static int N, M;
    static int max;
    static int[][] map;
    static int[] dr = { -1, 0, 1, 0, 1, 1, -1, -1 }; // 0 ~ 3 (십자가) 4 ~ 7 (크로스)
    static int[] dc = { 0, 1, 0, -1, -1, 1, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new int[N][N];

            // 초기화
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            max = 0;
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    십자가뿌리기(r, c);
                    크로스뿌리기(r, c);
                }
            }
            System.out.printf("#%d %d\n", tc, max);
        }
    }

    static void 크로스뿌리기(int r, int c) {
        int localMax = 0;
        localMax += map[r][c];
        for (int d = 4; d < 8; d++) {
            for (int dist = 1; dist < M; dist++) {
                int nr = r + dr[d] * dist;
                int nc = c + dc[d] * dist;
                if (check(nr, nc)) {
                    localMax += map[nr][nc];
                }
            }
        }
        max = Math.max(localMax, max);
    }

    static void 십자가뿌리기(int r, int c) {
        int localMax = 0;
        localMax += map[r][c];
        for (int d = 0; d < 4; d++) {
            for (int dist = 1; dist < M; dist++) {
                int nr = r + dr[d] * dist;
                int nc = c + dc[d] * dist;
                if (check(nr, nc)) {
                    localMax += map[nr][nc];
                }
            }
        }
        max = Math.max(localMax, max);
    }

    static boolean check(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}
