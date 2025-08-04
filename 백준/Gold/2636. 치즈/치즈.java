import java.io.*;
import java.util.*;

public class Main {
    static int[][] map;
    static boolean[][] visited;
    static int N, M, hour, changeCnt;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        while (!checkMapIsEmpty()) {
            changeCnt = 0;
            visited = new boolean[N][M];

            Queue<int[]> que = new ArrayDeque<>();
            que.offer(new int[]{0, 0});
            visited[0][0] = true;

            while (!que.isEmpty()) {
                int[] cur = que.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = cur[0] + dr[d];
                    int nc = cur[1] + dc[d];
                    if (!check(nr, nc)) continue;
                    int next = map[nr][nc];
                    visited[nr][nc] = true;
                    if (next == 0) {
                        que.offer(new int[]{nr, nc});
                    }
                    if (next == 1) {
                        map[nr][nc] = 0;
                        changeCnt++;
                    }
                }
            }
            hour++;
        }

        System.out.println(hour);
        System.out.println(changeCnt);
    }


    static boolean check(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M && !visited[r][c];
    }

    static void print() {
        for (int[] row : map) {
            System.out.println(Arrays.toString(row));
        }
    }

    static boolean checkMapIsEmpty() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0) return false;
            }
        }
        return true;
    }
}