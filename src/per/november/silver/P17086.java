package per.november.silver;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P17086 {
    static int[][] dxDy = {{-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};
    static int[][] map;
    static final int EMPTY = 0;
    static final int SHARK = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            StringTokenizer mapInfo = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(mapInfo.nextToken());

            }
        }
        System.out.println(solution(N, M));
    }

    static int solution(int N, int M) {
        int max = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == EMPTY) {
                    max = Math.max(max, bfs(j, i, N, M));
                }
            }
        }
        return max;
    }

    static int bfs(int x, int y, int N, int M) {
        Queue<Point> points = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        visited[y][x] = true;
        int count = 0;
        points.add(new Point(x, y));
        while (!points.isEmpty()) {
            int size = points.size();
            for (int i = 0; i < size; i++) {
                Point point = points.poll();
                if (map[point.y][point.x] == SHARK) {
                    return count;
                }
                for (int[] move : dxDy) {
                    int X = point.x + move[0];
                    int Y = point.y + move[1];
                    if (canVisit(X, Y, N, M) && !visited[Y][X]) {
                        visited[Y][X] = true;
                        points.add(new Point(X, Y));
                    }
                }
            }
            count++;
        }
        return count;
    }

    static boolean canVisit(int x, int y, int N, int M) {
        return (x >= 0 && y >= 0 && x < M && y < N);
    }
}
