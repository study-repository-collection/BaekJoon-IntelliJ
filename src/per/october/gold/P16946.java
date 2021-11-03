package per.october.gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P16946 {
    static int[][] map;
    static int[][] group;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static final int WALL = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer mapInfo = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(mapInfo.nextToken());
        int M = Integer.parseInt(mapInfo.nextToken());

        map = new int[N][M];
        group = new int[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }
        initMap(N, M);
        findArea(N, M);
        solution(N, M);
    }

    static void findArea(int N, int M) {
        boolean[][] visited = new boolean[N][M];
        int groupNumber = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0 && !visited[i][j]) {
                    bfs(j, i, visited, groupNumber++, N, M);
                    count = 0;
                }
            }
        }
    }


    static void initMap(int N, int M) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    map[i][j] = WALL;
                }
            }
        }
    }

    static void solution(int N, int M) {
        int[][] answer = new int[N][M];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    answer[i][j] = 0;
                } else if (map[i][j] == WALL) {
                    ArrayList<Integer> arrayList = new ArrayList<>();
                    int sum = 0;
                    for (int k = 0; k < 4; k++) {
                        int X = j + dx[k];
                        int Y = i + dy[k];
                        if (canGo(X, Y, N, M) && map[Y][X] != WALL && !arrayList.contains(group[Y][X])) {
                            arrayList.add(group[Y][X]);
                            sum += map[Y][X];
                        }
                    }
                    answer[i][j] = (sum + 1) % 10;
                }
                sb.append(answer[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static int count = 0;

    static void bfs(int x, int y, boolean[][] visited, int groupNumber, int N, int M) {
        visited[y][x] = true;
        int count = 1;
        Queue<Point> points = new LinkedList<>();
        ArrayList<Point> points1 = new ArrayList<>();
        points.add(new Point(x, y));

        while (!points.isEmpty()) {
            Point point = points.poll();
            points1.add(point);
            for (int i = 0; i < 4; i++) {
                int X = point.x + dx[i];
                int Y = point.y + dy[i];
                if (canGo(X, Y, N, M) && !visited[Y][X] && map[Y][X] == 0) {
                    count++;
                    points.add(new Point(X, Y));
                    visited[Y][X] = true;
                }
            }
        }
        for (Point point : points1) {
            int X = point.x;
            int Y = point.y;
            group[Y][X] = groupNumber;
            map[Y][X] = count;
        }
    }

    static boolean canGo(int x, int y, int N, int M) {
        return (x >= 0 && y >= 0 && x < M && y < N);
    }


}
