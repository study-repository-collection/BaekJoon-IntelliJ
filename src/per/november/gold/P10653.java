package per.november.gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

public class P10653 {
    static ArrayList<Point> checkPoints = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        checkPoints.add(new Point(1000000, 1000000));
        for (int i = 0; i < N; i++) {
            StringTokenizer point = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(point.nextToken());
            int y = Integer.parseInt(point.nextToken());
            checkPoints.add(new Point(x, y));
        }
        System.out.println(solution(N, K));
    }

    static int solution(int N, int K) {
        int[][] dp = new int[N + 1][K + 1]; //dp[i][j] = i번째 까지 j개를 거쳐서 갈 수 있는 최소 거리
        for (int i = 0; i <= N; i++) Arrays.fill(dp[i], 987654321);
        dp[1][0] = 0;
        Queue<Info> queue = new LinkedList<>();
        queue.add(new Info(1, 2, 0, 0));

        while (!queue.isEmpty()) {
            Info info = queue.poll();
            if (info.currentCheckPoint == N) {
                continue;
            }
            if (dp[info.nextCheckPoint][info.skipCount] > dp[info.currentCheckPoint][info.skipCount] + getDistance(checkPoints.get(info.currentCheckPoint), checkPoints.get(info.nextCheckPoint))) {
                dp[info.nextCheckPoint][info.skipCount] = dp[info.currentCheckPoint][info.skipCount] + getDistance(checkPoints.get(info.currentCheckPoint), checkPoints.get(info.nextCheckPoint));
                queue.add(new Info(info.nextCheckPoint, info.nextCheckPoint + 1, info.skipCount, dp[info.nextCheckPoint][info.skipCount]));
            }
            if (info.skipCount < K && info.nextCheckPoint != N && dp[info.nextCheckPoint][info.skipCount + 1] > dp[info.currentCheckPoint][info.skipCount]) {
                dp[info.nextCheckPoint][info.skipCount + 1] = dp[info.currentCheckPoint][info.skipCount];
                queue.add(new Info(info.currentCheckPoint, info.nextCheckPoint + 1, info.skipCount + 1, dp[info.currentCheckPoint][info.skipCount]));
            }

        }

        int min = Integer.MAX_VALUE;
        for (int value : dp[N]) {
            min = Math.min(min, value);
        }
        return min;
    }

    static class Info {
        int currentCheckPoint;
        int nextCheckPoint;
        int skipCount;
        int currentValue;

        public Info(int checkPoint, int nextCheckPoint, int skipCount, int currentValue) {
            this.currentCheckPoint = checkPoint;
            this.skipCount = skipCount;
            this.currentValue = currentValue;
            this.nextCheckPoint = nextCheckPoint;
        }
    }

    static int getDistance(Point point1, Point point2) {
        return Math.abs(point1.x - point2.x) + Math.abs(point1.y - point2.y);
    }
}
