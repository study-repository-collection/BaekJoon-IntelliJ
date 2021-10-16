package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P12015 {
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer input = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(input.nextToken());
        }
        System.out.println(binarySearchSolution(N));
    }

    /**
     * 동적 계획법을 이용한 풀이
     *
     * @param N 수열의 길이
     * @return 부분 수열의 최대 길이
     */
    static int solution(int N) {
        int[] dp = new int[N];
        Arrays.fill(dp, 1);
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
        }
        return Arrays.stream(dp).max().getAsInt();
    }

    static int binarySearchSolution(int N) {
        ArrayList<Integer> C = new ArrayList<>();
        C.add(arr[0]);
        for (int i = 1; i < N; i++) {
            if (C.get(C.size() - 1) < arr[i]) {
                C.add(arr[i]);
            } else {
                int index = Collections.binarySearch(C, arr[i]);
                if (index < 0) {
                    index *= -1;
                    index--;
                }
                C.set(index, arr[i]);
            }
        }
        return C.size();
    }

}
