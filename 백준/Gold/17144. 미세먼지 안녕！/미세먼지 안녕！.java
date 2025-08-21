
import java.io.*;
import java.util.*;

public class Main {

    static int R, C, T;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        int airConStartIdx = 0;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                int n = Integer.parseInt(st.nextToken());
                map[i][j] = n;
                if (airConStartIdx == 0 && n == -1) {
                    airConStartIdx = i;
                }
            }
        }

        // 시간 경과
        for (int i = 0; i < T; i++) {
            int[][] copyMap = copy(map);
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (copyMap[r][c] > 0) {
                        미세먼지확산(r, c, copyMap[r][c]);
                    }
                }
            }
            copyMap = copy(map);

            // 공기 순환
            공기순환(copyMap, airConStartIdx);

        }

        int sum = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] != -1) {
                    sum += map[i][j];
                }
            }
        }
        System.out.println(sum);
    }

    private static void print(int[][] map) {
        for (int[] row : map) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("--------------------------------");
    }

    private static void 공기순환(int[][] copyMap, int startRow) {
        // 위 순환
        for (int c = 1; c <= C - 1; c++) {
            map[0][c - 1] = copyMap[0][c];
        }
        for (int r = 1; r <= startRow; r++) {
            map[r - 1][C - 1] = copyMap[r][C - 1];
        }
        for (int i = 1; i <= C - 2; i++) {
            map[startRow][i + 1] = copyMap[startRow][i];
        }
        for (int i = 0; i <= startRow - 2; i++) {
            map[i + 1][0] = copyMap[i][0];
        }

        // 아래 순환
        for (int i = 1; i < C - 1; i++) {
            map[startRow + 1][i + 1] = copyMap[startRow + 1][i];
        }
        for (int i = startRow + 1; i < R - 1; i++) {
            map[i + 1][C - 1] = copyMap[i][C - 1];
        }
        for (int i = 1; i < C; i++) {
            map[R - 1][i - 1] = copyMap[R - 1][i];
        }
        for (int r = startRow + 3; r <= R - 1; r++) {
            map[r - 1][0] = copyMap[r][0];
        }

        map[startRow][1] = 0;
        map[startRow + 1][1] = 0;
    }

    private static void 미세먼지확산(int r, int c, int amt) {
        int unitAmt = amt / 5;
        int cnt = 0;
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (check(nr, nc) && map[nr][nc] != -1) {
                map[nr][nc] += unitAmt;
                cnt++;
            }
        }
        map[r][c] -= unitAmt * cnt;
    }

    private static boolean check(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    private static int[][] copy(int[][] map) {
        int[][] copy = new int[R][C];
        for (int i = 0; i < R; i++) {
            copy[i] = map[i].clone();
        }
        return copy;
    }
}
