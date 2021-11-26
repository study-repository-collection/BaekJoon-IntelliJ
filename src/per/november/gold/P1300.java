package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;

public class P1300 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine()); //배열의 크기 크기가 N*N 배열에 들어있는 수 A[i][j] = i*j;x
        long K = Long.parseLong(br.readLine()); //K번째 수를 구하시오
        solution(K, N);
        System.out.println(answer);
    }


    static long solution(long K, int N) {
        long maxNumber = (long) N * N;
        return binarySearch(1, maxNumber, K, N);
    }

    static long answer = 0;

    static long binarySearch(long start, long end, long K, int N) {
        long mid = 0;
        while (start <= end) {
            mid = (start + end) / 2;
            long count = countLowerNumber(mid, N, K);
            if (count >= K) {
                end = mid - 1;
            } else { start = mid + 1;
            }
        }
        return mid;
    }

    static long countLowerNumber(long number, int N, long K) {
        long count = 0;
        long pohamCount = 0;
        for (int i = 1; i <= N; i++) {
            long temp = number / i;
            pohamCount += Math.min(N, temp);
            if (i * temp == number) temp -= 1;
            count += Math.min(N, temp);
        }
        if (count < K && K <= pohamCount) answer = number;
        return count;
    }
}
