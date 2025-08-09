
import java.io.*;

public class Main {
    static int N;
    static int[] A;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        dfs(0);
        System.out.println(cnt);
    }

    private static void dfs(int depth) {
        if (depth == N) {
            cnt++;
            return;
        }

        for (int i = 0; i < N; i++) { // 현재 depth의 열
            // 앞선 값과 다른 i
            boolean check = true;
            for (int j = 0; j < depth; j++) {
                if (i == A[j]) {
                    check = false;
                    break;
                }

            }
            // 앞선 값과 대각선이 아닌 i
            for (int j = 0; j < depth; j++) {
                if (depth - j == Math.abs(A[j] - i)) {
                    check = false;
                    break;
                }

            }
            if (check) {
                A[depth] = i;
                dfs(depth + 1);
            }
        }
    }
}
