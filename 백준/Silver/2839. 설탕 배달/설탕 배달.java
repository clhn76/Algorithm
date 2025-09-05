import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int cnt = 0;
		while (N >= 0) {
			if (N % 5 ==0) {
				 System.out.println((N / 5) + cnt);
				 return;
			} else {
				N -= 3;
				cnt++;
			}
		}
		System.out.println(-1);

	}

}