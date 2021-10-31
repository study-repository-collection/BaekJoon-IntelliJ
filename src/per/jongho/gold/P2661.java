package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

import static java.lang.System.in;

public class P2661 {
    static int[] sequence;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        sequence = new int[N + 1];
        solution(N, 1);
    }


    static boolean find = false;

    static void solution(int N, int index) {
        if (index == N + 1 && !find) {
            find = true;
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= N; i++) {
                sb.append(sequence[i]);
            }
            System.out.print(sb);
            return;
        }
        if (find) return;
        for (int i = 1; i <= 3; i++) {
            if (i != sequence[index - 1]) {
                sequence[index] = i;
                if (isEnable(index)) {
                    solution(N, index + 1);
                }
            }
        }
    }


    static boolean isEnable(int index) {
        for (int i = 2; i <= index / 2; i++) {
            boolean isEnable = false;
            for (int j = 0; j < i; j++) {
                if (sequence[index - (2 * i) + 1 + j] != sequence[index - i + 1 + j]) {
                    isEnable = true;
                    break;
                }
            }
            if (!isEnable) {
                return false;
            }
        }
        return true;
    }
}
