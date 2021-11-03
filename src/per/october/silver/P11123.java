package per.october.silver;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P11123 {
    static int H;//그리드의 높이
    static int W;//그리드의 넓이
    static int[][] map;//그리드
    static final int SHEEP = '#';
    static final int GRASS = '.';
    static final int VISITED = 2;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            map = new int[H][W];
            for (int i = 0; i < H; i++) {
                String input = br.readLine();
                for (int j = 0; j < W; j++) {
                    map[i][j] = input.charAt(j);
                }
            }
            sb.append(solution()).append("\n");
        }
        System.out.println(sb);
    }

    static int solution() {
        int count = 0; //양의 무리
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] == SHEEP) {
                    bfs(j, i);
                    count++;
                }
            }
        }
        return count;
    }

    static void bfs(int x, int y) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            for (int i = 0; i < 4; i++) {
                int X = point.x + dx[i];
                int Y = point.y + dy[i];
                if (canGo(X, Y)) {
                    map[Y][X] = VISITED;
                    queue.add(new Point(X, Y));
                }
            }
        }
    }


    static boolean canGo(int x, int y) {
        return (x >= 0 && y >= 0 && x < W && y < H && map[y][x] == SHEEP);
    }
}
