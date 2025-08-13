
import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int totalCnt = 0;
    static int[] coins;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        coins = new int[N];
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }
        // 큰 수 부터 탐색
        while (K > 0) {
            for (int i = N - 1; i >= 0; i--) {
                int cnt = K / coins[i];
                if (cnt > 0) {
                    K -= coins[i] * cnt;
                    totalCnt += cnt;
                }
            }
        }

        System.out.println(totalCnt);
    }

}
