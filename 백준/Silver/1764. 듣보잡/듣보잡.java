import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		HashSet<String> set = new HashSet<>();
		
		for (int i = 0; i < N; i++) {
			set.add(br.readLine());
		}

		List<String> answer = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			String s = br.readLine();
			if (set.contains(s)) {
				answer.add(s);
			}
		}
		Collections.sort(answer);

		StringBuilder sb = new StringBuilder();
		sb.append(answer.size()).append("\n");
		for (String a : answer) {
			sb.append(a).append("\n");
		}
		System.out.println(sb);
	}
}