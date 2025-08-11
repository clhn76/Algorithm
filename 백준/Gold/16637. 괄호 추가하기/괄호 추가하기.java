
import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static List<Integer> nums = new ArrayList<>();
    static List<Character> ops = new ArrayList<>();
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String line = br.readLine();
        for (int i = 0; i < N; i++) {
            char c = line.charAt(i);
            if (i % 2 == 0) {
                // 숫자 위치
                nums.add(Character.getNumericValue(c));
            } else {
                ops.add(c);
            }
        }

        dfs(0, nums.get(0));

        System.out.println(max);
    }

    private static void dfs(int idx, int current) {
        if (idx >= ops.size()) {
            max = Math.max(current, max);
            return;
        }

        // 1. 뒤에 괄호 X
        int noBracket = calc(current, nums.get(idx + 1), ops.get(idx));
        dfs(idx + 1, noBracket);

        // 2. 뒤에 괄호 O
        if (idx + 1 < ops.size()) {
            int yesBracket = calc(nums.get(idx + 1), nums.get(idx + 2), ops.get(idx + 1));
            dfs(idx + 2, calc(current, yesBracket, ops.get(idx)));
        }

    }

    static int calc(int n1, int n2, char op) {
        switch (op) {
            case '+':
                return n1 + n2;
            case '-':
                return n1 - n2;
            case '*':
                return n1 * n2;
        }
        return 0;
    }
}
