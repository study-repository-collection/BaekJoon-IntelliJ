package per.november.gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

public class P17142 {

    static final int EMPTY = 0;
    static final int WALL = 1;
    static final int VIRUS = 2;
    static final int ACTIVE = 3;
    static final int[][] dxDy = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    static int[][] map;
    static ArrayList<Point> viruses = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken());//맵의 크기
        int M = Integer.parseInt(input.nextToken()); //놓을 수 있는 바이러스의 개수
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer mapInfo = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(mapInfo.nextToken());
                if (map[i][j] == VIRUS) {
                    viruses.add(new Point(j, i));
                }
            }
        }
        solution(N, M, 0, new Stack<>(), 0);
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }


    static int min = Integer.MAX_VALUE;

    static void solution(int N, int M, int count, Stack<Point> activeViruses, int start) {
        if (count == M) {
            Queue<Point> queue = new LinkedList<>();
            queue.addAll(activeViruses);
            int ret = bfs(N, queue, getCopiedMap(N, queue));
            min = Math.min(min, ret);
            return;
        }

        for (int i = start; i < viruses.size() - (M - count) + 1; i++) {
            activeViruses.add(viruses.get(i));
            solution(N, M, count + 1, activeViruses, i + 1);
            activeViruses.pop();
        }
    }

    static int[][] getCopiedMap(int N, Queue<Point> activeViruses) {
        int[][] copiedMap = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copiedMap[i][j] = map[i][j];
            }
        }
        for (Point point : activeViruses) copiedMap[point.y][point.x] = ACTIVE;
        return copiedMap;
    }

    static boolean canVisit(int x, int y, int N) {
        return (x >= 0 && y >= 0 && x < N && y < N);
    }

    static int bfs(int N, Queue<Point> activeViruses, int[][] map) {
        int count = 0;
        while (!activeViruses.isEmpty()) {
            if (isFill(N, map)) {
                return count;
            }
            int size = activeViruses.size();
            for (int i = 0; i < size; i++) {
                Point point = activeViruses.poll();
                for (int[] move : dxDy) {
                    int X = point.x + move[0];
                    int Y = point.y + move[1];

                    if (canVisit(X, Y, N) && map[Y][X] != WALL && map[Y][X] != ACTIVE) {
                        map[Y][X] = ACTIVE;
                        activeViruses.add(new Point(X, Y));
                    }
                }
            }
            count++;
        }
        return Integer.MAX_VALUE;
    }

    static boolean isFill(int N, int[][] map) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == EMPTY) return false;
            }
        }
        return true;
    }
}
