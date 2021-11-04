package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P14442 {

    static final int WALL = 1;
    static final int EMPTY = 0;
    static final int[][] dxDy = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer mapInfo = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(mapInfo.nextToken());
        int M = Integer.parseInt(mapInfo.nextToken());
        int K = Integer.parseInt(mapInfo.nextToken()); //벽을 부술 수 있는 횟수
        map = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            String input = br.readLine();
            for (int j = 1; j <= M; j++) {
                map[i][j] = input.charAt(j - 1) - '0';
            }
        }
        System.out.println(bfs(N, M, K));
    }

    static int bfs(int N, int M, int K) {

        int time = 1;
        Queue<PPoint> points = new LinkedList<>();
        boolean[][][] visited = new boolean[N + 1][M + 1][K + 1];
        visited[1][1][0] = true;
        points.add(new PPoint(1, 1, 0));

        while (!points.isEmpty()) {
            int size = points.size();

            for (int i = 0; i < size; i++) {
                PPoint point = points.poll();
                if (point.x == M && point.y == N) {
                    return time;
                }
                for (int[] move : dxDy) {
                    int X = point.x + move[0];
                    int Y = point.y + move[1];
                    if (canVisit(X, Y, N, M)) {
                        if (map[Y][X] == WALL && point.brickBreak < K && !visited[Y][X][point.brickBreak + 1]) {
                            visited[Y][X][point.brickBreak + 1] = true;
                            points.add(new PPoint(X, Y, point.brickBreak + 1));
                        } else if (map[Y][X] == EMPTY && !visited[Y][X][point.brickBreak]) {
                            visited[Y][X][point.brickBreak] = true;
                            points.add(new PPoint(X, Y, point.brickBreak));
                        }
                    }
                }
            }
            time++;
        }

        return -1;
    }

    static boolean canVisit(int x, int y, int N, int M) {
        return (x >= 1 && y >= 1 && x <= M && y <= N);
    }

    static class PPoint {
        int x;
        int y;
        int brickBreak = 0;

        public PPoint(int x, int y, int brickBreak) {
            this.x = x;
            this.y = y;
            this.brickBreak = brickBreak;
        }
    }
}
