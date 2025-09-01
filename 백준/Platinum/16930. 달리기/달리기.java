import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static char[][] map;
    static int[][] dist;
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[N + 1][M + 1];
        dist = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            String line = br.readLine();
            for (int j = 1; j <= M; j++) {
                map[i][j] = line.charAt(j - 1);
                dist[i][j] = -1; // -1로 초기화 (미방문)
            }
        }

        st = new StringTokenizer(br.readLine());
        int sr = Integer.parseInt(st.nextToken());
        int sc = Integer.parseInt(st.nextToken());
        int er = Integer.parseInt(st.nextToken());
        int ec = Integer.parseInt(st.nextToken());

        System.out.println(bfs(sr, sc, er, ec));
    }

    private static int bfs(int sr, int sc, int er, int ec) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] { sr, sc, 0 });
        dist[sr][sc] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1], d = cur[2];

            if (r == er && c == ec) {
                return d;
            }

            // 이미 더 짧은 경로로 방문했다면 스킵
            if (dist[r][c] < d) continue;

            for (int dir = 0; dir < 4; dir++) {
                // 각 방향으로 1~K칸까지 이동
                for (int step = 1; step <= (K == 0 ? 1 : K); step++) {
                    int nr = r + dr[dir] * step;
                    int nc = c + dc[dir] * step;

                    if (!check(nr, nc)) break; // 범위 벗어나면 더 이상 진행 불가
                    if (map[nr][nc] == '#') break; // 장애물 만나면 더 이상 진행 불가

                    // 이미 더 짧은 거리로 방문했다면 스킵
                    if (dist[nr][nc] != -1 && dist[nr][nc] < d + 1) break;
                    if(dist[nr][nc] == -1) {
                        dist[nr][nc] = d + 1;
                        q.offer(new int[] { nr, nc, d + 1 });
                    }
                }
            }
        }

        return dist[er][ec];
    }

    private static boolean check(int r, int c) {
        return r >= 1 && r <= N && c >= 1 && c <= M;
    }
}