
import java.io.*;
import java.util.*;

public class Main {

    static int N, M, R;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < R; i++) {
            int n = Integer.parseInt(st.nextToken());
            switch (n) {
                case 1:
                    p1();
                    break;
                case 2:
                    p2();
                    break;
                case 3:
                    p3();
                    break;
                case 4:
                    p4();
                    break;
                case 5:
                    p5();
                    break;
                case 6:
                    p6();
                    break;

            }
        }
        print();
    }

    static void p1() {
        int N = map.length;
        int M = map[0].length;
        int[][] temp = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                temp[i][j] = map[N - i - 1][j];
            }
        }
        map = temp;
    }

    static void p2() {
        int N = map.length;
        int M = map[0].length;
        int[][] temp = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                temp[i][j] = map[i][M - j - 1];
            }
        }
        map = temp;
    }

    static void p3() {
        int N = map.length;
        int M = map[0].length;
        int[][] temp = new int[M][N];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                temp[j][N - 1 - i] = map[i][j];
            }
        }
        map = temp;
    }

    static void p4() {
        int N = map.length;
        int M = map[0].length;
        int[][] temp = new int[M][N];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                temp[M - j -1][i] = map[i][j];
            }
        }
        map = temp;
    }

    static void p5() {
        int N = map.length;
        int M = map[0].length;
        int[][] temp = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i < N / 2 && j < M / 2) {
                    //1구역
                    temp[i][j + M / 2] = map[i][j];
                } else if (i < N / 2 && j >= M / 2) {
                    // 2구역
                    temp[i + N / 2][j] = map[i][j];
                } else if (i >= N / 2 && j >= M / 2) {
                    // 3구역
                    temp[i][j - M / 2] = map[i][j];
                } else {
                    temp[i - N / 2][j] = map[i][j];
                }

            }
        }
        map = temp;
    }

    static void p6() {
        int N = map.length;
        int M = map[0].length;
        int[][] temp = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i < N / 2 && j < M / 2) {
                    //1구역
                    temp[i + N / 2][j] = map[i][j];
                } else if (i < N / 2 && j >= M / 2) {
                    // 2구역
                    temp[i][j - M / 2] = map[i][j];
                } else if (i >= N / 2 && j >= M / 2) {
                    // 3구역
                    temp[i - N / 2][j] = map[i][j];
                } else {
                    temp[i][j + M / 2] = map[i][j];
                }

            }
        }
        map = temp;
    }

    static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

}
