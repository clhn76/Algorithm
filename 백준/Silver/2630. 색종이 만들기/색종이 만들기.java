import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N, totalOneCnt, totalZeroCnt;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        dfs(0, N, 0, N);
        
        System.out.println(totalZeroCnt);
        System.out.println(totalOneCnt);
    }

    private static void dfs(int rs, int re, int cs, int ce) {
        int oneCnt = 0;
        int zeroCnt = 0;        
        int goal = (re - rs) * (ce - cs);
        
        // 현재 영역의 색상 개수 세기
        for (int i = rs; i < re; i++) {
            for (int j = cs; j < ce; j++) {
                if (map[i][j] == 1) {
                    oneCnt++;
                } else {
                    zeroCnt++;
                }
            }
        }
        
        // 모두 같은 색이면 카운트 증가하고 종료
        if (oneCnt == goal) {
            totalOneCnt++;
            return; // 재귀 종료
        } else if (zeroCnt == goal) {
            totalZeroCnt++;
            return; // 재귀 종료
        }
        
        // 다른 색이 섞여있으면 4등분하여 재귀 호출
        int midR = (rs + re) / 2;
        int midC = (cs + ce) / 2;
        
        dfs(rs, midR, cs, midC);      // 좌상단
        dfs(rs, midR, midC, ce);      // 우상단  
        dfs(midR, re, cs, midC);      // 좌하단
        dfs(midR, re, midC, ce);      // 우하단
    }
}