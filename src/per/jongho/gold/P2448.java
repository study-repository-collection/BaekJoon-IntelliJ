package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;
import static java.lang.System.setIn;

public class P2448 {
    static char[][] starMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine()); //별 찍기의 높이로 유추
        starMap = new char[N + 1][2 * N];
        initMap();
        solution(1, N, N);
        printStarMap();

    }

    static void initMap() {
        int height = starMap.length - 1;
        int length = starMap[0].length - 1;
        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= length; j++) {
                starMap[i][j] = ' ';
            }
        }
    }

    static void solution(int startX, int startY, int N) {
        if (N == 3) {
            makeStar(startX, startY);
            return;
        }
        solution(startX, startY, N / 2);
        solution(startX + N, startY, N / 2);
        solution(startX + N / 2, startY - N / 2, N / 2);
    }

    static void makeStar(int startX, int startY) {
        for (int i = 0; i < 3; i++) {
            for (int j = startX + i; j < startX + 5 - i; j++) {
                starMap[startY - i][j] = '*';
            }
        }
        starMap[startY - 1][startX + 2] = ' ';
    }

    static void printStarMap() {
        StringBuilder sb = new StringBuilder();
        int height = starMap.length - 1;
        int length = starMap[0].length - 1;
        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= length; j++) {
                sb.append(starMap[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
