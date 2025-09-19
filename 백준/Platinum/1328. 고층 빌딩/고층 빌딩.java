import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());

		long[][][] dp = new long[N + 1][L + 1][R + 1];
		dp[1][1][1] = 1;
		int mod = 1000000007;

		for (int i = 2; i <= N; i++) {
			for (int j = 1; j <= L; j++) {
				for (int k = 1; k <= R; k++) {
					dp[i][j][k] = (dp[i-1][j-1][k] + dp[i-1][j][k-1] + dp[i-1][j][k] * (i - 2)) % mod  ;
				}
			}
		}

		System.out.println(dp[N][L][R]);
	}
}