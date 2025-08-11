
import java.io.*;
import java.util.*;


public class Main {
    static Jewel[] jewels;
    static int maxX, maxY;
    static int max = Integer.MIN_VALUE;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        jewels = new Jewel[T];
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            jewels[i] = new Jewel(x, y);
        }

        for (int i = 0; i < T; i++) {
            for (int j = 0; j < T; j++) {
                int startX = jewels[i].x;
                int startY = jewels[j].y;
                if (startX + K > N) startX = N - K;
                if (startY + K > M) startY = M - K;

                int cnt = 0;
                for (int l = 0; l < T; l++) {
                    int jx = jewels[l].x;
                    int jy = jewels[l].y;
                    if (startX <= jx && jx <= startX + K && startY <= jy && jy <= startY + K ) {
                        cnt++;
                    }
                }
              
                if (cnt > max) {
                    max = cnt;
                    maxX = startX;
                    maxY = startY;
                }
            }   
        }

        System.out.printf("%d %d\n", maxX, maxY + K);
        System.out.println(max);
    }

    static class Jewel {
        int x;
        int y;
        public Jewel(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
