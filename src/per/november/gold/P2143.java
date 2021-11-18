package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.System.getenv;
import static java.lang.System.in;

public class P2143 {
    static int[] A;
    static int[] B;
    static int[][] AArr;
    static int[][] BArr;
    static ArrayList<Integer> BSeqeunce = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int m = Integer.parseInt(br.readLine());
        B = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        AArr = calculateCumulativeSum(A);
        BArr = calculateCumulativeSum(B);
        for (int i = 0; i < m; i++) {
            for (int j = i; j < m; j++) {
                BSeqeunce.add(BArr[i][j]);
            }
        }
        BSeqeunce.sort(null);
        System.out.println(solution(n, m, T));
    }

    static long solution(int n, int m, int T) {
        long answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                answer += findCount(T - AArr[i][j]);
            }
        }
        return answer;
    }

    static long findCount(int toFind) {
        int lower = lowerBound(BSeqeunce, 0, BSeqeunce.size() - 1, toFind);
        int end = upperBound(BSeqeunce, 0, BSeqeunce.size() - 1, toFind);
        int lowerNumber = BSeqeunce.get(lower);
        int upperNumber = BSeqeunce.get(end);
        if (lowerNumber == toFind && upperNumber != toFind) {
            return end - lower;
        } else if (lowerNumber == toFind) {
            return end - lower + 1;
        } else {
            return 0;
        }
    }

    public static int lowerBound(ArrayList<Integer> arrayList, int start, int end, int K) {
        int mid;
        while (start < end) {
            mid = (start + end) / 2;
            if (arrayList.get(mid) < K) start = mid + 1;
            else end = mid;
        }
        return end;
    }

    public static int upperBound(ArrayList<Integer> arrayList, int start, int end, int K) {
        int mid;
        while (start < end) {
            mid = (start + end) / 2;
            if (arrayList.get(mid) <= K) start = mid + 1;
            else end = mid;
        }
        return end;
    }


    static int[][] calculateCumulativeSum(int[] number) {
        int[][] cumulative = new int[number.length][number.length];
        cumulative[0][0] = number[0];
        for (int i = 1; i < number.length; i++) {
            cumulative[0][i] = cumulative[0][i - 1] + number[i];
        }
        for (int i = 1; i < number.length; i++) {
            int sub = cumulative[i - 1][i - 1];
            for (int j = i; j < number.length; j++) {
                cumulative[i][j] = cumulative[i - 1][j] - sub;
            }
        }
        return cumulative;
    }
}
