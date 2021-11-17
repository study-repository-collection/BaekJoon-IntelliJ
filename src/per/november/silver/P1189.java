package per.november.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1189 {
    static char[][] map;
    static final char WALL = 'T';
    static final char EMPTY = '.';
    static final int[][] dxDY = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = input.charAt(j);
            }
        }
        boolean[][] visited = new boolean[R][C];
        visited[R - 1][0] = true;
        System.out.println(solution(0, R - 1, 1, visited, K, R, C));
    }

    static int solution(int x, int y, int count, boolean[][] visited, int K, int R, int C) {
        if (count == K) {
            if (x == C - 1 && y == 0) {
                return 1;
            } else {
                return 0;
            }
        }
        int sum = 0;
        for (int[] move : dxDY) {
            int X = x + move[0];
            int Y = y + move[1];
            if (canVisit(X, Y, R, C) && !visited[Y][X] && map[Y][X] == EMPTY) {
                visited[Y][X] = true;
                sum += solution(X, Y, count + 1, visited, K, R, C);
                visited[Y][X] = false;
            }
        }
        return sum;
    }

    static boolean canVisit(int x, int y, int R, int C) {
        return (x >= 0 && y >= 0 && x < C && y < R);
    }
}
