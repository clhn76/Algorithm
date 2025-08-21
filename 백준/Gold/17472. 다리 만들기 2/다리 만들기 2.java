
import java.io.*;
import java.util.*;

public class Main {

    static class Edge {

        int from, to, w;

        public Edge(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }

    }

    static int N, M;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[] parent;

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

        int islandCnt = 0;
        int lastIslandNum = 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    islandCnt++;
                    bfs(i, j, lastIslandNum);
                    lastIslandNum++;
                }
            }
        }

        // 다리를 Edge로
        PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.w - e2.w);;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] >= 2) {
                    for (int d = 0; d < 4; d++) {
                        int nr = i + dr[d];
                        int nc = j + dc[d];
                        // 직선 연결이 다음 섬과 연결되는지 확인
                        int dist = 0;
                        while (check(nr, nc)) {
                            if (map[nr][nc] == 0) {
                                dist++;
                                nr += dr[d];
                                nc += dc[d];
                            } else {
                                if (dist > 1) {
                                    pq.add(new Edge(map[i][j], map[nr][nc], dist));
                                }
                                break;
                            }
                        }

                    }
                }
            }
        }

        parent = new int[lastIslandNum + 1]; //
        for (int i = 2; i <= lastIslandNum; i++) {
            parent[i] = i;
        }

        int cnt = 0;
        int sum = 0;
        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            if (find(e.from) != find(e.to)) {
                union(e.from, e.to);
                cnt++;
                sum += e.w;
                if (cnt == islandCnt - 1) {
                    break;
                }
            }
        }

        System.out.println(cnt == islandCnt - 1 ? sum : -1);
    }

    private static void union(int a, int b) {
        parent[find(b)] = find(a);
    }

    private static int find(int i) {
        if (parent[i] == i) {
            return i;
        }
        return parent[i] = find(parent[i]);
    }

    private static void bfs(int r, int c, int groupNum) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{r, c});
        map[r][c] = groupNum;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cr = cur[0];
            int cc = cur[1];
            for (int d = 0; d < 4; d++) {
                int nr = cr + dr[d];
                int nc = cc + dc[d];
                if (!check(nr, nc)) {
                    continue;
                }
                if (map[nr][nc] == 1) {
                    map[nr][nc] = groupNum;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
    }

    private static boolean check(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

}
