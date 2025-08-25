
import java.io.*;
import java.util.*;

public class Main {

    static class Fish {

        int r, c, size;

        public Fish(int r, int c, int size) {
            this.r = r;
            this.c = c;
            this.size = size;
        }

    }

    static int N, sharkSize;
    static int[][] map;
    static List<Fish> fishes = new LinkedList<>();
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int sharkR = 0;
        int sharkC = 0;
        sharkSize = 2;
        int eatCnt = 0;
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num >= 1 && num <= 6) {
                    fishes.add(new Fish(i, j, num));
                    map[i][j] = num;
                } else if (num == 9) {
                    sharkR = i;
                    sharkC = j;
                    map[i][j] = 0;
                }
            }
        }

        int moveCnt = 0;
        while (true) {
            // 가장 가까운 먹을 수 있는 물고기 있는지 확인
            int fishDist = Integer.MAX_VALUE;
            int fishIdx = -1;
            for (int i = 0; i < fishes.size(); i++) {
                Fish f = fishes.get(i);
                if (fishes.get(i).size < sharkSize) {
                    int dist = bfs(sharkR, sharkC, f.r, f.c);
                    if (dist == -1) {
                        continue;
                    }
                    if (dist < fishDist) {
                        fishDist = dist;
                        fishIdx = i;
                    } else if (dist == fishDist && fishIdx > 0) {
                        Fish prevF = fishes.get(fishIdx);
                        if (f.r < prevF.r) {
                            fishIdx = i;
                        } else if (f.r == prevF.r && f.c < prevF.c) {
                            fishIdx = i;
                        }
                    }
                }
            }

            if (fishIdx < 0) {
                break;
            }

            // 물고기 먹기
            Fish targetFish = fishes.get(fishIdx);
            moveCnt += fishDist;
            sharkR = targetFish.r;
            sharkC = targetFish.c;
            map[targetFish.r][targetFish.c] = 0;

            fishes.remove(fishIdx);

            eatCnt++;
            if (eatCnt >= sharkSize) {
                sharkSize++;
                eatCnt = 0;
            }
        }

        System.out.println(moveCnt);
    }

    private static int bfs(int sharkR, int sharkC, int fishR, int fishC) {
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sharkR, sharkC, 0});
        visited[sharkR][sharkC] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            if (cur[0] == fishR && cur[1] == fishC) {
                return cur[2];
            }

            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if (check(nr, nc) && !visited[nr][nc] && map[nr][nc] <= sharkSize) {
                    visited[nr][nc] = true;
                    q.offer(new int[]{nr, nc, cur[2] + 1});
                }
            }
        }

        return -1;
    }

    private static boolean check(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}
