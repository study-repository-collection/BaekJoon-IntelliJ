package per.jongho.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1926 {
    static int[][] map;
    static final int VISITED = 2;
    static final int PICTURE = 1;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static ArrayList<Integer> pictures = new ArrayList<>();
    static int pictureSize = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //높이
        int M = Integer.parseInt(st.nextToken()); //길이

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(input.nextToken());
            }
        }
        solution(N, M);
        if (pictures.isEmpty()) {
            System.out.println("0\n0");
        } else {
            System.out.println(pictures.size() + "\n" + pictures.stream().max(Integer::compareTo).get());
        }
    }

    static void solution(int N, int M) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == PICTURE) {
                    dfs(N, M, j, i);
                    pictures.add(pictureSize);
                    pictureSize = 0;
                }
            }
        }
    }

    static void dfs(int N, int M, int x, int y) {
        map[y][x] = VISITED;
        pictureSize++;

        for (int i = 0; i < 4; i++) {
            int X = x + dx[i];
            int Y = y + dy[i];
            if (canGo(X, Y, N, M)) {
                dfs(N, M, X, Y);
            }
        }
    }

    static boolean canGo(int x, int y, int N, int M) {
        return (x >= 0 && y >= 0 && x < M && y < N && map[y][x] == PICTURE);
    }
}
