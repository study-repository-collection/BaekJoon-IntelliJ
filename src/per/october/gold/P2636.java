package per.october.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P2636 {

    static final int CHEESE = 1;
    static final int AIR = 0;
    static final int INNER_PLACE = 2;
    static final int WILL_MELT = 3;
    static int[][] map;
    static int[][] dxDy = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    //바깥 공기는 계속 바깥 공기이다!! -> 한 번 바깥 공기를 정한 후에, dfs로 만나는 모든 공기는 바깥 공기로 측정..

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer mapSize = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(mapSize.nextToken()); //맵의 높이
        int M = Integer.parseInt(mapSize.nextToken()); //맵의 길이
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            StringTokenizer mapInfo = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(mapInfo.nextToken());
            }
        }
        initMap(N, M);
        solution(N, M);
    }


    static void solution(int N, int M) {
        int time = 0;
        int beforeCheese;
        int answer = 0;
        while ((beforeCheese = countCheese(N, M)) != 0) {
            answer = beforeCheese;
            melting(N, M);
            airDfs(0, 0, new boolean[N][M], N, M);
            time++;
        }

        System.out.println(time);
        System.out.println(answer);
    }

    static void melting(int N, int M) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == CHEESE) {
                    for (int k = 0; k < 4; k++) {
                        int X = j + dxDy[k][0];
                        int Y = i + dxDy[k][1];
                        if (canVisit(X, Y, N, M) && map[Y][X] == AIR) {
                            map[i][j] = WILL_MELT;
                            break;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == WILL_MELT) {
                    map[i][j] = AIR;
                }
            }
        }
    }

    static int countCheese(int N, int M) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == CHEESE) {
                    count++;
                }
            }
        }
        return count;
    }

    static void initMap(int N, int M) {
        boolean[][] visited = new boolean[N][M];
        airDfs(0, 0, visited, N, M);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j] == AIR) {
                    map[i][j] = INNER_PLACE;
                }
            }
        }
    }

    static void airDfs(int x, int y, boolean[][] visited, int N, int M) {
        visited[y][x] = true;
        map[y][x] = AIR;
        for (int i = 0; i < 4; i++) {
            int X = x + dxDy[i][0];
            int Y = y + dxDy[i][1];
            if (canVisit(X, Y, N, M) && !visited[Y][X] && map[Y][X] != CHEESE) {
                airDfs(X, Y, visited, N, M);
            }
        }
    }

    static boolean canVisit(int x, int y, int N, int M) {
        return (x >= 0 && y >= 0 && x < M && y < N);
    }
}
