package per.november.gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P2146 {
    static int[][] map;
    static final int LAND = 1;
    static final int SEA = 0;
    static final int[][] dxDy = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(input.nextToken());
            }
        }
        makeLand(N);
        System.out.println(solution(N));
    }


    static int solution(int N) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] > 1) {
                    min = Math.min(min, bfs(j, i, N, map[i][j]));
                }
            }
        }
        return min;
    }

    static int bfs(int x, int y, int N, int startLandNumber) {
        Queue<Point> points = new LinkedList<>();
        int count = 0;
        boolean[][] visited = new boolean[N][N];
        points.add(new Point(x, y));
        visited[y][x] = true;
        while (!points.isEmpty()) {
            int size = points.size();
            for (int i = 0; i < size; i++) {
                Point point = points.poll();
                for (int[] move : dxDy) {
                    int X = point.x + move[0];
                    int Y = point.y + move[1];
                    if (canVisit(X, Y, N) && !visited[Y][X]) {
                        if (map[Y][X] == SEA) {
                            visited[Y][X] = true;
                            points.add(new Point(X, Y));
                        } else if (map[Y][X] != startLandNumber) {
                            return count;
                        }
                    }
                }
            }
            count++;
        }
        return Integer.MAX_VALUE;
    }

    static void makeLand(int N) {
        int landNumber = 2;
        boolean[][] visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == LAND) {
                    decideMap(j, i, landNumber++, N, visited);
                }
            }
        }
    }

    static void decideMap(int x, int y, int landNumber, int N, boolean[][] visited) {
        visited[y][x] = true;
        map[y][x] = landNumber;
        for (int[] move : dxDy) {
            int X = x + move[0];
            int Y = y + move[1];
            if (canVisit(X, Y, N) && !visited[Y][X] && map[Y][X] == LAND) {
                decideMap(X, Y, landNumber, N, visited);
            }
        }
    }


    static boolean canVisit(int x, int y, int N) {
        return (x >= 0 && y >= 0 && x < N && y < N);
    }
}
