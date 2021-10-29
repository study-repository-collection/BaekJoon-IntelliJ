package per.jongho.gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P3055 {


    static int[][] dxDy = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    static int[][] map;
    static final int EMPTY = '.' + 5500;
    static final int FILL = '*' + 5500; //물은 비어있는 인접한 칸으로 확장한다.
    static final int STONE = 'X' + 5500; //비버와 물은 돌을 통과할 수 없다.
    static final int CAVE = 'D' + 5500; //비버의 굴이다. 고슴도치는 비버의 굴로 이동해야한다.
    static final int GOSOOM = 'S' + 5500; //고슴도치의 위치이다

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer mapInfo = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(mapInfo.nextToken());
        int C = Integer.parseInt(mapInfo.nextToken());
        map = new int[R][C];

        int goSoomX = 0;
        int goSoomY = 0;
        int caveX = 0;
        int caveY = 0;
        for (int i = 0; i < R; i++) {
            String string = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = string.charAt(j) + 5500;
                if (map[i][j] == GOSOOM) {
                    goSoomX = j;
                    goSoomY = i;
                } else if (map[i][j] == CAVE) {
                    caveX = j;
                    caveY = i;
                }
            }
        }
        //맵 전처리 실행
        initMap(R, C);
        int result;
        System.out.println((result = answer(goSoomX, goSoomY, caveX, caveY, R, C)) == -1 ? "KAKTUS" : result);
    }


    static int answer(int startX, int startY, int finishX, int finishY, int R, int C) {
        boolean[][] visited = new boolean[R][C];
        Queue<Point> points = new LinkedList<>();
        visited[startY][startX] = true;
        points.add(new Point(startX, startY));

        int count = 0;
        while (!points.isEmpty()) {
            int size = points.size();
            for (int i = 0; i < size; i++) {
                Point temp = points.poll();
                if (temp.x == finishX && temp.y == finishY) {
                    return count;
                }
                for (int j = 0; j < 4; j++) {
                    int X = temp.x + dxDy[j][0];
                    int Y = temp.y + dxDy[j][1];

                    if (canVisit(X, Y, R, C) && !visited[Y][X] && map[Y][X] != STONE && map[Y][X] != FILL && (map[Y][X] == CAVE || count < map[Y][X] - 1 || map[Y][X] == EMPTY)) {
                        visited[Y][X] = true;
                        points.add(new Point(X, Y));
                    }
                }
            }
            count++;
        }


        return -1;
    }

    //맵의 빈 곳이 몇 턴 뒤에 물에 찰 지 전처리를 실행
    static void initMap(int R, int C) {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == EMPTY) {
                    map[i][j] = nearestWater(j, i, R, C);
                    if (map[i][j] == -1) {
                        map[i][j] = EMPTY;
                    }
                }
            }
        }
    }

    static int nearestWater(int x, int y, int R, int C) {
        boolean[][] visited = new boolean[R][C];
        Queue<Point> points = new LinkedList<>();
        visited[y][x] = true;
        points.add(new Point(x, y));
        int count = 0;
        while (!points.isEmpty()) {
            int size = points.size();

            for (int i = 0; i < size; i++) {
                Point temp = points.poll();
                if (map[temp.y][temp.x] == FILL) {
                    return count;
                }
                for (int j = 0; j < 4; j++) {
                    int X = temp.x + dxDy[j][0];
                    int Y = temp.y + dxDy[j][1];
                    if (canVisit(X, Y, R, C) && !visited[Y][X] && map[Y][X] != STONE && map[Y][X] != CAVE) {
                        visited[Y][X] = true;
                        points.add(new Point(X, Y));
                    }
                }
            }
            count++;
        }
        return -1;
    }

    static boolean canVisit(int x, int y, int R, int C) {
        return (x >= 0 && y >= 0 && x < C && y < R);
    }


}
