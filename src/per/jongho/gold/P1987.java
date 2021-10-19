package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1987 {
    static char[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static boolean[] visited = new boolean[26];

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
        visited[map[0][0] - 'A'] = true;
        backTracking(R, C, 0, 0, 1);
        System.out.println(max);
    }

    static int max = -1;

    static void backTracking(int R, int C, int x, int y, int count) {
        max = Math.max(max, count);
        for (int i = 0; i < 4; i++) {
            int X = x + dx[i];
            int Y = y + dy[i];
            if (canVisit(X, Y, R, C) && !visited[map[Y][X] - 'A']) {
                visited[map[Y][X] - 'A'] = true;
                backTracking(R, C, X, Y, count + 1);
                visited[map[Y][X] - 'A'] = false;
            }
        }
    }

    static boolean canVisit(int x, int y, int R, int C) {
        return (x >= 0 && y >= 0 && x < C && y < R);
    }
}
