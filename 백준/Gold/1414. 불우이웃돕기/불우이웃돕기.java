import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static List<Node>[] graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		graph = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		int totalLen = 0;

		for (int i = 1; i <= N; i++) {
			String line = br.readLine();
			for (int j = 1; j <= N; j++) {
				char c = line.charAt(j - 1);
				if (c != '0') {
					int len = Character.isUpperCase(c) ? c - 'A' + 27 : c - 'a' + 1;
					totalLen += len;
					graph[i].add(new Node(j, len));
					graph[j].add(new Node(i, len));
				}
			}
		}

		// 프림 알고리즘을 최소 신장 트리 구하기
		boolean[] visited = new boolean[N + 1];
		PriorityQueue<Node> pq = new PriorityQueue<>();

		int mstLen = 0;
		int mstCnt = 0;

		
		visited[1] = true; 
		for (Node next : graph[1]) {
			pq.offer(new Node(next.to, next.len));
		}


		while (!pq.isEmpty() && mstCnt < N - 1) {
			Node cur = pq.poll();
			
			if (visited[cur.to]) {
				continue;
			}
			
			visited[cur.to] = true;
			
			mstLen+= cur.len;
			mstCnt++;

			for (Node next : graph[cur.to]) {
				if (!visited[next.to]) {
					pq.offer(new Node(next.to, next.len));
				}
			}
		}

		System.out.println(mstCnt == N - 1 ? totalLen - mstLen : -1);
	}

	static class Node implements Comparable<Node> {
		int to;
		int len;

		public Node(int to, int len) {
			this.to = to;
			this.len = len;
		}

		@Override
		public int compareTo(Node n) {
			return this.len - n.len;
		}
	}
}
