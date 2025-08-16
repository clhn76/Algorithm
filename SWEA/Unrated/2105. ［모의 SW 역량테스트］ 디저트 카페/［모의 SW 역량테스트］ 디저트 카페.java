import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    static int N, maxCnt, startR, startC;
    static int[][] map;
    static boolean[] visited;
    static int[] dr = { 1, 1, -1, -1 };
    static int[] dc = { 1, -1, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());

            map = new int[N][N];
            visited = new boolean[101];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            maxCnt = -1;
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    startR = r;
                    startC = c;
                    visited[map[r][c]] = true;
                    int nr = r + dr[0];
                    int nc = c + dc[0];
                    if (check(nr, nc) && !visited[map[nr][nc]]) {
                        // 첫 시작은 무조건 0방향 직선 출발
                        visited[map[nr][nc]] = true;
                        dfs(nr, nc, 0, 1);
                        visited[map[nr][nc]] = false;
                    }
                    visited[map[r][c]] = false;
                }
            }

            System.out.println("#" + tc + " " + maxCnt);
        }
    }

    private static void dfs(int r, int c, int d, int cnt) {
        for (int nd = d; nd <= d + 1; nd++) { // 직선 또는 우측 꺽기
            if (nd >= 4)
                return;

            int nr = r + dr[nd];
            int nc = c + dc[nd];

            if (nd == 3 && nr == startR && nc == startC) {
                if (cnt + 1 > maxCnt) {
                    maxCnt = cnt + 1;
                }
                continue;
            }

            if (check(nr, nc) && !visited[map[nr][nc]]) {
                visited[map[nr][nc]] = true;
                dfs(nr, nc, nd, cnt + 1);
                visited[map[nr][nc]] = false;
            }
        }

    }

    private static boolean check(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}