import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static char[][] map;
    static int[][] dist; // 최소 이동 횟수
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        st = new StringTokenizer(br.readLine());
        int sx = Integer.parseInt(st.nextToken()) - 1;
        int sy = Integer.parseInt(st.nextToken()) - 1;
        int ex = Integer.parseInt(st.nextToken()) - 1;
        int ey = Integer.parseInt(st.nextToken()) - 1;

        dist = new int[N][M];
        for (int[] row : dist) Arrays.fill(row, -1);

        System.out.println(bfs(sx, sy, ex, ey));
    }

    static int bfs(int sx, int sy, int ex, int ey) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{sx, sy});
        dist[sx][sy] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];

            if (x == ex && y == ey) return dist[x][y];

            for (int dir = 0; dir < 4; dir++) {
                for (int step = 1; step <= K; step++) {
                    int nx = x + dx[dir] * step;
                    int ny = y + dy[dir] * step;

                    if (nx < 0 || ny < 0 || nx >= N || ny >= M) break;
                    if (map[nx][ny] == '#') break;

                    if (dist[nx][ny] == -1) { // 처음 방문
                        dist[nx][ny] = dist[x][y] + 1;
                        q.add(new int[]{nx, ny});
                    } else if (dist[nx][ny] <= dist[x][y]) {
                        // 이미 더 빠른 경로로 방문했으므로 중단
                        break;
                    }
                }
            }
        }
        return -1;
    }
}
