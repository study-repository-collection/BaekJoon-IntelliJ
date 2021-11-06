package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P16933 {

    static int[][] dxDy = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};


    static int[][] map;

    static final int WALL = 1;
    static final int EMPTY = 0;

    static final int Day = 0;
    static final int Night = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken()); //맵의 높이
        int M = Integer.parseInt(input.nextToken()); //맵의 길이
        int K = Integer.parseInt(input.nextToken()); //벽을 부술 수 있는 개수

        map = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            String mapInfo = br.readLine();
            for (int j = 1; j <= M; j++) {
                map[i][j] = mapInfo.charAt(j - 1) - '0' ;
            }
        }

        System.out.println(solution(N, M, K));
    }

    static int solution(int N, int M, int K) {
        boolean[][][][] visited = new boolean[N + 1][M + 1][K + 1][2];
        visited[1][1][0][Day] = true;
        Queue<Info> points = new LinkedList<>();
        points.add(new Info(1, 1, 0));
        int time = 1;
        int currentDay = Day;
        while (!points.isEmpty()) {
            int size = points.size();
            int nextDay = 1 - currentDay;
            for (int i = 0; i < size; i++) {
                Info info = points.poll();
                if (info.y == N && info.x == M) return time;
                for (int[] move : dxDy) {
                    int X = info.x + move[0];
                    int Y = info.y + move[1];
                    if (canVisit(X, Y, N, M)) {
                        if (map[Y][X] == EMPTY && !visited[Y][X][info.wallCount][nextDay]) {
                            visited[Y][X][info.wallCount][nextDay] = true;
                            points.add(new Info(X, Y, info.wallCount));
                        } else if (map[Y][X] == WALL && currentDay == Day && info.wallCount + 1 <= K && !visited[Y][X][info.wallCount + 1][1]) {
                            visited[Y][X][info.wallCount + 1][1] = true;
                            points.add(new Info(X, Y, info.wallCount + 1));
                        }
                    }
                }
                if (currentDay == Night && !visited[info.y][info.x][info.wallCount][nextDay]) {
                    visited[info.y][info.x][info.wallCount][nextDay] = true;
                    points.add(info);
                }
            }
            time++;
            currentDay = nextDay;
        }


        return -1;
    }

    static boolean canVisit(int x, int y, int N, int M) {
        return (x >= 1 && y >= 1 && x <= M && y <= N);
    }

    static class Info {
        final int x;
        final int y;
        final int wallCount;

        public Info(int x, int y, int wallCount) {
            this.x = x;
            this.y = y;
            this.wallCount = wallCount;
        }
    }
}
