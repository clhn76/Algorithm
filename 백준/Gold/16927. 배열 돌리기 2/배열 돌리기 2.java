import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int layers = Math.min(n, m) / 2;
        for (int layer = 0; layer < layers; layer++) {
            List<Integer> elements = new ArrayList<>();

            // 위
            for (int i = layer; i < m - layer - 1; i++) {
                elements.add(map[layer][i]);
            }
            // 오른쪽
            for (int i = layer; i < n - layer - 1; i++) {
                elements.add(map[i][m - layer - 1]);
            }
            // 아래
            for (int i = m - layer - 1; i > layer; i--) {
                elements.add(map[n - layer - 1][i]);
            }
            // 왼쪽
            for (int i = n - layer - 1; i > layer; i--) {
				elements.add(map[i][layer]);
            }

            // 이동 최적화
			int idx = r % elements.size();

            // 이동 반영
			// 위
            for (int i = layer; i < m - layer - 1; i++) {
               map[layer][i] = elements.get(idx % elements.size());
			   idx++;
            }
            // 오른쪽
            for (int i = layer; i < n - layer - 1; i++) {
				map[i][m - layer - 1] = elements.get(idx % elements.size());
			   idx++;
            }
            // 아래
            for (int i = m - layer - 1; i > layer; i--) {
                map[n - layer - 1][i] = elements.get(idx % elements.size());
				idx++;
            }
            // 왼쪽
            for (int i = n - layer - 1; i > layer; i--) {
				map[i][layer] = elements.get(idx % elements.size());
				idx++;
            }
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(map[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

}
