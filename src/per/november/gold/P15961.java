package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P15961 {

    static int[] arr;

    static int[] slidingWindow;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //접시의 수
        int d = Integer.parseInt(st.nextToken()); //초밥의 가짓수
        int k = Integer.parseInt(st.nextToken()); //연속해서 먹는 접시의 수
        int c = Integer.parseInt(st.nextToken()); //쿠폰 번호 c
        arr = new int[N];
        slidingWindow = new int[d + 1];

        for (int i = 0; i < N; i++) {
            int a = Integer.parseInt(br.readLine());
            arr[i] = a;
        }
        int currentAnswer = initSlidingWindow(k, c);
        System.out.println(solution(N, c, k, currentAnswer));
    }

    static int solution(int N, int c, int k, int currentAnswer) {
        int max = currentAnswer;
        int start = 0;
        int end = k - 1;
        for (; start < N; end++, start++) {
            slidingWindow[arr[start]]--;
            if (slidingWindow[arr[start]] == 0) {
                currentAnswer--;
            }
            slidingWindow[arr[end + 1]]++;
            if (slidingWindow[arr[end + 1]] == 1) {
                currentAnswer++;
            }
            if (end + 1 == N - 1) {
                end = -2;
            }
            max = Math.max(max, currentAnswer);
        }
        return max;
    }

    static int initSlidingWindow(int k, int c) {
        int ret = 0;
        for (int i = 0; i < k; i++) {
            if (slidingWindow[arr[i]] == 0) {
                ret++;
            }
            slidingWindow[arr[i]]++;
        }
        if (slidingWindow[c] == 0) {
            ret++;
        }
        slidingWindow[c]++;
        return ret;
    }
}
