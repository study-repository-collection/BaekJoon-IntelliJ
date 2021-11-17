package per.november.gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P2589 {
    static char[][] map;
    static final char LAND = 'L';
    static final char WATER = 'W';
    static final int[][] dxDy = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            String input2 = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input2.charAt(j);
            }
        }
        System.out.println(solution(N, M));
    }

    static int solution(int N, int M) {
        int max = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == LAND) {
                    max = Math.max(max, bfs(j, i, N, M));
                }
            }
        }
        return max;
    }


    static int bfs(int x, int y, int N, int M) {
        boolean[][] visited = new boolean[N][M];
        Queue<Point> points = new LinkedList<>();
        visited[y][x] = true;
        int count = 0;
        points.add(new Point(x, y));
        while (!points.isEmpty()) {
            int size = points.size();
            for (int i = 0; i < size; i++) {
                Point point = points.poll();
                for (int[] move : dxDy) {
                    int X = point.x + move[0];
                    int Y = point.y + move[1];
                    if (canVisit(X, Y, N, M) && !visited[Y][X] && map[Y][X] == LAND) {
                        visited[Y][X] = true;
                        points.add(new Point(X, Y));
                    }
                }
            }
            count++;
        }
        return count - 1;
    }

    static boolean canVisit(int x, int y, int N, int M) {
        return (x >= 0 && y >= 0 && x < M && y < N);
    }
}
