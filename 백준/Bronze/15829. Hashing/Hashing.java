import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        char[] chars = sc.next().toCharArray();

        long sum = 0;
        for (int i = 0; i < N; i++) {
            sum += (chars[i] - 96) * Math.pow(31, i);
        }

        System.out.println(sum);
    }
}