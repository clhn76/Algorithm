import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, 1, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // flood fill
        int group = 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1) {
                    ff(i, j, group);
                    group++;
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int gp = map[i][j];
                if (gp >= 2) {
                    min = Math.min(min, bfs(i, j, gp));
                }
            }
        }

        System.out.println(min);
    }

    private static int bfs(int sr, int sc, int group) {
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] { sr, sc, 0 });
        visited[sr][sc] = true;
        map[sr][sc] = group;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1], dist = cur[2];

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (!check(nr, nc))
                    continue;
                if (visited[nr][nc])
                    continue;
                if (map[nr][nc] == 0) {
                    visited[nr][nc] = true;
                    q.offer(new int[] { nr, nc, dist + 1 });
                } else if (map[nr][nc] != group && dist > 0) {
                    return dist;
                }
            }
        }

        return Integer.MAX_VALUE;
    }

    private static void ff(int sr, int sc, int group) {
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] { sr, sc });
        visited[sr][sc] = true;
        map[sr][sc] = group;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1];

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (check(nr, nc) && !visited[nr][nc] && map[nr][nc] == 1) {
                    visited[nr][nc] = true;
                    map[nr][nc] = group;
                    q.offer(new int[] { nr, nc });
                }
            }
        }
    }

    private static boolean check(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}