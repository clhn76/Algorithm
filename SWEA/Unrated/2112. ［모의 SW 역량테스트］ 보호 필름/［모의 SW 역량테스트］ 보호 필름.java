import java.io.*;
import java.util.*;

public class Solution {
    static int D, W, K, minCnt;
    static int[][] film;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            film = new int[D][W];
            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    film[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // K가 1이면 무조건 통과
            if (K == 1) {
                minCnt = 0;
            } else {
                minCnt = Integer.MAX_VALUE;
                dfs(0, 0);
            }

            System.out.println("#" + tc + " " + minCnt);
        }
    }

    private static void dfs(int row, int cnt) {
        // 가지치기
        if (cnt >= minCnt) return;

        if (row == D) {
            if (checkPass()) {
                minCnt = Math.min(minCnt, cnt);
            }
            return;
        }

        // 현재 행 약물 처리 안함
        dfs(row + 1, cnt);

        // 0으로 처리
        int[] backup = film[row].clone();
        Arrays.fill(film[row], 0);
        dfs(row + 1, cnt + 1);
        film[row] = backup;

        // 1으로 처리
        backup = film[row].clone();
        Arrays.fill(film[row], 1);
        dfs(row + 1, cnt + 1);
        film[row] = backup;
    }

    private static boolean checkPass() {
        for (int c = 0; c < W; c++) {
            boolean ok = false;
            int count = 1; // 연속 개수

            for (int r = 1; r < D; r++) {
                if (film[r][c] == film[r - 1][c]) {
                    count++;
                } else {
                    count = 1;
                }
                if (count >= K) {
                    ok = true;
                    break;
                }
            }

            // D == K 인 경우, 첫 행부터 마지막 행까지 같은 값이면 ok는 true로 바뀌지 않을 수 있음
            if (K == D) {
                ok = true; // 이 경우 전체가 연속
            }

            if (!ok) return false; // 한 열이라도 실패하면 불합격
        }
        return true;
    }
}
