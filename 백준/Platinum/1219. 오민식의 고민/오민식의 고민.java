import java.io.*;
import java.util.*;

public class Main {
    static class Edge {
        int from, to;
        long cost;
        Edge(int from, int to, long cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    static final long INF = Long.MAX_VALUE;
    static int N, M, start, end;
    static long[] earn;
    static ArrayList<Edge> edges = new ArrayList<>();
    static long[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            edges.add(new Edge(a, b, c));
        }

        earn = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            earn[i] = Long.parseLong(st.nextToken());
        }

        dist = new long[N];
        Arrays.fill(dist, INF);
        dist[start] = -earn[start];  // 시작 도시에서 버는 돈 반영

        // 벨만-포드
        for (int i = 0; i < N - 1; i++) {
            for (Edge e : edges) {
                if (dist[e.from] == INF) continue;
                if (dist[e.to] > dist[e.from] + e.cost - earn[e.to]) {
                    dist[e.to] = dist[e.from] + e.cost - earn[e.to];
                }
            }
        }

        // 음수 사이클 체크
        boolean[] cycle = new boolean[N];
        for (int i = 0; i < N; i++) {
            for (Edge e : edges) {
                if (dist[e.from] == INF) continue;
                if (dist[e.to] > dist[e.from] + e.cost - earn[e.to]) {
                    dist[e.to] = dist[e.from] + e.cost - earn[e.to];
                    cycle[e.to] = true;
                }
                if (cycle[e.from]) cycle[e.to] = true;
            }
        }

        if (dist[end] == INF) {
            System.out.println("gg");
        } else if (cycle[end]) {
            System.out.println("Gee");
        } else {
            System.out.println(-dist[end]); // 최대 이득
        }
    }
}
