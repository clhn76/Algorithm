
import java.io.*;
import java.util.*;

public class Solution {

    static int H, W, N, minCount;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            int[][] map = new int[H][W];
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            minCount = Integer.MAX_VALUE;
            dfs(map, 1);
            System.out.printf("#%d %d\n", tc, minCount);
        }
    }

    private static void dfs(int[][] map, int cnt) {
        if (cnt > N) {
            int reamin = countRemain(map);
            minCount = Math.min(reamin, minCount);
            return;
        }

        for (int i = 0; i < W; i++) {
            // 현재 상태 복사
            int[][] copyMap = copy(map);

            // i에 벽돌 떨구기
            shoot(copyMap, i);

            // 중력 적용해서 정리
            gravity(copyMap);

            // 업데이트된 상태 다음 cnt에 전달
            dfs(copyMap, cnt + 1);
        }

    }

    private static int countRemain(int[][] map) {
        int cnt = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] != 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static void gravity(int[][] map) {
        for (int c = 0; c < W; c++) {
            for (int r = H - 1; r >= 0; r--) {
                if (map[r][c] == 0) {
                    for (int i = r - 1; i >= 0; i--) {
                        if (map[i][c] != 0) {
                            map[r][c] = map[i][c];
                            map[i][c] = 0;
                            break;
                        }
                    }
                }
            }
        }
    }

    private static void shoot(int[][] copyMap, int i) {
        Queue<int[]> queue = new ArrayDeque<>();
        for (int r = 0; r < H; r++) {
            if (copyMap[r][i] != 0) {
                queue.offer(new int[]{r, i, copyMap[r][i]});
                break;
            }
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];
            int pw = cur[2];
            copyMap[r][c] = 0;

            for (int p = 1; p < pw; p++) {
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d] * p;
                    int nc = c + dc[d] * p;
                    if (check(nr, nc) && copyMap[nr][nc] != 0) {
                        queue.offer(new int[]{nr, nc, copyMap[nr][nc]});
                    }
                }
            }
        }
    }

    private static boolean check(int r, int c) {
        return r >= 0 && r < H && c >= 0 && c < W;
    }

    private static int[][] copy(int[][] map) {
        int[][] temp = new int[H][W];
        for (int i = 0; i < H; i++) {
            temp[i] = map[i].clone();
        }
        return temp;
    }

}
