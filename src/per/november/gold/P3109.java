package per.november.gold;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P3109 {
    static char[][] map;
    static final char EMPTY = '.';
    static final char PIPE = '-';
    static final char BUILDING = 'x';

    static int[][] dxDy = {{1, -1}, {1, 0}, {1, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer mapInfo = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(mapInfo.nextToken());
        int C = Integer.parseInt(mapInfo.nextToken());
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = input.charAt(j);
            }
        }
        System.out.println(solution(R, C));
    }

    static int solution(int R, int C) {
        int ret = 0;
        boolean[][] visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            canReach(R, C, new Point(0, i), visited);
            if (findRoute) {
                findRoute = false;
                ret++;
            }
        }
        return ret;
    }

    static boolean findRoute = false;

    static void canReach(int R, int C, Point point, boolean[][] visited) {
        if (findRoute) return;
        if (point.x == C - 1) {
            visited[point.y][point.x] = true;
            findRoute = true;
            return;
        }
        visited[point.y][point.x] = true;
        for (int[] move : dxDy) {
            int X = point.x + move[0];
            int Y = point.y + move[1];
            if (canVisit(Y, R) && map[Y][X] == EMPTY && !visited[Y][X]) {
                canReach(R, C, new Point(X, Y), visited);
            }
        }
    }

    static boolean canVisit(int y, int R) {
        return y >= 0 && y < R;
    }
}
