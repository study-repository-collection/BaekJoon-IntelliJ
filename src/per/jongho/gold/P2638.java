package per.jongho.gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P2638 {
    static int[][] map;

    static final int CHEESE = 1;
    static final int INTERNAL_AIR = 2;
    static final int TEMP_MELT = 3;
    static final int AIR = 0;

    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            StringTokenizer mapInfo = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(mapInfo.nextToken());
            }
        }
        //입력 종료
        int count = 0;
        while (!allMelt(N, M)) {
            count++;
            findInternalAir(N, M);
            melt(N, M);
            initInternalAir(N, M);
        }

        System.out.println(count);

    }

    static boolean allMelt(int N, int M) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == CHEESE) {
                    return false;
                }
            }
        }
        return true;
    }

    static void melt(int N, int M) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == CHEESE) {
                    int count = 0;
                    for (int k = 0; k < 4; k++) {
                        int X = j + dx[k];
                        int Y = i + dy[k];
                        if (canVisit(X, Y, N, M) && map[Y][X] == AIR) {
                            count++;
                        }
                    }
                    if (count >= 2) {
                        map[i][j] = TEMP_MELT;
                    }
                }
            }
        }
    }

    static void initInternalAir(int N, int M) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == INTERNAL_AIR || map[i][j] == TEMP_MELT) {
                    map[i][j] = AIR;
                }
            }
        }
    }

    static void findInternalAir(int N, int M) {
        boolean[][] visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == AIR && !visited[i][j]) {
                    bfs(N, M, visited, j, i);
                }
            }
        }
    }

    //해당 지역의 공기가 외부공기 인지, 치즈 내부의 공기인지 확인하는 함수
    static void bfs(int N, int M, boolean[][] visited, int x, int y) {
        ArrayList<Point> savePoint = new ArrayList<>();
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));
        visited[y][x] = true;

        boolean isInternal = true;
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            savePoint.add(point);

            for (int i = 0; i < 4; i++) {
                int X = point.x + dx[i];
                int Y = point.y + dy[i];
                if (canVisit(X, Y, N, M)) {
                    if (map[Y][X] == AIR && !visited[Y][X]) {
                        visited[Y][X] = true;
                        queue.add(new Point(X, Y));
                    }
                } else {
                    isInternal = false;
                }
            }
        }
        if (isInternal) {
            makeInternalAir(savePoint);
        }

    }

    static void makeInternalAir(ArrayList<Point> points) {
        for (Point point : points) {
            map[point.y][point.x] = INTERNAL_AIR;
        }
    }

    static boolean canVisit(int x, int y, int N, int M) {
        return (x >= 0 && y >= 0 && x < M && y < N);
    }

}
