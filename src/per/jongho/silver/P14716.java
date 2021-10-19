package per.jongho.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P14716 {

    static int M; //현수막의 높이
    static int N; //현수막의 길이
    static int[][] map; //현수막

    static final int VISITED = 2;
    static final int LETTER = 1;
    static final int NON_LETTER = 0;

    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N];

        for (int i = 0; i < M; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(input.nextToken());
            }
        }
        System.out.println(solution());
    }

    static int solution() {
        int countOfLetter = 0; //글자의 개수
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == LETTER) {
                    dfs(j, i);
                    countOfLetter++;
                }
            }
        }
        return countOfLetter;
    }

    static void dfs(int x, int y) {
        map[y][x] = VISITED;

        for (int i = 0; i < 8; i++) {
            int X = x + dx[i];
            int Y = y + dy[i];
            if (canGo(X, Y)) {
                dfs(X, Y);
            }
        }
    }

    static boolean canGo(int x, int y) {
        return (x >= 0 && y >= 0 && x < N && y < M && map[y][x] == LETTER);
    }

}
