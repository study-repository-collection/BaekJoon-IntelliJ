package per.october.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.System.in;

public class P11045 {
    static int[] sequence;
    static int[] LIS;
    static int[] LDS;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        sequence = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        LDS = new int[N];
        LIS = new int[N];
        findLDS(N);
        findLIS(N);
        System.out.println(answer(N));

    }


    static int answer(int N) {
        int max = -1;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, LIS[i] + LDS[i] -1);
        }
        return max;
    }

    //최장 부분 증가 수열을 찾는 함수
    static void findLIS(int N) {
        LIS = new int[N];
        Arrays.fill(LIS, 1);

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (sequence[j] < sequence[i]) {
                    LIS[i] = Math.max(LIS[j] + 1, LIS[i]);
                }
            }
        }
    }


    //최장 부분 감소 수열을 찾는 함수 가장 끝쪽에서부터 역순으로 탐색한다.
    static void findLDS(int N) {
        LDS = new int[N];
        Arrays.fill(LDS, 1);
        for (int i = N - 2; i >= 0; i--) {
            for (int j = i + 1; j < N; j++) {
                if (sequence[i] > sequence[j]) {
                    LDS[i] = Math.max(LDS[j] + 1, LDS[i]);
                }
            }
        }
    }
}
