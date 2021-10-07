package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class Floyid {
    static int[][] map;
    static final int INF = 10000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine()); //도시의 개수
        int M = Integer.parseInt(br.readLine()); //버스의 개수

        map = new int[N + 1][N + 1];
        initMap(N);
        while (M-- > 0) {
            StringTokenizer edge = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(edge.nextToken());
            int x = Integer.parseInt(edge.nextToken());
            int value = Integer.parseInt(edge.nextToken());
            map[y][x] = Math.min(value, map[y][x]);
        }
        floyid(N);
        printAnswer(N);

    }

    static void floyid(int N) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;
                for (int k = 1; k <= N; k++) {
                    if (j == k || i == k) continue;
                    map[j][k] = Math.min(map[j][k], map[j][i] + map[i][k]);
                }
            }
        }
    }

    static void printAnswer(int N) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sb.append(map[i][j] == INF ? 0 : map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void initMap(int N) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) map[i][j] = 0;
                else map[i][j] = INF;
            }
        }
    }
}
