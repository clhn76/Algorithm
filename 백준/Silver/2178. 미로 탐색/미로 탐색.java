import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] miro = new int[N][M];
        for (int i = 0; i < N; i++) {
            String S = br.readLine();
            for (int j = 0; j < S.length(); j++) {
                miro[i][j] = Character.getNumericValue(S.charAt(j));
            }
        }

        Queue<int[]> queue = new ArrayDeque<>();
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        boolean[][] visited = new boolean[N][M];
        queue.offer(new int[]{0, 0, 1}); // row, col, dist
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == N-1 && cur[1] == M-1) {
                System.out.println(cur[2]);
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                if (nr >= 0 && nr < N && nc >=0 && nc < M && !visited[nr][nc] && miro[nr][nc] == 1) {
                    queue.offer(new int[]{nr, nc, cur[2] + 1});
                    visited[nr][nc] = true;
                }
            }
        }
    }

    static void print(int[][] miro) {
        for (int i = 0; i < miro.length; i++) {
            for (int j = 0; j < miro[0].length; j++) {
                System.out.print(miro[i][j]);
            }
            System.out.println();
        }
    }
}