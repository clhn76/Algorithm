import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] chars = br.readLine().toCharArray();
        int checkIndex = 0;
        int m = Character.getNumericValue(chars[chars.length - 1]);
        for (int i = 0; i < 13; i++) {
            if (chars[i] == '*') checkIndex = i;
        }

        for (int i = 0; i <= 9; i++) {
            int sum = 0;
            for (int j = 0; j < checkIndex; j++) {
                int n = Character.getNumericValue(chars[j]);
                sum += j % 2 == 0 ? n : 3 * n;
            }
            for (int j = checkIndex + 1; j < 12; j++) {
                int n = Character.getNumericValue(chars[j]);
                sum += j % 2 == 0 ? n : 3 * n;
            }
            if (checkIndex % 2 == 0) {
                sum += i;
            } else {
                sum += 3 * i;
            }

            if (m == 0 && sum % 10 == 0) {
                System.out.println(i);
                break;
            } else if (m == 10 - (sum % 10)) {
                System.out.println(i);
                break;
            }
        }
    }
}