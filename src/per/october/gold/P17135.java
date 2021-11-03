package per.october.gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P17135 {
    static int[][] map;
    static int[][] originalMap;
    static final int ENEMY = 1;
    static final int EMPTY = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken()); //궁수의 공격 거리 제한

        map = new int[N + 1][M];
        originalMap = new int[N][M];
        archerPosition = new int[3];
        for (int i = 0; i < N; i++) {
            StringTokenizer mapInfo = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(mapInfo.nextToken());
                originalMap[i][j] = map[i][j];
            }
        }
        System.out.println(deployArcher(M, N, D));
    }

    static int[] archerPosition;

    static int deployArcher(int M, int N, int D) {
        int max = -1;
        for (int i = 0; i < M - 2; i++) {
            for (int j = i + 1; j < M - 1; j++) {
                for (int k = j + 1; k < M; k++) {
                    archerPosition[0] = i;
                    archerPosition[1] = j;
                    archerPosition[2] = k;
                    max = Math.max(playGame(N, M, D), max);
                    copyMap(N, M);
                }
            }
        }
        return max;
    }

    static void copyMap(int N, int M) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = originalMap[i][j];
            }
        }
    }

    static int playGame(int N, int M, int D) {
        int enemyCount = 0;

        Point[] archerTargetPosition = new Point[3];
        int[] targetDistance = new int[3];
        for (int i = 0; i < 3; i++) {
            archerTargetPosition[i] = new Point(-1, -1);
            targetDistance[i] = Integer.MAX_VALUE;
        }
        while (isEnemyExist(N, M)) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < N; j++) {
                    for (int k = 0; k < M; k++) {
                        int distance = 0;
                        distance = getDistance(k, archerPosition[i], j, N);
                        if (map[j][k] == ENEMY && distance <= D) {
                            if (distance < targetDistance[i]) {
                                targetDistance[i] = getDistance(k, archerPosition[i], j, N);
                                archerTargetPosition[i].x = k;
                                archerTargetPosition[i].y = j;
                            } else if (distance == targetDistance[i]) {
                                if (archerTargetPosition[i].x > k) {
                                    archerTargetPosition[i].x = k;
                                    archerTargetPosition[i].y = j;
                                }
                            }
                        }
                    }
                }
            }
            for (int i = 0; i < 3; i++) {
                if (archerTargetPosition[i].x != -1) {
                    enemyCount += map[archerTargetPosition[i].y][archerTargetPosition[i].x];
                    map[archerTargetPosition[i].y][archerTargetPosition[i].x] = 0;
                }
            }
            moveEnemy(N, M);
            for (int i = 0; i < 3; i++) {
                archerTargetPosition[i].x = -1;
                targetDistance[i] = Integer.MAX_VALUE;
            }
        }
        return enemyCount;
    }

    static boolean isEnemyExist(int N, int M) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == ENEMY) {
                    return true;
                }
            }
        }
        return false;
    }

    static void moveEnemy(int N, int M) {
        for (int i = N - 1; i > 0; i--) {
            for (int j = 0; j < M; j++) {
                map[i][j] = map[i - 1][j];
                map[i - 1][j] = EMPTY;
            }
        }
    }

    static int getDistance(int x1, int x2, int y1, int y2) {
        return Math.abs(y1 - y2) + Math.abs(x1 - x2);
    }
}
