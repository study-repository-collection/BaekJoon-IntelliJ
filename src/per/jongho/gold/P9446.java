package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

public class P9446 {
    static int[] arr;
    static boolean[] visited;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine()); //테스트 케이스의 개수
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine()); //학생의 수
            arr = new int[N + 1];
            visited = new boolean[N + 1];
            answer = 0;
            StringTokenizer arrInfo = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(arrInfo.nextToken());
                if (arr[i] == i) {
                    answer++;
                    visited[i] = true;
                }
            }
            sb.append(solution(N)).append("\n");
        }
        System.out.print(sb);
    }


    static int solution(int N) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                dfs(i, visited, queue);
                answer += queue.size();
                queue = new LinkedList<>();
            }
        }
        return N - answer;
    }

    static void dfs(int to, boolean[] visited, Queue<Integer> queue) {
        visited[to] = true;
        queue.add(to);
        if (!visited[arr[to]]) {
            dfs(arr[to], visited, queue);
        } else {
            while (!queue.isEmpty() && queue.peek() != arr[to]) {
                queue.poll();
            }
        }
    }
}
