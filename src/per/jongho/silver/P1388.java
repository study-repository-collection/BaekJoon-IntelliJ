package per.jongho.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1388 {
    static int[][] map;
    static final int VISITED = 1;

    static int count = 0;

    static final int HORIZONTAL = 0;
    static final int VERTICAL = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);

            }
        }
        solution(N, M);
        System.out.println(count);
    }

    static void solution(int N, int M) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != VISITED) {
                    dfs(j, i, N, M, map[i][j] == '-' ? HORIZONTAL : VERTICAL);
                    count++;
                }
            }
        }
    }

    static void dfs(int x, int y, int N, int M, int type) {
        map[y][x] = VISITED;
        if (type == HORIZONTAL) {
            int X = x + 1;
            if (canGo(X, y, N, M, type)) {
                dfs(X, y, N, M, type);
            }
        } else {
            int Y = y + 1;
            if (canGo(x, Y, N, M, type)) {
                dfs(x, Y, N, M, type);
            }
        }

    }

    static boolean canGo(int x, int y, int N, int M, int type) {
        return (x >= 0 && y >= 0 && y < N && x < M && map[y][x] != VISITED && (type == HORIZONTAL ? map[y][x] == '-' : map[y][x] == '|'));
    }

}
