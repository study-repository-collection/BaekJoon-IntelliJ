package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

public class P11780 {
    static int[][] map;
    static int[][] route;
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());//도시의 개수
        map = new int[n + 1][n + 1];
        route = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(map[i], INF);
            map[i][i] = 0;
        }
        int m = Integer.parseInt(br.readLine());//버스의 개수
        while (m-- > 0) {
            StringTokenizer info = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(info.nextToken());
            int finish = Integer.parseInt(info.nextToken());
            int weight = Integer.parseInt(info.nextToken());
            if (map[start][finish] > weight) {
                map[start][finish] = weight;
                route[start][finish] = start;
            }
        }
        floyid(n);
    }

    static void floyid(int n) {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                if (k == i) continue;
                for (int j = 1; j <= n; j++) {
                    if (k == j || i == j) continue;
                    if (map[i][j] > map[i][k] + map[k][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                        //i에서 j로 이동하려면 중간에 k에서 j로 이동해야 하므로, k에서 j까지의 이전 정점을 저장
                        route[i][j] = route[k][j];
                    }
                }
            }
        }
        answer(n);
    }


    static void answer(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                sb.append(map[i][j] != INF ? map[i][j] : 0).append(" ");
            }
            sb.append("\n");
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (map[i][j] == 0 || map[i][j] == INF) {
                    sb.append(0);
                } else {
                    findRoute(i, j, sb);
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }

    static void findRoute(int start, int finish, StringBuilder sb) {
        Stack<Integer> stack = new Stack<>();
        stack.add(finish);
        while (finish != start) {
            finish = route[start][finish];
            stack.add(finish);
        }
        sb.append(stack.size()).append(" ");
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }
    }
}
