package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P16397 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken()); //LED 로 표현된 수
        int T = Integer.parseInt(input.nextToken()); //버튼을 누를 수 있는 횟수
        int G = Integer.parseInt(input.nextToken()); //탈출을 위해 마늘어야하는수
        int ret = solution(N, T, G);
        System.out.println(ret == -1 ? "ANG" : ret);
    }

    static int solution(int N, int T, int G) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[100000];
        visited[N] = true;
        queue.add(N);
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int temp = queue.poll();
                if (temp == G) {
                    return count;
                }
                int nextA = temp + 1;
                if (nextA < 100000 && !visited[nextA]) {
                    visited[nextA] = true;
                    queue.add(nextA);
                }
                int nextB = temp * 2;
                if (nextB < 100000 && nextB > 0) {
                    int sub = 10;
                    while (nextB - sub >= 0) {
                        sub *= 10;
                    }
                    sub /= 10;
                    if (!visited[nextB - sub]) {
                        visited[nextB - sub] = true;
                        queue.add(nextB - sub);
                    }

                }
            }
            if (count >= T) {
                return -1;
            }
            count++;
        }
        return -1;
    }
}
