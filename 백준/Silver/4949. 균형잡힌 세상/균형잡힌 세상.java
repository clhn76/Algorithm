import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();


		while (!line.equals(".")) {
			Stack<Character> stack = new Stack<>();
			boolean isOk = true;

			for (char c : line.toCharArray()) {
				if (c == '(' || c == '[') {
					stack.push(c);
				} else if (c == ')' || c == ']') {
					if (stack.isEmpty()) {
						isOk= false;
						break;
					} else if (c == ')' ? stack.pop() != '(' : stack.pop() != '[') {
						isOk = false;
						break;
					}
				}
			}

			if (!stack.isEmpty()) {
				isOk = false;
			}

			if (isOk) {
				System.out.println("yes");
			} else {
				System.out.println("no");
			}

			line = br.readLine();
		}	
	}
}