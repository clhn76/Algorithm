
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int answer = 1; // 자기 자신 포함
        int start = 1;
        int end = 1;
        int sum = 1;

        while (end < n) {
            if (sum < n) {
                sum += ++end;
            } else if (sum > n) {
                sum -= start++;
            } else {
                answer++;
                sum += ++end;
            }
        }
        System.out.println(answer);
    }
}
