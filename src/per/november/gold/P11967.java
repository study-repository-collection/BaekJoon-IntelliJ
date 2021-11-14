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
import static java.lang.System.out;

public class P11967 {

    static int[][] dxDy = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    static ArrayList<Point>[][] switchMap;
    static boolean[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        switchMap = new ArrayList[N + 1][N + 1];
        map = new boolean[N + 1][N + 1];
        map[1][1] = true;
        while (M-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (null == switchMap[y][x]) {
                switchMap[y][x] = new ArrayList<>();
            }
            switchMap[y][x].add(new Point(b, a));
        }
        System.out.println(solution(N));
    }

    static int solution(int N) {
        boolean[][] visited = new boolean[N + 1][N + 1];
        int count = 1;
        Queue<Point> queue = new LinkedList<>();
        visited[1][1] = true;
        queue.add(new Point(1, 1));
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            if (null != switchMap[point.y][point.x]) {
                for (Point point1 : switchMap[point.y][point.x]) {
                    if (!map[point1.y][point1.x]) {
                        count++;
                        map[point1.y][point1.x] = true;
                        for (int[] move : dxDy) {
                            int X = point1.x + move[0];
                            int Y = point1.y + move[1];
                            if (canVisit(X, Y, N) && visited[Y][X]) {
                                queue.add(new Point(X, Y));
                            }
                        }
                    }
                }
            }
            for (int[] move : dxDy) {
                int X = point.x + move[0];
                int Y = point.y + move[1];
                if (canVisit(X, Y, N) && map[Y][X] && !visited[Y][X]) {
                    queue.add(new Point(X, Y));
                    visited[Y][X] = true;
                }
            }
        }
        return count;
    }

    static boolean canVisit(int x, int y, int N) {
        return (x >= 1 && y >= 1 && x <= N && y <= N);
    }
}
