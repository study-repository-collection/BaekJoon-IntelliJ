package per.november.gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P16197 {
    static final char COIN = 'o';
    static final char EMPTY = '.';
    static final char WALL = '#';
    static Point coin1 = null;
    static Point coin2 = null;
    static char[][] map;

    static final int[][] dxDy = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);
                if (map[i][j] == COIN) {

                    if (null == coin1) {
                        coin1 = new Point(j, i);
                    } else {
                        coin2 = new Point(j, i);
                    }
                }
            }
        }
        moveCoin(1, N, M, coin1.x, coin1.y, coin2.x, coin2.y);
        System.out.println(find ? findCount : -1);
    }

    static int findCount = 11;
    static boolean find = false;

    static void moveCoin(int count, int N, int M, int coin1x, int coin1y, int coin2x, int coin2y) {
        if (count > 10) {
            return;
        }
        for (int[] move : dxDy) {
            int coin1X = coin1x + move[0];
            int coin1Y = coin1y + move[1];
            int coin2X = coin2x + move[0];
            int coin2Y = coin2y + move[1];
            boolean isCoin1Inside = isInside(coin1X, coin1Y, N, M);
            boolean isCoin2Inside = isInside(coin2X, coin2Y, N, M);
            if (isCoin1Inside && map[coin1Y][coin1X] == WALL) {
                coin1X = coin1x;
                coin1Y = coin1y;
            }
            if (isCoin2Inside && map[coin2Y][coin2X] == WALL) {
                coin2X = coin2x;
                coin2Y = coin2y;
            }
            if (isCoin1Inside && isCoin2Inside) {
                moveCoin(count + 1, N, M, coin1X, coin1Y, coin2X, coin2Y);
            } else if (isCoin1Inside || isCoin2Inside) {
                find = true;
                findCount = Math.min(findCount, count);
                return;
            }
        }
    }

    static boolean isInside(int x, int y, int N, int M) {
        return (x >= 0 && y >= 0 && x < M && y < N);
    }
}
