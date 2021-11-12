package per.november.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P16918 {
    static char[][] map;

    static final char EMPTY = '.';
    static final char BOMB = 'O';

    static final int[][] dxDy = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        int N = Integer.parseInt(st.nextToken()); //N 초가 지난 후에 격자판의 상태를 출력하시오
        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = input.charAt(j);
                if (map[i][j] == BOMB) map[i][j] = '2';
            }
        }

        solution(R, C, N);
    }

    static void solution(int R, int C, int N) {
        N--;
        while (N > 0) {
            N--;
            installBomb(R, C);
            bomb(R, C);
        }
        printAnswer(R, C);
    }

    static void bomb(int R, int C) {
        boolean[][] toBomb = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == '0') {
                    toBomb[i][j] = true;
                    for (int[] move : dxDy) {
                        int X = j + move[0];
                        int Y = i + move[1];
                        if (canVisit(X, Y, R, C)) {
                            toBomb[Y][X] = true;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (toBomb[i][j]) map[i][j] = EMPTY;
            }
        }
    }

    static boolean canVisit(int x, int y, int R, int C) {
        return (x >= 0 && y >= 0 && x < C && y < R);
    }

    static void installBomb(int R, int C) {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == EMPTY) {
                    map[i][j] = '3';
                } else {
                    map[i][j]--;
                }
            }
        }
    }

    static void printAnswer(int R, int C) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > '0' && map[i][j] < '4') {
                    sb.append(BOMB);
                } else {
                    sb.append(map[i][j]);
                }
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
