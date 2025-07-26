import java.util.Scanner;

public class Main {
    static final int n = 31;
    static final int mod = 1234567891;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int len = scan.nextInt();
        String s = scan.next();

        long pow = 1;
        long answer = 0;

        for(int i = 0; i < len; i++) {
            answer += (s.charAt(i) - 'a' + 1) * pow;
            pow = (pow * n) % mod;
        }

        System.out.println(answer % mod);
    }
}