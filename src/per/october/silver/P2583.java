package per.october.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P2583 {
    static int[][] map;
    static int VISITED = 1;
    static int UNVISITED = 0;
    static int INNER_SQUARE = 2;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static int size = 0;
    static final ArrayList<Integer> answer = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        int K = Integer.parseInt(st.nextToken());

        while (K-- > 0) {
            StringTokenizer square = new StringTokenizer(br.readLine());
            int leftX = Integer.parseInt(square.nextToken());
            int leftY = Integer.parseInt(square.nextToken());
            int rightX = Integer.parseInt(square.nextToken());
            int rightY = Integer.parseInt(square.nextToken());
            preTreatment(leftX, leftY, rightX, rightY);
        }
        solution(M, N);
        sb.append(answer.size()).append("\n");
        Collections.sort(answer);
        for (int value : answer) sb.append(value).append(" ");
        System.out.println(sb);
    }


    static void solution(int M, int N) {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == UNVISITED) {
                    dfs(M, N, i, j);
                    answer.add(size);
                    size = 0;
                }
            }
        }
    }

    static void dfs(int M, int N, int y, int x) {
        size++;
        map[y][x] = VISITED;
        for (int i = 0; i < 4; i++) {
            int X = x + dx[i];
            int Y = y + dy[i];
            if (canGo(M, N, Y, X)) {
                dfs(M, N, Y, X);
            }
        }

    }

    //?????? ????????? ?????????????????? ????????? ???????????? ??????
    static boolean canGo(int M, int N, int Y, int X) {
        return (X >= 0 && Y >= 0 && X < N && Y < M && (map[Y][X] == UNVISITED));
    }

    /**
     * map ?????? ???????????? ?????? ????????? ??????
     *
     * @param leftX  ?????? ?????? X ??????
     * @param leftY  ?????? ?????? Y ??????
     * @param rightX ?????? ?????? X ??????
     * @param rightY ?????? ?????? Y ??????
     */
    static void preTreatment(int leftX, int leftY, int rightX, int rightY) {
        for (int i = leftX; i < rightX; i++) {
            for (int j = leftY; j < rightY; j++) {
                map[j][i] = INNER_SQUARE;
            }
        }
    }
}
