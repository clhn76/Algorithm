import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		List<Integer> arr = new ArrayList<>();
		
		for (int i = 0; i < n; i++) {
			int a = Integer.parseInt(br.readLine());
			arr.add(a);
		}

		Collections.sort(arr);

		int pad = (int)  Math.round((double) n  * 0.15);
		int start = pad;
		int end = arr.size() - pad - 1;
		
		int sum = 0;
		for (int i = start; i <= end; i++) {
			sum += arr.get(i);
		}

		System.out.println(Math.round((double) sum / (end - start + 1)));
	}
}