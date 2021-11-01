package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P2812 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken());
        int K = Integer.parseInt(input.nextToken());
        String number = br.readLine();
        solution(number, K);
    }

    static void solution(String number, int K) {
        Stack<Integer> stack = new Stack<>();
        int count = 0;
        for (int i = 0; i < number.length(); i++) {
            if (stack.isEmpty()) {
                stack.add(number.charAt(i) - '0');
            } else if (stack.peek() < number.charAt(i) - '0' && count != K) {
                while (!stack.isEmpty() && stack.peek() < number.charAt(i) - '0' && count != K) {
                    count++;
                    stack.pop();
                }
                stack.add(number.charAt(i) - '0');
            } else {
                stack.add(number.charAt(i) - '0');
            }
        }
        for (; count < K; count++) {
            stack.pop();
        }

        int answer = 0;

        for (int i = 0; !stack.isEmpty(); i++) {
            answer += stack.pop() * Math.pow(10, i);
        }
        System.out.println(answer);
    }

}
