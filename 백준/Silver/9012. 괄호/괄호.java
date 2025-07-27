import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String S = br.readLine();
            int cnt = 0;
            for (char c : S.toCharArray()) {
                if (c == '(') {
                    cnt++;
                } else if (cnt <= 0) {
                    cnt--;
                    break;
                } else {
                    cnt--;
                }
            }
            if (cnt == 0) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            };
        }
    }
}