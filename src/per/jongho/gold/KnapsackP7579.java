package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class KnapsackP7579 {
    static ArrayList<App> apps = new ArrayList<>();


    static class App implements Comparable<App> {
        final int memoryUsage;
        final int weight;

        public App(int memoryUsage, int weight) {
            this.memoryUsage = memoryUsage;
            this.weight = weight;
        }

        @Override
        public int compareTo(App o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());//현재 활성화 되어 있는 앱의 개수
        int M = Integer.parseInt(st.nextToken());//확보해야 하는 바이트
        StringTokenizer memoryUsage = new StringTokenizer(br.readLine());
        StringTokenizer weight = new StringTokenizer(br.readLine());
        int sumOfZero = 0;
        int sumOfWeight = 0;
        for (int i = 0; i < N; i++) {
            int usage = Integer.parseInt(memoryUsage.nextToken());
            int appWeight = Integer.parseInt(weight.nextToken());
            if (appWeight == 0) sumOfZero += usage;
            else apps.add(new App(usage, appWeight));
            sumOfWeight += appWeight;
        }
        Collections.sort(apps);
        if (sumOfZero >= M) {
            System.out.println(0);
        } else {
            System.out.println(solution(N, M, sumOfZero, sumOfWeight));
        }
    }

    static int solution(int N, int M, int sumOfZero, int sumOfWeight) {
        int[][] dp = new int[N][sumOfWeight + 1];
        Arrays.fill(dp[0], sumOfZero);

        int minIndex = sumOfWeight + 2;
        for (int j = 0; j <= sumOfWeight; j++) {
            if (apps.get(0).weight <= j) {
                dp[0][j] += apps.get(0).memoryUsage;
            }
            if (dp[0][j] >= M) minIndex = Math.min(minIndex, j);
        }
        for (int i = 1; i < apps.size(); i++) {
            for (int j = 0; j <= sumOfWeight; j++) {
                if (apps.get(i).weight <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - apps.get(i).weight] + apps.get(i).memoryUsage);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }

                if (dp[i][j] >= M) minIndex = Math.min(minIndex, j);
            }
        }
        return minIndex;
    }

}
