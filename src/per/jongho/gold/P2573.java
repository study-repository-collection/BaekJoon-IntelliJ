package per.jongho.gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P2573 {
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer mapSize = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(mapSize.nextToken());
        int M = Integer.parseInt(mapSize.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(input.nextToken());
            }
        }
        //입력 종료
        System.out.println(solution(N, M));
    }

    static int[][] copyMap(int N, int M) {
        int[][] copiedMap = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copiedMap[i][j] = map[i][j];
            }
        }
        return copiedMap;
    }

    static int solution(int N, int M) {
        int year = 0;
        while (true) {
            int count = 0;
            if (!after1Year(N, M)) return 0;
            year++;
            int[][] copiedMap = copyMap(N, M);
            for (int i = 1; i < N - 1; i++) {
                for (int j = 1; j < M - 1; j++) {
                    if (copiedMap[i][j] > 0) {
                        count++;
                        dfs(new Point(j, i), N, M, copiedMap);
                    }
                }
            }
            if (count >= 2) return year;
        }
    }

    static boolean after1Year(int N, int M) {
        boolean isRemain = false;
        int[][] decent = new int[N][M];
        for (int i = 1; i < N - 1; i++) {
            for (int j = 1; j < M - 1; j++) {
                if (map[i][j] > 0) {
                    isRemain = true;
                    int count = 0;
                    for (int k = 0; k < 4; k++) {
                        int X = j + dx[k];
                        int Y = i + dy[k];
                        if (map[Y][X] == 0) {
                            count++;
                        }
                    }
                    decent[i][j] = count;
                }
            }
        }

        for (int i = 1; i < N - 1; i++) {
            for (int j = 1; j < M - 1; j++) {
                if (map[i][j] > 0) {
                    map[i][j] -= decent[i][j];
                    map[i][j] = Math.max(map[i][j], 0);
                }
            }
        }
        return isRemain;
    }

    static final int VISITED = -1;

    static void dfs(Point point, int N, int M, int[][] copiedMap) {
        copiedMap[point.y][point.x] = VISITED;
        for (int i = 0; i < 4; i++) {
            int X = point.x + dx[i];
            int Y = point.y + dy[i];
            if (canGo(X, Y, N, M, copiedMap)) {
                dfs(new Point(X, Y), N, M, copiedMap);
            }
        }
    }

    static boolean canGo(int x, int y, int N, int M, int[][] copiedMap) {
        return (x >= 0 && y >= 0 && x < M && y < N && copiedMap[y][x] > 0);
    }

}
