import java.io.*;
import java.util.*;

public class Main {
    static int N, minDiff;
    static List<Integer>[] graph;
    static int[] populations;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        populations = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            populations[i] = Integer.parseInt(st.nextToken());
        }

        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            for (int j = 0; j < num; j++) {
                int next = Integer.parseInt(st.nextToken());
                graph[i].add(next);
                graph[next].add(i);
            }
        }

        // 선거구를 2개의 그룹으로 나누기
        minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < (1 << N); i++) {
            List<Integer> groupA = new ArrayList<>();
            List<Integer> groupB = new ArrayList<>();

            for (int j = 0; j < N; j++) {
                if ((i & 1 << j) != 0) {
                    groupA.add(j + 1);
                } else {
                    groupB.add(j + 1);
                }
            }

            if (isConnected(groupA) && isConnected(groupB)) {
                minDiff = Math.min(minDiff, Math.abs(sum(groupA) - sum(groupB)));
            }
        }

        System.out.println(minDiff == Integer.MAX_VALUE ? -1 : minDiff);
    }

    private static int sum(List<Integer> group) {
        int sum = 0;
        for (int n : group) {
            sum += populations[n];
        }
        return sum;
    }

    private static boolean isConnected(List<Integer> group) {
        if (group.size() == 0)
            return false;

        HashSet groupSet = new HashSet<>(group);
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        int start = group.get(0);
        visited[start] = true;
        q.offer(start);
        int cnt = 0;

        // System.out.println(groupSet);

        while (!q.isEmpty()) {
            int cur = q.poll();
            cnt++;
            for (int next : graph[cur]) {
                if (!visited[next] && groupSet.contains(next)) {
                    visited[next] = true;
                    q.offer(next);
                }
            }
        }

        return cnt == group.size();
    }
}