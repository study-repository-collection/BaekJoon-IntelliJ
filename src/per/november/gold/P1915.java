package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1915 {

    static int[][] map;
    static int max = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer input = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(input.nextToken());
        int m = Integer.parseInt(input.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            String mapInfo = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = mapInfo.charAt(j) - '0';
                max = Math.max(map[i][j], max);
            }
        }
        solution(n, m);
        System.out.println(max * max);
    }

    static int solution(int n, int m) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1 && i > 0 && j > 0) {
                    int a = map[i - 1][j - 1];
                    int b = map[i - 1][j];
                    int c = map[i][j - 1];
                    int min = Math.min(a, b);
                    min = Math.min(min, c);
                    map[i][j] = min + 1;
                    max = Math.max(max, map[i][j]);
                }
            }
        }
        return max;
    }
}
