package per.jongho.gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P20058 {
    static int[][] map;
    static Queue<Integer> fireStorms = new LinkedList<>();

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();


        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken()); //맵의 크기
        int Q = Integer.parseInt(input.nextToken()); //파이어스톰을 시전한 횟수
        map = new int[(int) Math.pow(2, N)][(int) Math.pow(2, N)];

        for (int i = 0; i < Math.pow(2, N); i++) {
            StringTokenizer mapInfo = new StringTokenizer(br.readLine());
            for (int j = 0; j < Math.pow(2, N); j++) {
                map[i][j] = Integer.parseInt(mapInfo.nextToken());
            }
        }

        StringTokenizer fireStromInfo = new StringTokenizer(br.readLine());

        while (fireStromInfo.hasMoreTokens()) {
            fireStorms.add(Integer.parseInt(fireStromInfo.nextToken()));
        }

        while (!fireStorms.isEmpty()) {
            fireStorm(fireStorms.poll());
        }
        answer();
    }

    static void fireStorm(int L) {
        int divisionSize = (int) Math.pow(2, L);
        for (int i = 0; i < map.length; i += divisionSize) {
            for (int j = 0; j < map.length; j += divisionSize) {
                rotate90Degree(j, i, L);
            }
        }
        int[][] melting = new int[map.length][map.length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                melting[i][j] = melting(j, i);
            }
        }
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                map[i][j] -= melting[i][j];
            }
        }
    }

    static int melting(int x, int y) {
        if (map[y][x] == 0) return 0;
        int sum = 0;
        for (int i = 0; i < 4; i++) {
            int X = x + dx[i];
            int Y = y + dy[i];
            if (canGo(X, Y, map.length) && map[Y][X] > 0) {
                sum++;
            }
        }
        if (sum < 3) {
            return 1;
        } else {
            return 0;
        }
    }

    static int[][] copyArr(int startX, int startY, int size) {
        int[][] copy = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                copy[i][j] = map[i + startY][j + startX];
            }
        }
        return copy;
    }

    static void answer() {
        boolean[][] visited = new boolean[map.length][map.length];
        int iceSum = 0;
        int maxPart = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                iceSum += map[i][j];
                if (map[i][j] > 0 && !visited[i][j]) {
                    maxPart = Math.max(maxPart, bfs(j, i, visited));
                }
            }
        }
        System.out.println(iceSum);
        System.out.println(maxPart);
    }

    static int bfs(int x, int y, boolean[][] visited) {
        int sum = 0;
        Queue<Point> points = new LinkedList<>();

        points.add(new Point(x, y));
        visited[y][x] = true;

        while (!points.isEmpty()) {
            Point point = points.poll();
            sum += 1;
            for (int i = 0; i < 4; i++) {
                int X = point.x + dx[i];
                int Y = point.y + dy[i];
                if (canGo(X, Y, map.length) && !visited[Y][X] && map[Y][X] > 0) {
                    visited[Y][X] = true;
                    points.add(new Point(X, Y));
                }
            }
        }
        return sum;
    }

    static boolean canGo(int x, int y, int N) {
        return (x >= 0 && y >= 0 && x < N && y < N);
    }

    static void rotate90Degree(int startX, int startY, int L) {
        int realSize = (int) Math.pow(2, L);

        int[][] temp = copyArr(startX, startY, realSize);
        for (int i = startY; i < startY + realSize; i++) {
            for (int j = startX; j < startX + realSize; j++) {
                map[i][j] = temp[realSize - 1 - (j - startX)][i - startY];
            }
        }
    }
}
