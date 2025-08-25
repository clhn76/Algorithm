import java.io.*;
import java.util.*;

public class Main {
    static int N, sharkSize = 2, eaten = 0, time = 0;
    static int[][] map;
    static int sharkX, sharkY;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    sharkX = i;
                    sharkY = j;
                    map[i][j] = 0;
                }
            }
        }
        
        while (true) {
            Fish target = findFish();
            if (target == null) break;
            
            time += target.dist;
            sharkX = target.x;
            sharkY = target.y;
            map[sharkX][sharkY] = 0;
            
            if (++eaten == sharkSize) {
                sharkSize++;
                eaten = 0;
            }
        }
        
        System.out.println(time);
    }
    
    static Fish findFish() {
        PriorityQueue<Fish> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> q = new LinkedList<>();
        
        q.offer(new int[]{sharkX, sharkY, 0});
        visited[sharkX][sharkY] = true;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1], dist = cur[2];
            
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (visited[nx][ny] || map[nx][ny] > sharkSize) continue;
                
                visited[nx][ny] = true;
                
                if (map[nx][ny] > 0 && map[nx][ny] < sharkSize) {
                    pq.offer(new Fish(nx, ny, dist + 1));
                }
                q.offer(new int[]{nx, ny, dist + 1});
            }
        }
        
        return pq.isEmpty() ? null : pq.poll();
    }
    
    static class Fish implements Comparable<Fish> {
        int x, y, dist;
        
        Fish(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
        
        @Override
        public int compareTo(Fish o) {
            if (dist != o.dist) return dist - o.dist;
            if (x != o.x) return x - o.x;
            return y - o.y;
        }
    }
}