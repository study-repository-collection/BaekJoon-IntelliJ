package per.october.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P16932 {
    static int[][] map;
    static ShapeInfo[][] shapeInfos;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer mapInfo = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(mapInfo.nextToken());
        int M = Integer.parseInt(mapInfo.nextToken());
        map = new int[N][M];
        shapeInfos = new ShapeInfo[N][M];
        Date startTime = new Date();
        for (int i = 0; i < N; i++) {
            StringTokenizer mapInfos = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(mapInfos.nextToken());
            }
        }
        Date endTime = new Date();
        long lTime = endTime.getTime() - startTime.getTime();
        System.out.println("TIME : " + lTime + "(ms)");


        solution(N, M);

        System.out.println(answer(N, M));

    }


    static void solution(int N, int M) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    shapeInfo = new ShapeInfo(0);
                    dfs(j, i, N, M);
                    mid++;
                    shapeInfo.size = count;
                    count = 0;
                }
            }
        }
    }

    static int answer(int N, int M) {
        int max = -1;
        ArrayList<Integer> ids = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int count = 1;
                ids.clear();
                if (map[i][j] == 0) {
                    for (int k = 0; k < 4; k++) {
                        int X = j + dx[k];
                        int Y = i + dy[k];
                        if (canVisit(X, Y, N, M) && null != shapeInfos[Y][X] && !ids.contains(shapeInfos[Y][X].id)) {
                            ids.add(shapeInfos[Y][X].id);
                            count += shapeInfos[Y][X].size;
                        }
                    }
                    max = Math.max(max, count);
                }
            }
        }
        return max;
    }

    static final int VISITED = -1;
    static int count = 0;

    static ShapeInfo shapeInfo;

    static void dfs(int x, int y, int N, int M) {
        map[y][x] = VISITED;
        count++;

        for (int i = 0; i < 4; i++) {
            int X = x + dx[i];
            int Y = y + dy[i];
            if (canGo(X, Y, N, M)) {
                dfs(X, Y, N, M);
            }
        }
        shapeInfos[y][x] = shapeInfo;
    }

    static boolean canGo(int x, int y, int N, int M) {
        return (x >= 0 && y >= 0 && x < M && y < N && map[y][x] == 1);
    }

    static boolean canVisit(int x, int y, int N, int M) {
        return (x >= 0 && y >= 0 && x < M && y < N);
    }

    static int mid = 0;

    static class ShapeInfo {
        int size;
        final int id;

        public ShapeInfo(int size) {
            this.size = size;
            this.id = mid;
        }
    }
}
