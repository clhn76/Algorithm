import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine()); // 1m²당 참외 개수

        int[] direction = new int[6];
        int[] length = new int[6];

        // 입력 받기
        for (int i = 0; i < 6; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            direction[i] = Integer.parseInt(st.nextToken());
            length[i] = Integer.parseInt(st.nextToken());
        }

        // 가장 긴 가로와 세로 찾기
        int maxWidth = 0, maxHeight = 0;
        int maxWidthIdx = 0, maxHeightIdx = 0;

        for (int i = 0; i < 6; i++) {
            if (direction[i] == 1 || direction[i] == 2) { // 동쪽(1) 또는 서쪽(2)
                if (length[i] > maxWidth) {
                    maxWidth = length[i];
                    maxWidthIdx = i;
                }
            } else { // 남쪽(3) 또는 북쪽(4)
                if (length[i] > maxHeight) {
                    maxHeight = length[i];
                    maxHeightIdx = i;
                }
            }
        }

        // 작은 직사각형의 변 찾기
        // 최대 가로/세로에 인접한 두 변의 차이가 작은 직사각형의 변이 됨
        int smallWidth = Math.abs(length[(maxHeightIdx + 5) % 6] - length[(maxHeightIdx + 1) % 6]);
        int smallHeight = Math.abs(length[(maxWidthIdx + 5) % 6] - length[(maxWidthIdx + 1) % 6]);

        // 넓이 계산
        int area = maxWidth * maxHeight - smallWidth * smallHeight;

        // 참외 개수 출력
        System.out.println(area * K);
    }
}