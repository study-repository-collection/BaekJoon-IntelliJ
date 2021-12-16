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
            int initialX = 0, initialY = 0;
            for (int y = 0; y < N; y++) {
                String blockInfo = br.readLine();
                for (int x = 0; x < M; x++) {
                    if (blocks[i].isEmpty() && blockInfo.charAt(x) == '#') {
                        blocks[i].add(new Point(0, 0));
                        initialY = y;
                        initialX = x;
                    } else if (blockInfo.charAt(x) == '#') {
                        blocks[i].add(new Point(x - initialX, y - initialY));
                    }
                }
            }
        }
        solution(L, 0, 0);
        if (!isFind) {
            System.out.println("gg");
        }
    }

    static boolean isFind = false;

    static void solution(int L, int line, int x) {
        if (isFind) return;
        if (line == L) {
            if (isFill(L)) {
                isFind = true;
                printAnswer(L);
            }
            return;
        }
        if (lineFill(line, L)) {
            solution(L, line + 1, 0);
        }
        for (int i = x; i < L; i++) {
            for (int j = 1; j <= 5; j++) {
                if (!use[j] && map[line][i] == 0 && canAttach(blocks[j], i, line, L)) {
                    use[j] = true;
                    attach(i, line, j);
                    solution(L, line, i + 1);
                    use[j] = false;
                    detach(i, line, j);
                }
            }
        }
    }

    static boolean lineFill(int line, int L) {
        for (int i = 0; i < L; i++) {
            if (map[line][i] == 0) {
                return false;
            }
        }
        return true;
    }

    static boolean isFill(int L) {
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
                if (map[i][j] == 0) {
                    return false;
                }
            }
        }
        for (int i = 1; i <= 5; i++) {
            if (!use[i]) {
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
