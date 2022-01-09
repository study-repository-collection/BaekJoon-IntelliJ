package per.november.gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

public class P18809 {

    static final int HOSU = 10000;
    static final int CANT = 10001;
    static final int CAN = 10002;

    static final int FLOWER = 10003;
    static int[][] map;

    static final int[][] dxDy = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    static ArrayList<Point> canPosition = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        int G = Integer.parseInt(input.nextToken()); //초록색 배양액의 개수
        int R = Integer.parseInt(input.nextToken()); //빨간색 배양액의 개수
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            StringTokenizer mapInfo = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(mapInfo.nextToken()) + 10000;
                if (map[i][j] == CAN) {
                    canPosition.add(new Point(j, i));
                }
            }
        }
        System.out.println(solution(N, M, G, R));
    }

    static int max = 0;

    static int solution(int N, int M, int G, int R) {
        choose(G, R, new Point[G], new Point[R], new boolean[canPosition.size()], N, M, 0);
        return max;
    }


    static void choose(int G, int R, Point[] green, Point[] red, boolean[] use, int N, int M, int start) {
        if (G == 0 && R == 0) {
            max = Math.max(max, calculate(green, red, N, M));
            return;
        }

        for (int i = start; i < canPosition.size(); i++) {
            if (!use[i]) {
                use[i] = true;
                if (G > 0) {
                    green[G - 1] = canPosition.get(i);
                    choose(G - 1, R, green, red, use, N, M, i + 1);
                }
                if (R > 0) {
                    red[R - 1] = canPosition.get(i);
                    choose(G, R - 1, green, red, use, N, M, i + 1);
                }
                use[i] = false;
            }
        }
    }


    static int time = 1;

    static int calculate(Point[] green, Point[] red, int N, int M) {
        boolean[][] visited = new boolean[N][M];
        int flowerCount = 0;
        Queue<Point> greenPoints = new LinkedList<>();
        Queue<Point> redPoints = new LinkedList<>();
        for (Point it : green) {
            visited[it.y][it.x] = true;
            greenPoints.add(it);
            map[it.y][it.x] = time - 1;
        }
        for (Point it : red) {
            visited[it.y][it.x] = true;
            redPoints.add(it);
            map[it.y][it.x] = time - 1;
        }

        while (!greenPoints.isEmpty() && !redPoints.isEmpty()) {
            int size = greenPoints.size();
            for (int i = 0; i < size; i++) {
                Point temp = greenPoints.poll();
                if (map[temp.y][temp.x] == FLOWER) {
                    continue;
                }
                for (int[] move : dxDy) {
                    int X = temp.x + move[0];
                    int Y = temp.y + move[1];
                    if (canVisit(X, Y, N, M) && !visited[Y][X] && map[Y][X] != HOSU) {
                        visited[Y][X] = true;
                        greenPoints.add(new Point(X, Y));
                        map[Y][X] = time;
                    }
                }
            }


            size = redPoints.size();
            for (int i = 0; i < size; i++) {
                Point temp = redPoints.poll();
                for (int[] move : dxDy) {
                    int X = temp.x + move[0];
                    int Y = temp.y + move[1];
                    if (canVisit(X, Y, N, M)) {
                        if (!visited[Y][X] && map[Y][X] != HOSU) {
                            visited[Y][X] = true;
                            map[Y][X] = 0;
                            redPoints.add(new Point(X, Y));
                        } else if (map[Y][X] == time) {
                            map[Y][X] = FLOWER;
                            flowerCount++;
                        }
                    }
                }
            }
            time++;
        }
        time -= 5000;
        return flowerCount;
    }

    static boolean canVisit(int x, int y, int N, int M) {
        return x >= 0 && y >= 0 && x < M && y < N;
    }

}
