import java.io.*;
import java.util.*;

public class Main {
    static int N, M, answer;
    static int[][] map;
    static List<int[]> chickens;
    static int[][] selected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        chickens = new ArrayList<>();
        selected = new int[M][];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    chickens.add(new int[] { i, j });
                }
            }
        }

        // 치킨 가게를 M만큼 조합
        answer = Integer.MAX_VALUE;
        dfs(0, 0);

        System.out.println(answer);
    }

    private static void dfs(int idx, int start) {
        if (idx >= selected.length) {
            int totalDist = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == 1) {
                        totalDist += getMinDist(i, j);
                    }
                }
            }
            answer = Math.min(totalDist, answer);
            return;
        }

        for (int i = start; i < chickens.size(); i++) {
            selected[idx] = chickens.get(i);
            dfs(idx + 1, i + 1);
            selected[idx] = null;
        }
    }

    private static int getMinDist(int r, int c) {
        int minDist = Integer.MAX_VALUE;
        for (int[] chicken : selected) {
            int dist = Math.abs(chicken[0] - r) + Math.abs(chicken[1] - c);
            minDist = Math.min(dist, minDist);
        }
        return minDist;
    }
}