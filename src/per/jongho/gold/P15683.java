package per.jongho.gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P15683 {
    static int[][] map;
    static final int WALL = 6;
    static final ArrayList<CCTV> cctvs = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stringTokenizer.nextToken()); //맵 높이
        int M = Integer.parseInt(stringTokenizer.nextToken()); //맵 길이

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            StringTokenizer mapInfo = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(mapInfo.nextToken());
            }
        }
        solution(map, N, M);
        System.out.println(min);
    }

    static void solution(int[][] map, int N, int M) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] >= 1 && map[i][j] < 6) {
                    cctvs.add(new CCTV(j, i, map[i][j]));
                }
            }
        }
        bruteForce(0, N, M);
    }

    static void bruteForce(int index, int N, int M) {
        if (index == cctvs.size()) {
            calculateEmptySpace(map, N, M);
            return;
        }
        CCTV cctv = cctvs.get(index);
        switch (cctv.number) {
            case 1:
                for (int i = 0; i < 4; i++) {
                    makeMap(i, map, N, M, cctv.point, true);
                    bruteForce(index + 1, N, M);
                    makeMap(i, map, N, M, cctv.point, false);
                }
                break;
            case 2:
                for (int i = 0; i < 2; i++) {
                    makeMap(i, map, N, M, cctv.point, true);
                    makeMap(i + 2, map, N, M, cctv.point, true);
                    bruteForce(index + 1, N, M);
                    makeMap(i, map, N, M, cctv.point, false);
                    makeMap(i + 2, map, N, M, cctv.point, false);
                }
                break;
            case 3:
                for (int i = 0; i < 4; i++) {
                    makeMap(i, map, N, M, cctv.point, true);
                    makeMap((i + 1) % 4, map, N, M, cctv.point, true);
                    bruteForce(index + 1, N, M);
                    makeMap(i, map, N, M, cctv.point, false);
                    makeMap((i + 1) % 4, map, N, M, cctv.point, false);
                }
                break;
            case 4:
                for (int i = 0; i < 4; i++) {
                    makeMap(i, map, N, M, cctv.point, true);
                    makeMap((i + 1) % 4, map, N, M, cctv.point, true);
                    makeMap((i + 3) % 4, map, N, M, cctv.point, true);
                    bruteForce(index + 1, N, M);
                    makeMap(i, map, N, M, cctv.point, false);
                    makeMap((i + 1) % 4, map, N, M, cctv.point, false);
                    makeMap((i + 3) % 4, map, N, M, cctv.point, false);
                }
                break;
            case 5:
                for (int i = 0; i < 4; i++) {
                    makeMap(i, map, N, M, cctv.point, true);
                }
                bruteForce(index + 1, N, M);
                break;
        }
    }

    static final int NORTH = 0;
    static final int EAST = 1;
    static final int SOUTH = 2;
    static final int WEST = 3;
    static final int SUPERVISED = 10;

    static void makeMap(int direction, int[][] map, int N, int M, Point point, boolean fill) {
        int x = point.x;
        int y = point.y;
        while (true) {
            switch (direction) {
                case NORTH:
                    y -= 1;
                    break;
                case EAST:
                    x += 1;
                    break;
                case SOUTH:
                    y += 1;
                    break;
                case WEST:
                    x -= 1;
                    break;
            }

            if (canGo(map, x, y, N, M)) {
                if (fill) {
                    if (map[y][x] >= SUPERVISED) {
                        map[y][x]++;
                    } else if (map[y][x] == 0) {
                        map[y][x] = SUPERVISED;
                    }
                } else {
                    if (map[y][x] > SUPERVISED) {
                        map[y][x]--;
                    } else if (map[y][x] == SUPERVISED) {
                        map[y][x] = 0;
                    }
                }
            } else {
                break;
            }
        }
    }


    static int min = 65;

    static void calculateEmptySpace(int[][] map, int N, int M) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    count++;
                }
            }
        }
        min = Math.min(count, min);
    }

    static boolean canGo(int[][] map, int X, int Y, int N, int M) {
        return (X >= 0 && Y >= 0 && Y < N && X < M && map[Y][X] != WALL);
    }

    static class CCTV {
        final Point point;
        final int number;

        public CCTV(int x, int y, int number) {
            point = new Point(x, y);
            this.number = number;
        }
    }
}
