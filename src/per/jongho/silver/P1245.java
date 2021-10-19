package per.jongho.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1245 {
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};

    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer size = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(size.nextToken());
        int M = Integer.parseInt(size.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(input.nextToken());
            }
        }
        System.out.println(solution(N, M));
    }

    static boolean isPeak = true;

    static int solution(int N, int M) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0 && !visited[i][j]) {
                    isPeak = true;
                    dfs(j, i, N, M);
                    count += isPeak ? 1 : 0;
                }
            }
        }
        return count;
    }

    static void dfs(int x, int y, int N, int M) {
        visited[y][x] = true;
        for (int i = 0; i < 8; i++) {
            int X = x + dx[i];
            int Y = y + dy[i];
            if (canGo(X, Y, N, M)) {
                if (map[Y][X] > map[y][x]) {
                    isPeak = false;
                }
                if(!visited[Y][X] && map[Y][X] == map[y][x]){
                    dfs(X,Y,N,M);
                }
            }
        }
    }

    static boolean canGo(int x, int y, int N, int M) {
        return (x >= 0 && y >= 0 && x < M && y < N);
    }
}
