package per.october.gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P3197 {

    static final int WATER = 0;
    static final int ICE = -1;
    static final int SWAN = -10;

    static int[][] map;
    static Queue<Point> waters = new LinkedList<>();

    static Queue<Point> swans = new LinkedList<>();
    static boolean[][] visited;
    static final int[][] dxDy = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    static Point swan1;
    static Point swan2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer mapInfo = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(mapInfo.nextToken());
        int C = Integer.parseInt(mapInfo.nextToken());
        map = new int[R][C];
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                switch (input.charAt(j)) {
                    case '.':
                        map[i][j] = WATER;
                        waters.add(new Point(j, i));
                        break;
                    case 'X':
                        map[i][j] = ICE;
                        break;
                    case 'L':
                        map[i][j] = SWAN;
                        waters.add(new Point(j, i));
                        if (null == swan1) {
                            swan1 = new Point(j, i);
                            visited[i][j] = true;
                            swans.add(swan1);
                        } else {
                            swan2 = new Point(j, i);
                        }
                        break;
                }
            }
        }
        int count = 0;
        while (!bfs(R, C)) {
            melt(R, C);
            count++;
        }
        System.out.println(count);
    }

    static boolean bfs(int R, int C) {
        ArrayList<Point> breakingPoint = new ArrayList<>();

        while (!swans.isEmpty()) {
            Point point = swans.poll();
            for (int i = 0; i < 4; i++) {
                int X = point.x + dxDy[i][0];
                int Y = point.y + dxDy[i][1];
                if (canVisit(X, Y, R, C) && !visited[Y][X]) {
                    if (map[Y][X] == ICE) {
                        breakingPoint.add(new Point(point.x, point.y));
                    } else if (map[Y][X] == WATER) {
                        visited[Y][X] = true;
                        swans.add(new Point(X, Y));
                    } else {
                        return true;
                    }
                }
            }
        }
        swans.addAll(breakingPoint);
        return false;
    }

    static void melt(int R, int C) {


        int size = waters.size();
        for (int i = 0; i < size; i++) {
            Point point = waters.poll();
            for (int j = 0; j < 4; j++) {
                int X = point.x + dxDy[j][0];
                int Y = point.y + dxDy[j][1];
                if (canVisit(X, Y, R, C) && map[Y][X] == ICE) {
                    map[Y][X] = WATER;
                    waters.add(new Point(X, Y));
                }
            }
        }
    }

    static boolean canVisit(int x, int y, int R, int C) {
        return (x >= 0 && y >= 0 && x < C && y < R);
    }
}
