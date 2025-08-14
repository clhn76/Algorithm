
import java.io.*;
import java.util.*;

public class Solution {

    static int N, maxCore, minWire;
    static int[][] board;
    static List<int[]> cores;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            board = new int[N][N];
            cores = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    // 가장자리가 아닌 코어만 저장
                    if (board[i][j] == 1 && i > 0 && i < N - 1 && j > 0 && j < N - 1) {
                        cores.add(new int[]{i, j});
                    }
                }
            }

            maxCore = 0;
            minWire = Integer.MAX_VALUE;
            dfs(0, 0, 0);

            System.out.println("#" + tc + " " + minWire);;
        }
    }

    private static void dfs(int idx, int coreCnt, int wireCnt) {
        if (coreCnt + (cores.size() - idx) < maxCore) {
            return; // 가지치기
        }
        if (idx == cores.size()) {
            if (coreCnt > maxCore) {
                maxCore = coreCnt;
                minWire = wireCnt;
            } else if (coreCnt == maxCore) {
                minWire = Math.min(minWire, wireCnt);
            }
            return;
        }

        int[] core = cores.get(idx);

        // 4방향 시도
        for (int d = 0; d < 4; d++) {
            int wire = canConnect(core[0], core[1], d);
            if (wire > 0) {
                setWire(core[0], core[1], d, 2); // 전선 설치
                dfs(idx + 1, coreCnt + 1, wireCnt + wire);
                setWire(core[0], core[1], d, 0); // 전선 제거
            }
        }

        dfs(idx + 1, coreCnt, wireCnt); // 전선 설치 안하는 경우
    }

    private static void setWire(int x, int y, int d, int val) {
        int nx = x + dx[d];
        int ny = y + dy[d];

        while (nx >= 0 && nx < N && ny >= 0 && ny < N) {
            board[nx][ny] = val;
            nx += dx[d];
            ny += dy[d];
        }
    }

    private static int canConnect(int x, int y, int d) {
        int nx = x + dx[d];
        int ny = y + dy[d];
        int cnt = 0;

        while (nx >= 0 && nx < N && ny >= 0 && ny < N) {
            if (board[nx][ny] != 0) {
                return 0; // 장애물 있음
            }
            cnt++;
            nx += dx[d];
            ny += dy[d];
        }

        return cnt;
    }
}
