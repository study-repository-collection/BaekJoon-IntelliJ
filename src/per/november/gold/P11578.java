package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P11578 {
    static int[] arr;
    static int answer = 0;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        arr = new int[M + 1];
        initAnswer(N);

        for (int i = 1; i <= M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            st.nextToken();
            while (st.hasMoreTokens()) {
                arr[i] |= 1 << Integer.parseInt(st.nextToken());
            }
        }

        solution(M, 1, 0, 0);
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    static void initAnswer(int N) {
        for (int i = 1; i <= N; i++) {
            answer |= 1 << i;
        }
    }

    static void solution(int M, int start, int count, int number) {
        if (canCompleteAll(number)) {
            min = Math.min(min, count);
            return;
        }
        for (int i = start; i <= M; i++) {
            solution(M, i + 1, count + 1, number | arr[i]);
            solution(M, i + 1, count, number);
        }
    }

    static boolean canCompleteAll(int number) {
        return number == answer;
    }
}
