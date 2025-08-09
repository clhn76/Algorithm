import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] result = new int[n];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        Stack<Integer> stack = new Stack<>();
        
        // 배열을 뒤에서부터 처리
        for (int i = n - 1; i >= 0; i--) {
            // 스택에서 현재 원소보다 작거나 같은 원소들을 제거
            while (!stack.isEmpty() && stack.peek() <= arr[i]) {
                stack.pop();
            }
            
            // 스택이 비어있으면 오큰수가 없음(-1)
            // 비어있지 않으면 스택의 맨 위가 오큰수
            if (stack.isEmpty()) {
                result[i] = -1;
            } else {
                result[i] = stack.peek();
            }
            
            // 현재 원소를 스택에 추가
            stack.push(arr[i]);
        }
        
        // 결과 출력
        for (int i = 0; i < n; i++) {
            bw.write(result[i] + " ");
        }
        
        bw.flush();
        bw.close();
        br.close();
    }
}