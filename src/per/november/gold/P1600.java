package per.november.gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1600 {
    static int[][] ability = {{-2, -1}, {-1, -2}, {1, -2}, {2, -1}, {2, 1}, {1, 2}, {-1, 2}, {-2, 1}};
    static int[][] dxDy = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    static final int EMPTY = 0;
    static final int WALL = 1;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int K = Integer.parseInt(br.readLine());//능력 사용 횟수
        StringTokenizer mapInfo = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(mapInfo.nextToken());
        int N = Integer.parseInt(mapInfo.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            StringTokenizer mapInfos = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(mapInfos.nextToken());
            }
        }

        System.out.println(solution(N, M, K));
    }

    static int solution(int N, int M, int K) {
        Queue<Point> points = new LinkedList<>();
        boolean[][][] visited = new boolean[N][M][K + 1];
        visited[0][0][0] = false;
        points.add(new Point(0, 0, 0));
        int count = 0;
        while (!points.isEmpty()) {
            int size = points.size();
            for (int i = 0; i < size; i++) {
                Point point = points.poll();
                if (point.y == N - 1 && point.x == M - 1) {
                    return count;
                }
                for (int[] move : dxDy) {
                    int X = point.x + move[0];
                    int Y = point.y + move[1];
                    if (canGo(X, Y, N, M) && map[Y][X] != WALL && !visited[Y][X][point.abilityCount]) {
                        visited[Y][X][point.abilityCount] = true;
                        points.add(new Point(X, Y, point.abilityCount));
                    }
                }
                if (point.abilityCount < K) {
                    for (int[] move : ability) {
                        int X = point.x + move[0];
                        int Y = point.y + move[1];
                        if (canGo(X, Y, N, M) && map[Y][X] != WALL && !visited[Y][X][point.abilityCount + 1]) {
                            visited[Y][X][point.abilityCount + 1] = true;
                            points.add(new Point(X, Y, point.abilityCount + 1));
                        }
                    }
                }
            }
            count++;
        }
        return -1;
    }

    static boolean canGo(int x, int y, int N, int M) {
        return x >= 0 && y >= 0 && x < M && y < N;
    }

    static class Point {
        int x;
        int y;
        int abilityCount;

        public Point(int x, int y, int abilityCount) {
            this.x = x;
            this.y = y;
            this.abilityCount = abilityCount;
        }
    }
}
