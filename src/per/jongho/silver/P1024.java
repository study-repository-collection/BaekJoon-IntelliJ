package per.jongho.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import static java.lang.System.in;

public class P1024 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(in);
        int N = scanner.nextInt(); //수열의 합
        int L = scanner.nextInt(); //수열의 최소// 길이
        solution(N, L);
    }

    private static void solution(int N, int L) {
        for (int i = L; i <= 100; i++) {
            int top = (2 * N) - (i * i) - i;
            int bottom = (2 * i);
            if (top % bottom == 0) {
                printAnswer(top / bottom + 1, i);
                return;
            }
        }
        System.out.println("-1");
    }

    private static void printAnswer(int start, int length) {
        if (start < 0) {
            System.out.println("-1");
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (length-- > 0) {
            stringBuilder.append(start++).append(" ");
        }
        System.out.println(stringBuilder);
    }
}