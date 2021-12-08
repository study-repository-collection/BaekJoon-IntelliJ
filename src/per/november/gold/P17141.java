package per.november.gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P17141 {
    static int[][] map;
    static final int EMPTY = 0;
    static final int WALL = 1;
    static final int VIRUS = 2;
    static ArrayList<Point> points = new ArrayList<>();
    static final int[][] dxDy = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(input.nextToken());
                if (map[i][j] == VIRUS) {
                    points.add(new Point(j, i));
                }
            }
        }
        determine(M, 0, 0, new Point[M], N);
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    static int answer = Integer.MAX_VALUE;

    static void determine(int M, int count, int start, Point[] virus, int N) {
        //모든 바이러스의 위치를 정했으면,
        if (count == M) {
            int ret = bfs(copyMap(N), virus, N);
            if (ret != -1) {
                answer = Math.min(answer, ret);
            }
            return;
        }
        for (int i = start; i < points.size(); i++) {
            virus[count] = points.get(i);
            determine(M, count + 1, i + 1, virus, N);
        }
    }

    static int[][] copyMap(int N) {
        int[][] copiedMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copiedMap[i][j] = map[i][j];
            }
        }
        return copiedMap;
    }

    static int bfs(int[][] map, Point[] viruses, int N) {
        Queue<Point> points = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        int count = -1;
        for (Point point : viruses) {
            points.add(point);
            visited[point.y][point.x] = true;
            map[point.y][point.x] = 3;
        }

        while (!points.isEmpty()) {
            int size = points.size();
            for (int i = 0; i < size; i++) {
                Point point = points.poll();
                for (int[] move : dxDy) {
                    int X = point.x + move[0];
                    int Y = point.y + move[1];
                    if (canVisit(X, Y, N) && (map[Y][X] == EMPTY || map[Y][X] == VIRUS) && !visited[Y][X]) {
                        visited[Y][X] = true;
                        map[Y][X] = 3;
                        points.add(new Point(X, Y));
                    }
                }
            }
            count++;
        }
        if (isFill(map, N)) {
            return count;
        } else {
            return -1;
        }
    }

    static boolean isFill(int[][] map, int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean canVisit(int x, int y, int N) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }
}
