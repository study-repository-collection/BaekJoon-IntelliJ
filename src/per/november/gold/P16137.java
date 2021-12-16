package per.november.gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P16137 {

    static final int NORMAL = 1;
    static final int WALL = 0;
    static final int OJACKGYO = 2;

    static int[][] map;
    static int[][] dxDy = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken()); //행과 열의 크기
        int M = Integer.parseInt(input.nextToken()); //새로 만들어지는 오작교의 주기
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solution(N, M));
    }

    static int solution(int N, int M) {
        Queue<Point> points = new LinkedList<>();
        points.add(new Point(0, 0, 0, 0));
        int[][][] visited = new int[N][N][2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Arrays.fill(visited[i][j], 987654321);
            }
        }
        visited[0][0][0] = 0;

        while (!points.isEmpty()) {
            Point point = points.poll();
            for (int[] move : dxDy) {
                int X = point.x + move[0];
                int Y = point.y + move[1];
                if (canVisit(X, Y, N)) {
                    //이동하려는 곳이 일반적인 곳일 경우 (시간만 적으면 무조건 이동가능!)
                    if (map[Y][X] == NORMAL && visited[Y][X][point.use] > point.time + 1) {
                        visited[Y][X][point.use] = point.time + 1;
                        points.add(new Point(X, Y, point.time + 1, point.use));
                    }
                    //이동하려는 곳이 오작교일 경우 ( 현재 있는 곳이 0이거나 2이상이면 안됨!
                    else if (map[Y][X] >= OJACKGYO && map[point.y][point.x] != 0 && map[point.y][point.x] < 2) {
                        //주기가 지금 ㄷ딱맞으면?
                        if ((point.time + 1) % map[Y][X] == 0) {
                            if (visited[Y][X][point.use] > point.time + 1) {
                                visited[Y][X][point.use] = point.time + 1;
                                points.add(new Point(X, Y, point.time + 1, point.use));
                            }
                        }
                        //주기가 지금 딱맞지 않으면?? 다음 주기열릴떄까지 기다림!
                        else {
                            int nextTime = ((point.time + 1) / map[Y][X] + 1) * map[Y][X];
                            if (visited[Y][X][point.use] > nextTime) {
                                visited[Y][X][point.use] = nextTime;
                                points.add(new Point(X, Y, nextTime, point.use));
                            }
                        }
                    }
                    //이동하려는 곳이 절벽일 경우? (오작교 설치해야함!,지금 사용하지 않은 상태여야 함!,현재 있는 곳이 절벽이거나 오작교면 안됨, 오작교 설치가능해야함!)
                    else if (map[Y][X] == WALL && point.use != 1 && map[point.y][point.x] != 0 && map[point.y][point.x] < 2 && canInstall(X, Y, N)) {
                        //주기가 지금 딱맞으면?
                        if ((point.time + 1) % M == 0) {
                            if (visited[Y][X][1] > point.time + 1) {
                                visited[Y][X][1] = point.time + 1;
                                points.add(new Point(X, Y, point.time + 1, 1));
                            }
                        }
                        //주기가 지금 딱맞지 않으면?? 다음 주기열릴떄까지 기다려!
                        else {
                            int nextTime = ((point.time + 1) / M + 1) * M;
                            if (visited[Y][X][1] > nextTime) {
                                visited[Y][X][1] = nextTime;
                                points.add(new Point(X, Y, nextTime, 1));
                            }
                        }
                    }
                }
            }
        }
        return Math.min(visited[N - 1][N - 1][0], visited[N - 1][N - 1][1]);
    }


    static boolean canInstall(int x, int y, int N) {
        boolean horizontal = false;
        boolean vertical = false;
        for (int i = 0; i < 3; i += 2) {
            int X = x + dxDy[i][0];
            int Y = y + dxDy[i][1];
            if (canVisit(X, Y, N) && map[Y][X] == WALL) {
                horizontal = true;
                break;
            }
        }
        for (int i = 1; i < 4; i += 2) {
            int X = x + dxDy[i][0];
            int Y = y + dxDy[i][1];
            if (canVisit(X, Y, N) && map[Y][X] == WALL) {
                vertical = true;
                break;
            }
        }
        return !horizontal || !vertical;
    }

    static class Point {
        int x;
        int y;
        int time;
        int use;

        public Point(int x, int y, int time, int use) {
            this.x = x;
            this.y = y;
            this.time = time;
            this.use = use;

        }
    }

    static boolean canVisit(int x, int y, int N) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }
}
