import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int lastNum = 0, lstNumIdx = 0;
        for (int i = 0; i < 3; i++) {
            try {
                int n = Integer.parseInt(br.readLine());
                lastNum = n;
                lstNumIdx = i;
            } catch (NumberFormatException e) {
            }
        }
        int targetNum = lastNum + (3 - lstNumIdx);
        if (targetNum % 3 == 0 && targetNum % 5 == 0) {
            System.out.println("FizzBuzz");
        } else if (targetNum % 3 == 0) {
            System.out.println("Fizz");
        } else if (targetNum % 5 == 0) {
            System.out.println("Buzz");
        } else {
            System.out.println(targetNum);
        }
    }
}