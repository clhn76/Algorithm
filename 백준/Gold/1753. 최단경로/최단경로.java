import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int v, w;
        
        Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
        
        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());
        
        // 그래프 초기화
        List<Node>[] graph = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }
        
        // 간선 입력
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Node(v, w));
        }
        
        // 다익스트라
        int[] dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[K] = 0;
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(K, 0));
        
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            
            if (cur.w > dist[cur.v]) continue;
            
            for (Node next : graph[cur.v]) {
                if (dist[next.v] > dist[cur.v] + next.w) {
                    dist[next.v] = dist[cur.v] + next.w;
                    pq.offer(new Node(next.v, dist[next.v]));
                }
            }
        }
        
        // 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            sb.append(dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]).append('\n');
        }
        System.out.print(sb);
    }
}