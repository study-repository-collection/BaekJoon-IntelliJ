package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P16929 {
    static char[][] map;
    static final int[][] dxDy = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String mapInfo = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = mapInfo.charAt(j);
            }
        }
        System.out.println(solution(N, M) ? "Yes" : "No");
    }


    static boolean solution(int N, int M) {
        boolean[][] visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (dfs(j, i, j, i, visited, 1, N, M)) {
                    return true;
                }
            }
        }

        return false;
    }

    static boolean dfs(int x, int y, int sx, int sy, boolean[][] visited, int count, int N, int M) {
        boolean ret = false;
        visited[y][x] = true;
        for (int[] move : dxDy) {
            int X = x + move[0];
            int Y = y + move[1];
            if (canVisit(X, Y, N, M) && map[Y][X] == map[y][x]) {
                if (Y == sy && x == sx && count >= 4) {
                    return true;
                } else if (!visited[Y][X]) {
                    ret |= dfs(X, Y, sx, sy, visited, count + 1, N, M);
                }
            }
        }
        visited[y][x] = false;
        return ret;
    }

    static boolean canVisit(int x, int y, int N, int M) {
        return x >= 0 && y >= 0 && x < M && y < N;
    }
}
