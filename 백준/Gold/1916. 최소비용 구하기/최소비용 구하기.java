import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static List<Node>[] graph;
	static long[] Cost;
	static long INF = Long.MAX_VALUE / 3;

	static class Node {
		int to;
		long cost;

		public Node(int to, long newCost) {
			this.to = to;
			this.cost = newCost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		graph = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			graph[from].add(new Node(to, cost));
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		Cost = new long[N + 1];
		Arrays.fill(Cost, INF);
		Cost[start] = 0;

		// 다익스트라
		PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> n1.cost < n2.cost  ? -1 : 1);
		pq.offer(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			if (cur.cost > Cost[cur.to]) continue;

			for (Node next: graph[cur.to]) {
				long newCost = Cost[cur.to] + next.cost;
				if (newCost < Cost[next.to]) {
					Cost[next.to] = newCost;
					pq.offer(new Node(next.to, newCost));
				}
			}
		}

		System.out.println(Cost[end]);
	}

}
