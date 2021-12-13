package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1507 {
    static int[][] map;
    static int[][] answer;
    static final int UNABLE = -1;
    static final int EXIST = -2;
    static final int UN_EXIST = -3;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine()); //도시의 개수
        map = new int[N + 1][N + 1];
        answer = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(input.nextToken());
            }
        }
        System.out.println(solution(N));
    }


    static int solution(int N) {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                if (i == k) continue;
                for (int j = 1; j <= N; j++) {
                    if (k == j || i == j) continue;
                    if (map[i][j] == map[i][k] + map[k][j]) {
                        map[i][j] = 987654321;
                    } else if (map[i][j] > map[i][k] + map[k][j] && map[i][j] != 987654321) {
                        return -1;
                    }
                }
            }
        }
        int sum = 0;
        for (int i = 1; i <= N - 1; i++) {
            for (int j = i + 1; j <= N; j++) {
                if (map[i][j] != 987654321) {
                    sum += map[i][j];
                }
            }
        }
        return sum;
    }
}
