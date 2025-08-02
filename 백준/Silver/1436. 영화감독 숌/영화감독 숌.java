import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int targetCnt = Integer.parseInt(br.readLine());

        long num = 666;
        long cnt = 0;
        while (true) {
            String numString = String.valueOf(num);
            if (numString.contains("666")) {
                cnt++;
                if (cnt == targetCnt) {
                    System.out.println(num);
                    break;
                }
            }
            num++;
        }
    }
}