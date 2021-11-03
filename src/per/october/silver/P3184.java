package per.october.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P3184 {
    static int[][] map;
    static final int EMPTY = '.';
    static final int SHEEP = 'o';
    static final int WOLF = 'v';
    static final int FENCE = '#';
    static final int VISITED = 0;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static int R;
    static int C;

    static int wolf = 0;
    static int sheep = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken()); //행
        C = Integer.parseInt(st.nextToken()); //열
        map = new int[R][C];

        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = input.charAt(j);
            }
        }
        solution(R, C);
    }

    static void solution(int R, int C) {
        int currentSheep = 0;
        int currentWolf = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] != VISITED && map[i][j] != FENCE) {
                    dfs(i, j);
                    //양이 늑대보다 많은 경우
                    if (sheep > wolf) {
                        currentSheep += sheep;
                    } else {
                        currentWolf += wolf;
                    }
                    wolf = 0;
                    sheep = 0;
                }
            }
        }
        System.out.print(currentSheep + " ");
        System.out.println(currentWolf);
    }

    static void dfs(int y, int x) {
        if (map[y][x] == WOLF) wolf++;
        else if (map[y][x] == SHEEP) sheep++;

        map[y][x] = VISITED;

        for (int i = 0; i < 4; i++) {
            int Y = y + dy[i];
            int X = x + dx[i];
            if (canGo(Y, X, R, C)) {
                dfs(Y, X);
            }
        }
    }

    static boolean canGo(int y, int x, int R, int C) {
        return ((y >= 0) && (x >= 0) && (y < R) && (x < C) && map[y][x] != VISITED && map[y][x] != FENCE);
    }
}
