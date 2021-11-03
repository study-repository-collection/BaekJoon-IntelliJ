package per.october.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

import static java.lang.System.in;

public class P1918 {

    static final HashMap<Character, Integer> hashMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        hashMap.put('(', 3);
        hashMap.put(')', 3);
        hashMap.put('+', 1);
        hashMap.put('-', 1);
        hashMap.put('*', 2);
        hashMap.put('/', 2);
        String input = br.readLine();
        solution(input);
    }

    static void solution(String input) {
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < input.length(); i++) {
            char temp = input.charAt(i);
            if (!hashMap.containsKey(temp)) {
                sb.append(temp);
            } else if (temp == '+' || temp == '-') {
                while (!stack.isEmpty() && (stack.peek() != '(')) {
                    sb.append(stack.pop());
                }
                stack.add(temp);
            } else if (temp == '*' || temp == '/') {
                while (!stack.isEmpty() && (stack.peek() == '*' || stack.peek() == '/')) {
                    sb.append(stack.pop());
                }
                stack.add(temp);
            } else if (temp == ')') {
                while (stack.peek() != '(') {
                    sb.append(stack.pop());
                }
                stack.pop();
            } else if (temp == '(') {
                stack.add(temp);
            }
        }
        while (!stack.isEmpty()) sb.append(stack.pop());
        System.out.println(sb);
    }
}
