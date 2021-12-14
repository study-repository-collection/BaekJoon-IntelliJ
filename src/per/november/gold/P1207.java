package per.november.gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1207 {
    static int[][] map;

    static ArrayList<Point>[] blocks = new ArrayList[6];
    static boolean[] use = new boolean[6];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        int L = Integer.parseInt(br.readLine());
        map = new int[L][L];
        for (int i = 1; i <= 5; i++) {
            blocks[i] = new ArrayList<>();
            StringTokenizer input = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(input.nextToken());
            int M = Integer.parseInt(input.nextToken());
            for (int y = 0; y < N; y++) {
                String blockInfo = br.readLine();
                for (int x = 0; x < M; x++) {
                    if (blockInfo.charAt(x) == '#') {
                        blocks[i].add(new Point(x, y));
                    }
                }
            }
        }

        solution(L);
    }

    static void solution(int L) {
        for (int i = 0; i < L; i++) {
            lineFill = false;
            lineFill(i, 1, L);
        }
        if (isFill(L)) {
            printAnswer(L);
        } else {
            System.out.println("gg");
        }
    }

    static boolean isFill(int L) {
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
                if (map[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean lineFill = false;

    static void lineFill(int line, int number, int L) {
        if (number == 6) return;
        for (int i = 0; i < L; i++) {
            if (map[line][i] == 0) {
                if (!use[number] && canAttach(blocks[number], i, line, L)) {
                    use[number] = true;
                    attach(i, line, number);
                    if (lineFilled(line, L)) {
                        lineFill = true;
                        return;
                    }
                    if (lineFill) {
                        return;
                    }
                    lineFill(line, number + 1, L);
                    detach(i, line, number);
                    use[number] = false;
                } else {
                    lineFill(line, number + 1, L);
                }
            }
        }
        if (!lineFill) {
            lineFill(line, number + 1, L);
        }
    }

    static boolean lineFilled(int line, int L) {
        for (int i = 0; i < L; i++) {
            if (map[line][i] == 0) {
                return false;
            }
        }
        return true;
    }

    static void attach(int x, int y, int number) {
        ArrayList<Point> block = blocks[number];
        for (Point point : block) {
            int X = x + point.x;
            int Y = y + point.y;
            map[Y][X] = number;
        }
    }


    static void detach(int x, int y, int number) {
        ArrayList<Point> block = blocks[number];
        for (Point point : block) {
            int X = x + point.x;
            int Y = y + point.y;
            map[Y][X] = 0;
        }
    }


    static void printAnswer(int L) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
                stringBuilder.append(map[i][j]);
            }
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder);
    }

    static boolean canAttach(ArrayList<Point> block, int x, int y, int L) {
        for (Point point : block) {
            int X = x + point.x;
            int Y = y + point.y;
            if (X >= 0 && Y >= 0 && X < L && Y < L) {
                if (map[Y][X] != 0) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

}
