import java.io.*;
import java.util.*;

public class Main {

	static class Task {
		int order;
		int priority;

		public Task(int priority, int order) {
			this.priority = priority;
			this.order = order;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			Queue<Task> q = new ArrayDeque<>();
			int[] priorities = new int[10];
			
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int p = Integer.parseInt(st.nextToken());
				priorities[p]++;
				q.add(new Task(p, j)); //중요도, index
			}
		
			int cnt = 0;
			while (!q.isEmpty()) {
				Task cur = q.poll();
				
				boolean isPopOrder = true;
				for (int p = cur.priority + 1; p < 10; p++) {
					if (priorities[p] > 0) {
						isPopOrder = false;
						break;
					} 
				}

				if (!isPopOrder) {
					q.offer(cur);
				} else {
					priorities[cur.priority]--;
					cnt++;

					if (cur.order == M) {
						System.out.println(cnt);
						break;
					}
				}
			}
		}
	}
}