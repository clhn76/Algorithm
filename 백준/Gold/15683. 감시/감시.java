
import java.io.*;
import java.util.*;

public class Main {

    static int N, M, answer;
    static int[][] map;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static int[][][] directions = {
        {},
        {{0}, {1}, {2}, {3}},
        {{0, 2}, {1, 3}},
        {{3, 0}, {0, 1}, {1, 2}, {2, 3}},
        {{2, 3, 0}, {3, 0, 1}, {0, 1, 2}, {1, 2, 3}},
        {{0, 1, 2, 3}}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        List<int[]> cameras = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int n = Integer.parseInt(st.nextToken());
                map[i][j] = n;
                if (n >= 1 && n <= 5) {
                    cameras.add(new int[]{i, j, n});
                }
            }
        }

        answer = Integer.MAX_VALUE;
        dfs(map, cameras, 0);
        System.out.println(answer);
    }

    private static void dfs(int[][] map, List<int[]> cameras, int idx) {
        if (idx >= cameras.size()) {
            answer = Math.min(result(map), answer);
            return;
        }

        int[] cur = cameras.get(idx);

        for (int[] dirs : directions[cur[2]]) {
            int[][] copyMap = copy(map);
            for (int d : dirs) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                while (check(nr, nc) && map[nr][nc] >= -1 && map[nr][nc] <= 5) {
                    if (copyMap[nr][nc] == 0) {
                        copyMap[nr][nc] = -1;
                    }

                    nr += dr[d];
                    nc += dc[d];
                }
            }
            dfs(copyMap, cameras, idx + 1);
        }
    }

    private static int result(int[][] map) {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static boolean check(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    private static int[][] copy(int[][] map) {
        int[][] copy = new int[N][M];
        for (int i = 0; i < N; i++) {
            copy[i] = map[i].clone();
        }
        return copy;
    }

}
