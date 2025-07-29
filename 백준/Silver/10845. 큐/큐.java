import java.util.*;
import java.io.*;

class Main {
    static int N;
    static Queue<Integer> que;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        que = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            switch (cmd) {
                case "push":
                    int num = Integer.parseInt(st.nextToken());
                    que.offer(num);
                    break;
                case "pop":
                    if (que.isEmpty()) {
                        System.out.println(-1);
                    } else {
                        System.out.println(que.poll());
                    }
                    break;
                case "size":
                    System.out.println(que.size());
                    break;
                case  "empty":
                    if (que.isEmpty()) {
                        System.out.println(1);
                    } else {
                        System.out.println(0);
                    }
                    break;
                case "front":
                    if (que.isEmpty()) {
                        System.out.println(-1);
                    } else {
                        System.out.println(que.peek());
                    }
                    break;
                case "back":
                    if (que.isEmpty()) {
                        System.out.println(-1);
                    } else {
                        System.out.println(que.toArray()[que.size() -1]);
                    }
                    break;
            }
        }
    }
}