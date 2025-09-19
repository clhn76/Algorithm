import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<String> pmonList = new ArrayList<>();
		HashMap<String, Integer> pmonMap = new HashMap<>();

		for (int i = 0 ; i < N; i++) {
			String a = br.readLine();
			pmonList.add(a);
			pmonMap.put(a, i);
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			String input =  br.readLine();
			if (input.matches("[a-zA-z]+")) {
				sb.append(pmonMap.get(input)+ 1);
			} else {
				int idx = Integer.parseInt(input) - 1;
				sb.append(pmonList.get(idx));
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}