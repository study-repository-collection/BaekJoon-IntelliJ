package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1726 {
    static int[][] dxDy = {{}, {1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static final int ROAD = 0;
    static final int CANT = 1;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(input.nextToken());
            }
        }

        StringTokenizer startInfo = new StringTokenizer(br.readLine());
        int sy = Integer.parseInt(startInfo.nextToken())-1;
        int sx = Integer.parseInt(startInfo.nextToken())-1;
        int sd = Integer.parseInt(startInfo.nextToken());

        Point startPoint = new Point(sx, sy, sd);
        StringTokenizer finishInfo = new StringTokenizer(br.readLine());
        int fy = Integer.parseInt(finishInfo.nextToken())-1;
        int fx = Integer.parseInt(finishInfo.nextToken())-1;
        int fd = Integer.parseInt(finishInfo.nextToken());
        Point endPoint = new Point(fx, fy, fd);

        System.out.println(solution(startPoint, endPoint, N, M));
    }

    static boolean canVisit(int x, int y, int N, int M) {
        return x >= 0 && y >= 0 && x < M && y < N;
    }

    static int solution(Point startPoint, Point endPoint, int N, int M) {
        Queue<Point> points = new LinkedList<>();
        points.add(startPoint);
        boolean[][][] visited = new boolean[N][M][5];
        visited[startPoint.y][startPoint.x][startPoint.direction] = true;
        int count = 0;

        while (!points.isEmpty()) {
            int size = points.size();
            for (int i = 0; i < size; i++) {
                Point point = points.poll();
                if (point.equal(endPoint)) {
                    return count;
                }
                for (int j = 1; j <= 3; j++) {
                    int X = point.x + (dxDy[point.direction][0] * j);
                    int Y = point.y + (dxDy[point.direction][1] * j);
                    if (canVisit(X, Y, N, M) && map[Y][X] == ROAD) {
                        if (!visited[Y][X][point.direction]) {
                            visited[Y][X][point.direction] = true;
                            points.add(new Point(X, Y, point.direction));
                        }
                    } else {
                        break;
                    }
                }

                if (point.direction == 1 || point.direction == 2) {
                    if (!visited[point.y][point.x][3]) {
                        visited[point.y][point.x][3] = true;
                        points.add(new Point(point.x, point.y, 3));
                    }
                    if (!visited[point.y][point.x][4]) {
                        visited[point.y][point.x][4] = true;
                        points.add(new Point(point.x, point.y, 4));
                    }
                } else {
                    if (!visited[point.y][point.x][1]) {
                        visited[point.y][point.x][1] = true;
                        points.add(new Point(point.x, point.y, 1));
                    }
                    if (!visited[point.y][point.x][2]) {
                        visited[point.y][point.x][2] = true;
                        points.add(new Point(point.x, point.y, 2));
                    }
                }
            }
            count++;
        }
        return count;
    }

    static class Point {
        int x;
        int y;
        int direction;

        public Point(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        public boolean equal(Point other) {
            return this.y == other.y && this.x == other.x && this.direction == other.direction;
        }
    }
}
