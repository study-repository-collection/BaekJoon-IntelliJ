package per.november.gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1981 {
    static int[][] map;
    static final int[][] dxDy = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        int max = -1;
        int min = 201;
        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(input.nextToken());
                max = Math.max(max, map[i][j]);
                min = Math.min(min, map[i][j]);
            }
        }
        solution(N, max - min, max, min);
    }

    static void solution(int N, int max, int maxNum, int minNum) {
        int start = 0;
        int finish = max;
        int answer = 0;
        while (start <= finish) {
            int mid = (start + finish) / 2;
            if (bfs(N, mid, maxNum, minNum)) {
                answer = mid;
                finish = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        System.out.println(answer);
    }

    static boolean bfs(int N, int diff, int maxNum, int minNum) {
        for (int i = minNum; i + diff <= maxNum; i++) {
            Queue<Point> queue = new LinkedList<>();
            queue.add(new Point(0, 0));
            boolean[][] visited = new boolean[N][N];
            visited[0][0] = true;
            if (map[0][0] < i || map[0][0] > i + diff) continue;
            while (!queue.isEmpty()) {
                Point temp = queue.poll();
                if (temp.y == N - 1 && temp.x == N - 1) return true;
                for (int[] move : dxDy) {
                    int X = temp.x + move[0];
                    int Y = temp.y + move[1];
                    if (canVisit(X, Y, N)) {
                        if (!visited[Y][X] && map[Y][X] >= i && map[Y][X] <= i + diff) {
                            visited[Y][X] = true;
                            queue.add(new Point(X, Y));
                        }
                    }
                }
            }
        }
        return false;
    }

    static class Point {
        int x;
        int y;


        public Point(int x, int y) {
            this.x = x;
            this.y = y;

        }
    }

    static boolean canVisit(int x, int y, int N) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }
}
