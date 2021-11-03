package per.october.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.System.in;

public class P13305 {
    static int[] distances;
    static int[] prices;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        distances = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        prices = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(solution(N));
    }

    static long solution(int N) {
        int currentPrice = prices[0];
        long sum = 0;
        for (int i = 0; i < distances.length; i++) {
            currentPrice = Math.min(currentPrice, prices[i]);
            sum += (long) currentPrice * distances[i];
        }
        return sum;
    }
}
