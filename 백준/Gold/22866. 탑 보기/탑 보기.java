
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        int[] heights = new int[n + 1]; // 건물별 높이
        int[] counts = new int[n + 1]; // 건물별 보이는 건물 수
        int[] nearest = new int[n + 1]; // 건물별 가장 가까운 건물번호

        for (int i = 1; i <= n ; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        // 왼쪽 탐색
        Stack<Integer> stack = new Stack<>();
        for (int i = 1; i <= n; i++) {
            // 현재 건물보다 낮은 건물들 제거
            while (!stack.isEmpty() && heights[stack.peek()] <= heights[i]) {
                stack.pop();
            }

            counts[i] += stack.size();
            if (!stack.isEmpty()) {
                nearest[i] = stack.peek();
            }

            stack.add(i);
        }

        // 오른쪽 탐색
        stack = new Stack<>();
        for (int i = n; i >= 1; i--) {
            // 현재 건물보다 낮은 건물들 제거
            while (!stack.isEmpty() && heights[stack.peek()] <= heights[i]) {
                stack.pop();
            }

            counts[i] += stack.size();
            if (!stack.isEmpty()) {
                if (nearest[i] != 0 ) {
                    if (i - nearest[i] > stack.peek() - i) {
                        nearest[i] = stack.peek();
                    }
                } else {
                    nearest[i] = stack.peek();
                }
            }

            stack.add(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(counts[i]).append(" ");
            if (nearest[i] != 0) {
                sb.append(nearest[i]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
