package per.november.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P14712 {
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        map = new int[N][M];
        solution(0, 0, N, M);
        System.out.println(count);
    }

    static int count = 0;

    static void solution(int x, int y, int N, int M) {
        if (y == N) {
            count++;
            return;
        }
        int X = x + 1;
        int Y = y;
        if (X == M) {
            X = 0;
            Y++;
        }
        if (y > 0 && x > 0) {
            if (map[y - 1][x] == 1 && map[y][x - 1] == 1 && map[y - 1][x - 1] == 1) {
            } else {
                map[y][x] = 1;
                solution(X, Y, N, M);
            }
        } else {
            map[y][x] = 1;
            solution(X, Y, N, M);
        }
        map[y][x] = 0;
        solution(X, Y, N, M);
    }

}
