package per.october.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class Dijkstra4485 {
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int count = 1;
        while (true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) break;
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            sb.append("Problem ").append(count++).append(": ");
            sb.append(dijkstra(N)).append("\n");
        }
        System.out.print(sb);
    }

    static final int INF = 100000;

    static int dijkstra(int N) {
        int[][] dp = new int[N][N];
        PriorityQueue<Thief> thieves = new PriorityQueue<>();
        boolean[][] visited = new boolean[N][N];
        for (int i = 0; i < N; i++) Arrays.fill(dp[i], INF);
        dp[0][0] = map[0][0];
        thieves.add(new Thief(0, 0, dp[0][0]));

        while (!thieves.isEmpty()) {
            Thief thief = thieves.poll();
            if (visited[thief.y][thief.x]) continue;
            visited[thief.y][thief.x] = true;

            for (int i = 0; i < 4; i++) {
                int X = thief.x + dx[i];
                int Y = thief.y + dy[i];
                if (canGo(X, Y, N, visited)) {
                    dp[Y][X] = Math.min(dp[Y][X], dp[thief.y][thief.x] + map[Y][X]);
                    if (!visited[Y][X]) thieves.add(new Thief(X, Y, dp[Y][X]));
                }
            }
        }
        return dp[N - 1][N - 1];
    }

    static boolean canGo(int x, int y, int N, boolean[][] visited) {
        return (x >= 0 && y >= 0 && x < N && y < N);
    }

    static class Thief implements Comparable<Thief> {
        final int x;
        final int y;
        final int value;

        public Thief(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }

        @Override
        public int compareTo(Thief o) {
            return this.value > o.value ? 1 : -1;
        }
    }
}
