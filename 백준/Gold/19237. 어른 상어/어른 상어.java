import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    // N: 격자 크기, M: 상어 수, k: 냄새 유지 시간
    static int N, M, k;

    // 맵 정보 (상어 위치)
    static int[][] map;

    // 냄새 정보 (어떤 상어의 냄새인지, 냄새가 언제 사라지는지)
    static int[][] smellOwner;
    static int[][] smellTime;

    // 상어 정보 (위치, 방향, 생존 여부)
    static Shark[] sharks;

    // 방향 벡터 (0:dummy, 1:상, 2:하, 3:좌, 4:우)
    static int[] dr = {0, -1, 1, 0, 0};
    static int[] dc = {0, 0, 0, -1, 1};

    static class Shark {
        int num;
        int r, c;
        int dir;
        int[][][] priorities; // [현재방향][우선순위] = 다음방향

        public Shark(int num, int r, int c) {
            this.num = num;
            this.r = r;
            this.c = c;
            this.priorities = new int[5][1][4];
        }
    }

    public static void main(String[] args) throws IOException {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        smellOwner = new int[N][N];
        smellTime = new int[N][N];
        sharks = new Shark[M + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int val = Integer.parseInt(st.nextToken());
                if (val > 0) {
                    map[i][j] = val;
                    sharks[val] = new Shark(val, i, j);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            sharks[i].dir = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= M; i++) {
            for (int d = 1; d <= 4; d++) {
                st = new StringTokenizer(br.readLine());
                for (int p = 0; p < 4; p++) {
                    sharks[i].priorities[d][0][p] = Integer.parseInt(st.nextToken());
                }
            }
        }

        solve();
    }

    static void solve() {
        int time = 0;
        int sharkCount = M;

        // 초기 냄새 뿌리기
        spreadSmell();

        while (time <= 1000) {
            if (sharkCount == 1) {
                System.out.println(time);
                return;
            }
            
            time++;
            if (time > 1000) {
                break;
            }

            // 1. 모든 상어 다음 위치 결정
            int[][] nextMap = new int[N][N];
            for (int i = 1; i <= M; i++) {
                if (sharks[i] == null) continue; // 이미 쫓겨난 상어

                Shark s = sharks[i];
                int nextR = -1, nextC = -1, nextDir = -1;

                // 우선순위 1: 냄새 없는 곳
                boolean moved = false;
                for (int p = 0; p < 4; p++) {
                    int d = s.priorities[s.dir][0][p];
                    int nr = s.r + dr[d];
                    int nc = s.c + dc[d];

                    if (isIn(nr, nc) && smellOwner[nr][nc] == 0) {
                        nextR = nr;
                        nextC = nc;
                        nextDir = d;
                        moved = true;
                        break;
                    }
                }

                // 우선순위 2: 자신의 냄새가 있는 곳
                if (!moved) {
                    for (int p = 0; p < 4; p++) {
                        int d = s.priorities[s.dir][0][p];
                        int nr = s.r + dr[d];
                        int nc = s.c + dc[d];

                        if (isIn(nr, nc) && smellOwner[nr][nc] == s.num) {
                            nextR = nr;
                            nextC = nc;
                            nextDir = d;
                            break;
                        }
                    }
                }
                
                // 2. 이동 및 충돌 처리
                if (nextMap[nextR][nextC] == 0) { // 해당 위치에 다른 상어가 아직 없음
                    nextMap[nextR][nextC] = s.num;
                    s.r = nextR;
                    s.c = nextC;
                    s.dir = nextDir;
                } else { // 다른 상어가 이미 있음 (번호가 더 작은 상어가 선점)
                    sharks[s.num] = null; // 현재 상어 쫓겨남
                    sharkCount--;
                }
            }
            
            map = nextMap;

            // 3. 기존 냄새 시간 감소
            decreaseSmellTime();
            
            // 4. 현재 상어 위치에 새로운 냄새 뿌리기
            spreadSmell();
        }

        System.out.println(-1);
    }

    // 현재 살아있는 상어들이 자신의 위치에 냄새를 뿌리는 함수
    static void spreadSmell() {
        for (int i = 1; i <= M; i++) {
            if (sharks[i] != null) {
                Shark s = sharks[i];
                smellOwner[s.r][s.c] = s.num;
                smellTime[s.r][s.c] = k;
            }
        }
    }

    // 전체 맵의 냄새 시간을 1씩 감소시키는 함수
    static void decreaseSmellTime() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (smellTime[i][j] > 0) {
                    smellTime[i][j]--;
                    if (smellTime[i][j] == 0) {
                        smellOwner[i][j] = 0;
                    }
                }
            }
        }
    }

    static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}