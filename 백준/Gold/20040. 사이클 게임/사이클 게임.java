import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}

		int cnt = 0;
		for (int i = 0; i < M; i++) {
			 st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			cnt++;
			if (find(s) != find(e)) {
				union(s, e);
			} else {
				System.out.println(cnt);
				return;
			}
		}
		
		System.out.println(0);
	}

	private static void union(int s, int e) {
		parent[find(s)] = find(e);
	}

	private static int find(int a) {
		if (a == parent[a]) return a;
		return parent[a] = find(parent[a]);
	}

}
