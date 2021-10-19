package per.jongho.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P11659 {
    static int[] prefixSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken());//수의 개수
        int M = Integer.parseInt(input.nextToken());//합을 구해야하는 횟수

        prefixSum = new int[N + 1];
        int[] numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        getPrefixSum(numbers);
        while (M-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            sb.append(getSum(start, end)).append("\n");
        }
        System.out.print(sb);
    }

    static void getPrefixSum(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            prefixSum[i + 1] = prefixSum[i] + numbers[i];
        }
    }

    static int getSum(int start, int end) {
        return prefixSum[end] - prefixSum[start - 1];
    }
}
