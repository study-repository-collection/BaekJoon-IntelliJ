package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

import static java.lang.System.in;

public class P9935 {
    static StringBuilder sb;
    static String bomb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        sb = new StringBuilder(br.readLine());
        bomb = br.readLine();
        solution(bomb.length());
    }

    static void solution(int bombLength) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < sb.length(); i++) {
            stack.push(sb.charAt(i));

            if (sb.charAt(i) == bomb.charAt(bombLength - 1)) {
                boolean isBomb = true;
                try {
                    for (int j = 1; j < bombLength; j++) {
                        if (stack.get(stack.size() - 1 - j) != bomb.charAt(bombLength - 1 - j)) {
                            isBomb = false;
                            break;
                        }
                    }
                } catch (IndexOutOfBoundsException e) {
                    isBomb = false;
                }
                if (isBomb) {
                    for (int j = 0; j < bombLength; j++) {
                        stack.pop();
                    }
                }
            }
        }
        if (stack.isEmpty()) {
            System.out.println("FRULA");
        } else {
            StringBuilder answer = new StringBuilder();
            for (Character c : stack) {
                answer.append(c);
            }
            System.out.println(answer);
        }
    }
}
