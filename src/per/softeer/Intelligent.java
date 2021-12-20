package per.softeer;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class Intelligent {
    static final int[][] dxDy = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    static final int LEFT = 0;
    static final int TOP = 1;
    static final int RIGHT = 2;
    static final int BOTTOM = 3;

    static final int FROM_LEFT = 1;
    static final int FROM_BOTTOM = 2;
    static final int FROM_RIGHT = 3;
    static final int FROM_TOP = 0;
    //각 신호 번호는 LEFT,TOP,RIGHT,BOTTOM 에서 와야 적용되는 것!
    static final int[][] signals_const = {{}, {TOP, RIGHT, BOTTOM}, {LEFT, TOP, RIGHT}, {TOP, LEFT, BOTTOM}, {LEFT, BOTTOM, RIGHT},
            {TOP, RIGHT}, {LEFT, TOP}, {LEFT, BOTTOM}, {BOTTOM, RIGHT}, {RIGHT, BOTTOM}, {TOP, RIGHT}, {TOP, LEFT}, {LEFT, BOTTOM}};

    static int[][][] signals;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken());
        int T = Integer.parseInt(input.nextToken());
        signals = new int[N][N][4];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                StringTokenizer signal = new StringTokenizer(br.readLine());
                for (int k = 0; k < 4; k++)
                    signals[i][j][k] = Integer.parseInt(signal.nextToken());
            }
        }
        System.out.println(solution(N, T));
    }

    public static int solution(int N, int T) {
        Queue<Point> points = new LinkedList<>();
        int time = 0;
        if (signals[0][0][0] != 2 && signals[0][0][0] != 10) return 1;
        boolean[][][][] visited = new boolean[N][N][4][4];
        visited[0][0][FROM_BOTTOM][0] = true;
        points.add(new Point(0, 0, FROM_BOTTOM));
        while (!points.isEmpty() && time < T) {
            int size = points.size();
            int currentSignalIndex = time % 4;
            for (int i = 0; i < size; i++) {
                Point temp = points.poll();
                int currentSignal = signals[temp.y][temp.x][currentSignalIndex];
                //현재 차의 방향이 교차로의 신호와 맞는지 확인
                if (currentSignal % 4 != temp.direction) {
                    continue;
                }
                //차의 방향이 교차로의 신호와 맞다면, 진행
                int[] thisSignals = signals_const[currentSignal];
                for (int signal : thisSignals) {
                    int X = temp.x + dxDy[signal][0];
                    int Y = temp.y + dxDy[signal][1];
                    int nextDirection = 0;
                    switch (signal) {
                        case LEFT:
                            nextDirection = FROM_RIGHT;
                            break;
                        case TOP:
                            nextDirection = FROM_BOTTOM;
                            break;
                        case RIGHT:
                            nextDirection = FROM_LEFT;
                            break;
                        case BOTTOM:
                            nextDirection = FROM_TOP;
                            break;
                    }
                    if (canVisit(X, Y, N) && !visited[Y][X][nextDirection][(time + 1) % 4]) {
                        visited[Y][X][nextDirection][(time + 1) % 4] = true;
                        points.add(new Point(X, Y, nextDirection));
                    }
                }
            }
            time++;
        }
        int totalVisitCount = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                boolean isUpdate = false;
                for (int l = 0; l < 4; l++) {
                    if (isUpdate) break;
                    for (int k = 0; k < 4; k++) {
                        if (visited[i][j][l][k]) {
                            totalVisitCount++;
                            isUpdate = true;
                            break;
                        }
                    }
                }
            }
        }
        return totalVisitCount;
    }

    static boolean canVisit(int x, int y, int N) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }

    static class Point {
        int x;
        int y;
        int direction;

        public Point(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }
}
