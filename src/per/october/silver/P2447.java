package per.october.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;

public class P2447 {
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine()); //별 크기
        map = new char[N + 1][N + 1];
        initStarMap(N);
        makeStarMap(1, 1, N);
        printStarMap(map);
    }

    static void initStarMap(int N) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                map[i][j] = '*';
            }
        }
    }

    static void makeStarMap(int startX, int startY, int size) {
        if (size == 1) return;
        int blankStartX = startX + size / 3;
        int blankStartY = startY + size / 3;
        for (int i = blankStartY; i < blankStartY + size / 3; i++) {
            for (int j = blankStartX; j < blankStartX + size / 3; j++) {
                map[i][j] = ' ';
            }
        }
        int secondX = startX + size / 3;
        int secondY = startY + size / 3;

        int thirdX = startX + (2 * (size / 3));
        int thirdY = startY + (2 * (size / 3));
        makeStarMap(startX, startY, size / 3);
        makeStarMap(secondX, startY, size / 3);
        makeStarMap(thirdX, startY, size / 3);
        makeStarMap(startX, secondY, size / 3);
        makeStarMap(thirdX, secondY, size / 3);
        makeStarMap(startX, thirdY, size / 3);
        makeStarMap(secondX, thirdY, size / 3);
        makeStarMap(thirdX, thirdY, size / 3);

    }

    static void printStarMap(char[][] map) {
        StringBuilder sb = new StringBuilder();
        int N = map.length - 1;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
