import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int M = Integer.parseInt(br.readLine());

		HashSet<Integer> set = new HashSet<>();

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			int x = -1;
			if (st.hasMoreTokens()) {
				x = Integer.parseInt(st.nextToken());
			}

			switch (cmd) {
				case "add":
					set.add(x);
					break;
				case "remove":
					set.remove(x);
					break;
				case "check":
					if (set.contains(x)) {
						sb.append(1);
					} else {
						sb.append(0);
					}
					sb.append("\n");
					break;
				case "toggle":
					if (set.contains(x)) {
						set.remove(x);
					} else {
						set.add(x);
					}
					break;
				case "all":
					for (int j = 1; j <= 20; j++) {
						set.add(j);
					}
					break;
				case "empty":
					set.clear();
					break;

			}
		}

		System.out.println(sb);
	}
}