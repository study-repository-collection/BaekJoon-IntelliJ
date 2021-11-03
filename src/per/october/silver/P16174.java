package per.october.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P16174 {

    static final int VISITED = 101;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());//게임 구역의 크기
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solution(map, N) ? "HaruHaru" : "Hing");
    }

    static boolean solution(int[][] map, int N) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0, map[0][0]));

        while (!queue.isEmpty()) {
            Point temp = queue.poll();
            if (temp.x == N - 1 && temp.y == N - 1) {
                return true;
            }
            int dx = temp.x + temp.value;
            int dy = temp.y + temp.value;
            if (canGo(temp.x, dy, N) && map[dy][temp.x] != VISITED) {
                queue.add(new Point(temp.x, dy, map[dy][temp.x]));
                map[dy][temp.x] = VISITED;
            }
            if (canGo(dx, temp.y, N) && map[temp.y][dx] != VISITED) {
                queue.add(new Point(dx, temp.y, map[temp.y][dx]));
                map[temp.y][dx] = VISITED;
            }
        }
        return false;
    }

    static boolean canGo(int x, int y, int N) {
        return (x < N && y < N);
    }

    static class Point {
        final int x;
        final int y;
        final int value;

        public Point(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }

    }

}
