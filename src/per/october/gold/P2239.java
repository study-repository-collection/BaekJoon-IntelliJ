package per.october.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;

public class P2239 {

    static int[][] map = new int[9][9];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            String input = br.readLine();
            for (int j = 0; j < 9; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }
        solution(0, 0);
    }

    static boolean findAnswer = false;

    static void solution(int x, int y) {
        if (findAnswer) return;
        if (y == 9) {
            printAnswer();
            findAnswer = true;
            return;
        }
        if (map[y][x] != 0) {
            if (x == 8) {
                solution(0, y + 1);
                return;
            } else {
                solution(x + 1, y);
                return;
            }
        }
        boolean[] exist = new boolean[10];
        checkSubSquare(x, y, exist);
        checkRow(x, exist);
        checkCol(y, exist);
        for (int i = 1; i <= 9; i++) {
            if (!exist[i]) {
                map[y][x] = i;
                if (x == 8) {
                    solution(0, y + 1);
                } else {
                    solution(x + 1, y);
                }
                map[y][x] = 0;
            }
        }
    }

    static void printAnswer() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void checkSubSquare(int x, int y, boolean[] exist) {
        x = (x / 3) * 3;
        y = (y / 3) * 3;

        for (int i = y; i < y + 3; i++) {
            for (int j = x; j < x + 3; j++) {
                exist[map[i][j]] = true;
            }
        }
    }

    static void checkCol(int y, boolean[] exist) {
        for (int i = 0; i < 9; i++) {
            exist[map[y][i]] = true;
        }
    }

    static void checkRow(int x, boolean[] exist) {
        for (int i = 0; i < 9; i++) {
            exist[map[i][x]] = true;
        }
    }
}
