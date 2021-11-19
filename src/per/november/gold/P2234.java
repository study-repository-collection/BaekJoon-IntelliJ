package per.november.gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P2234 {
    static int[][] map;
    static Point[][] size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer mapInfo = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(mapInfo.nextToken());
        int N = Integer.parseInt(mapInfo.nextToken());
        map = new int[N][M];
        size = new Point[N][M];
        for (int i = 0; i < N; i++) {
            StringTokenizer mapInfos = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(mapInfos.nextToken());
            }
        }
        solution(N, M);
    }

    static void solution(int N, int M) {
        boolean[][] visited = new boolean[N][M];
        int countOfRoom = 0;
        int maxRoomSize = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    countOfRoom++;
                    maxRoomSize = Math.max(maxRoomSize, bfs(visited, j, i, countOfRoom));
                }
            }
        }
        System.out.println(countOfRoom);
        System.out.println(maxRoomSize);
        System.out.println(sumTwoRoom(N, M));
    }

    static int sumTwoRoom(int N, int M) {
        int max = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if ((map[i][j] & 8) == 8 && i + 1 < N && size[i][j].x != size[i + 1][j].x) {
                    max = Math.max(size[i][j].y + size[i + 1][j].y, max);
                }
                if ((map[i][j] & 4) == 4 && j + 1 < M && size[i][j].x != size[i][j + 1].x) {
                    max = Math.max(size[i][j].y + size[i][j + 1].y, max);
                }
                if ((map[i][j] & 2) == 2 && i - 1 >= 0 && size[i][j].x != size[i - 1][j].x) {
                    max = Math.max(size[i][j].y + size[i - 1][j].y, max);
                }
                if ((map[i][j] & 1) == 1 && j - 1 >= 0 && size[i][j].x != size[i][j - 1].x) {
                    max = Math.max(size[i][j].y + size[i][j - 1].y, max);
                }

            }
        }
        return max;
    }

    static int bfs(boolean[][] visited, int x, int y, int count) {
        visited[y][x] = true;
        Queue<Point> points = new LinkedList<>();
        LinkedList<Point> list = new LinkedList<>();
        int sizeZ = 0;
        points.add(new Point(x, y));

        while (!points.isEmpty()) {
            Point point = points.poll();
            list.add(point);
            sizeZ++;
            if ((map[point.y][point.x] & 8) != 8 && !visited[point.y + 1][point.x]) {
                visited[point.y + 1][point.x] = true;
                points.add(new Point(point.x, point.y + 1));
            }
            if ((map[point.y][point.x] & 4) != 4 && !visited[point.y][point.x + 1]) {
                visited[point.y][point.x + 1] = true;
                points.add(new Point(point.x + 1, point.y));
            }
            if ((map[point.y][point.x] & 2) != 2 && !visited[point.y - 1][point.x]) {
                visited[point.y - 1][point.x] = true;
                points.add(new Point(point.x, point.y - 1));
            }
            if ((map[point.y][point.x] & 1) != 1 && !visited[point.y][point.x - 1]) {
                visited[point.y][point.x - 1] = true;
                points.add(new Point(point.x - 1, point.y));
            }
        }
        for (Point point : list) {
            size[point.y][point.x] = new Point(count, sizeZ);
        }
        return sizeZ;
    }
}
