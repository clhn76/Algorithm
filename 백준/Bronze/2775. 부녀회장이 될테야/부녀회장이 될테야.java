import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer tc = Integer.parseInt(br.readLine());

        for (int i = 0; i < tc; i++) {
            int k = Integer.parseInt(br.readLine());
            int n = Integer.parseInt(br.readLine());

            int[] prev = new int[n];
            for (int j = 0; j < n; j++) {
                prev[j] = j + 1;
            }
            int[] next = new int[n];

            if (k == 0) {
                System.out.println(prev[n-1]);
                continue;
            }
            for (int h = 0; h <k ; h++) {
                for (int j = 0; j < n; j++) {
                    int sum = 0;
                    for (int l = 0; l <= j; l++) {
                        sum += prev[l];
                    }
                    next[j] = sum;
                    if (j == n -1 ) {
                        prev = Arrays.copyOf(next, next.length);
                    }
                }
            }
            System.out.println(next[n-1]);
        }
    }
}