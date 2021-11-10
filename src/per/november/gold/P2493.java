package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

public class P2493 {

    static int[] sequence;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        sequence = new int[N + 1];

        StringTokenizer sequenceInfo = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            sequence[i] = Integer.parseInt(sequenceInfo.nextToken());
        }
        solution(N);
    }

    static void solution(int N) {
        Stack<Integer> stack = new Stack<>();
        int[] answer = new int[N + 1];
        for (int i = N; i >= 1; i--) {
            while (!stack.isEmpty() && sequence[stack.peek()] <= sequence[i]) {
                answer[stack.pop()] = i;
            }
            stack.add(i);
        }
        while (!stack.isEmpty()) {
            answer[stack.pop()] = 0;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(answer[i]).append(" ");
        }
        System.out.println(sb);
    }
}
