import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] matrix;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        matrix = new int[N][2];
        dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            matrix[i][0] = Integer.parseInt(st.nextToken()); // 행
            matrix[i][1] = Integer.parseInt(st.nextToken()); // 열
        }

        int result = dfs(0, N - 1);
        System.out.println(result);
    }

    private static int dfs(int left, int right) {
        if (left == right) {
            return 0;
        }

        if (dp[left][right] != -1) {
            return dp[left][right];
        }

        dp[left][right] = Integer.MAX_VALUE;

        for (int mid = left; mid < right; mid++) {
            int leftCost = dfs(left, mid);
            int rightCost = dfs(mid + 1, right);
            int mergeCost = matrix[left][0] * matrix[mid][1] * matrix[right][1];

            int totalCost = leftCost + rightCost + mergeCost;

            dp[left][right] = Math.min(dp[left][right], totalCost);
        }

        return dp[left][right];
    }
}