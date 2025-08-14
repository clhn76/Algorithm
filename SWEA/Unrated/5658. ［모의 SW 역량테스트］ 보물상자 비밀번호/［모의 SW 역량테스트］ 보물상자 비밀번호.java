
import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            List<Integer> arr = new ArrayList<>();
            String line = br.readLine();
            line += line;
            int unitCnt = N / 4;
            for (int i = 0; i < N / 4; i++) { // 회전수
                for (int j = 0; j < 4; j++) { // 각 방면
                    int start = i + unitCnt * j;
                    int end = i + unitCnt * (j + 1);
                    String sub = line.substring(start, end);
                    int num = Integer.parseInt(sub, 16);
                    if (!arr.contains(num)) {
                        arr.add(num);
                    }
                }
            }

            arr.sort(Collections.reverseOrder());

            System.out.printf("#%d %d\n", tc, arr.get(K - 1));
        }
    }
}
