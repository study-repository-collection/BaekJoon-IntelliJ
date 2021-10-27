package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P5014 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer input = new StringTokenizer(br.readLine());

        int F = Integer.parseInt(input.nextToken()); //사무실 총 층 수
        int S = Integer.parseInt(input.nextToken()); //현재 위치
        int G = Integer.parseInt(input.nextToken()); //이동할 위치
        int U = Integer.parseInt(input.nextToken()); //위 버튼 누를 시 이동 칸 수
        int D = Integer.parseInt(input.nextToken()); //아래 버튼 누를 시 이동 칸 수

        int ret;
        System.out.println((ret = bfs(F, S, G, U, D)) == -1 ? "use the stairs" : ret);
    }


    static int bfs(int F, int S, int G, int U, int D) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[F + 1];
        int count = 0;
        visited[S] = true;
        queue.add(S);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int floor = queue.poll();
                if (floor == G) {
                    return count;
                }
                if (floor + U <= F && !visited[floor + U]) {
                    queue.add(floor + U);
                    visited[floor + U] = true;
                }
                if (floor - D >= 1 && !visited[floor - D]) {
                    queue.add(floor - D);
                    visited[floor - D] = true;
                }
            }
            count++;
        }
        return -1;
    }
}
