
import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] population;
    static List<Integer>[] graph;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        population = new int[N + 1];
        graph = new List[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt; j++) {
                int to = Integer.parseInt(st.nextToken());
                graph[i].add(to);
            }
        }

        // 비트마스킹을 통한 모든 조합 확인 (1 ~ 2^N -1)
        for (int mask = 1; mask < (1 << N) - 1; mask++) {
            List<Integer> groupA = new ArrayList<>();
            List<Integer> groupB = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                if ((mask & (1 << i)) != 0) {
                    groupA.add(i + 1);
                } else {
                    groupB.add(i + 1);
                }
            }

            // 두 그룹이 각각 연결되어 있는지 확인
            if (isConnected(groupA) && isConnected(groupB)) {
                int diff = Math.abs(getSum(groupA) - getSum(groupB));
                answer = Math.min(answer, diff);
            }
        }

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    private static int getSum(List<Integer> group) {
        int sum = 0;
        for (int node : group) {
            sum += population[node];
        }
        return sum;
    }

    private static boolean isConnected(List<Integer> group) {
        if (group.isEmpty()) {
            return false;
        }

        Set<Integer> groupSet = new HashSet<>(group);
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];

        queue.offer(group.get(0));
        visited[group.get(0)] = true;
        int cnt = 1;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : graph[cur]) {
                if (!visited[next] && groupSet.contains(next)) {
                    visited[next] = true;
                    queue.offer(next);
                    cnt++;
                }
            }
        }

        return cnt == group.size();
    }
}
