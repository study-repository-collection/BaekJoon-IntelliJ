package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P2159 {

    static final int[][] dxDy = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    static ArrayList<ArrayList<Point>> points = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        StringTokenizer input = new StringTokenizer(br.readLine());
        Point startPoint = new Point(Integer.parseInt(input.nextToken()), Integer.parseInt(input.nextToken()));
        for (int i = 0; i < N; i++) {
            points.add(new ArrayList<>());
            input = new StringTokenizer(br.readLine());
            Point temp = new Point(Integer.parseInt(input.nextToken()), Integer.parseInt(input.nextToken()));
            points.get(i).add(temp);
            for (int[] move : dxDy) {
                int X = temp.x + move[0];
                int Y = temp.y + move[1];
                if (canVisit(X, Y)) {
                    points.get(i).add(new Point(X, Y));
                }
            }
        }
        System.out.println(solution(N, startPoint));
    }

    static long solution(int N, Point startPoint) {
        long[][] dp = new long[N][5];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], 9876543210L);
        }
        for (int i = 0; i < points.get(0).size(); i++) {
            dp[0][i] = startPoint.distance(points.get(0).get(i));
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < points.get(i).size(); j++) {
                Point current = points.get(i).get(j);
                for (int k = 0; k < points.get(i - 1).size(); k++) {
                    Point before = points.get(i - 1).get(k);
                    dp[i][j] = Math.min(dp[i][j], current.distance(before) + dp[i - 1][k]);
                }
            }
        }

        long min = Long.MAX_VALUE;
        for (long value : dp[N - 1]) {
            min = Math.min(min, value);
        }
        return min;
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public long distance(Point other) {
            return Math.abs(x - other.x) + Math.abs(y - other.y);
        }
    }

    static boolean canVisit(int x, int y) {
        return x >= 0 && y >= 0 && x <= 100000 && y <= 100000;
    }
}
