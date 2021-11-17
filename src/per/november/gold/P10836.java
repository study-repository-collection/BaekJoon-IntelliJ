package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P10836 {
    static int[][] map;
    static final int[][] dxDy = {{-1, 0}, {-1, -1}, {0, -1}};
    static Queue<Integer> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer input = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(input.nextToken()); //지도 크기
        int N = Integer.parseInt(input.nextToken()); //날짜 수
        map = new int[M][M];
        for (int i = 0; i < M; i++) {
            Arrays.fill(map[i], 1);
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            solution(a, b, c, M);
        }
        addSolution(M);
        printAnswer(M);
    }


    static void addSolution(int M) {
        for (int i = 1; i < M; i++) {
            for (int j = 1; j < M; j++) {
                for (int[] move : dxDy) {
                    int X = j + move[0];
                    int Y = i + move[1];
                    map[i][j] = Math.max(map[i][j], map[Y][X]);
                }
            }
        }
    }

    static void printAnswer(int M) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void solution(int a, int b, int c, int M) {
        //왼쪽 열을 먼저 성장시킴
        for (int i = M - 1; i >= 0; i--) {
            if (a != 0) a--;
            else if (b != 0) {
                b--;
                map[i][0] += 1;
            } else {
                map[i][0] += 2;
            }
        }

        //위쪽 행을 성장시킴
        for (int i = 1; i < M; i++) {
            if (a != 0) a--;
            else if (b != 0) {
                b--;
                map[0][i] += 1;
            } else {
                map[0][i] += 2;
            }
        }
    }
}
