
import java.io.*;
import java.util.*;

public class Solution {

    static int N, maxScore;
    static int[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] blockDir = {
        {},
        {2, 3, 1, 0},
        {1, 3, 0, 2},
        {3, 2, 0, 1},
        {2, 0, 3, 1},
        {2, 3, 0, 1}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 탐색 시작 배치
            maxScore = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == 0) {
                        for (int d = 0; d < 4; d++) {
                            simulate(i, j, d);
                        }
                    }
                }
            }
            System.out.printf("#%d %d\n", tc, maxScore);
        }
    }

    private static void simulate(int r, int c, int d) {
        int startR = r;
        int startC = c;
        int score = 0;

        while (true) {
            // 바로 1칸 이동
            r = r + dr[d];
            c = c + dc[d];

            // 탐색 종료 조건
            if (check(r, c) && ((r == startR && c == startC) || map[r][c] == -1)) {
                if (score > maxScore) {
                    maxScore = score;
                }
                return;
            }

            if (!check(r, c)) { // 영역 벗어난 경우
                score++;
                d = (d + 2) % 4;
            } else if (1 <= map[r][c] && map[r][c] <= 5) { // 블록 조건
                score++;
                d = blockDir[map[r][c]][d];
            } else if (6 <= map[r][c] && map[r][c] <= 10) { // 웜홀 조건
                int[] cord = findWormHole(map[r][c], r, c);
                if (cord.length == 2) {
                    r = cord[0];
                    c = cord[1];
                }
            }
        }

    }

    private static int[] findWormHole(int n, int r, int c) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == n && (i != r || j != c)) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }

    private static boolean check(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}
