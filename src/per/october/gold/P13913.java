package per.october.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P13913 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken());
        int K = Integer.parseInt(input.nextToken());
        if (N == K) {
            System.out.println(0);
            System.out.println(N);
            return;
        }
        solution(N, K);
    }

    static void solution(int N, int K) {
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[100001];
        int[] route = new int[100001];
        route[N] = N;
        queue.add(N);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int index = queue.poll();
                if (index == K) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(count).append("\n");
                    Stack<Integer> answer = new Stack<>();
                    do {
                        answer.add(K);
                        K = route[K];
                    } while (K != N);
                    answer.add(N);
                    while (!answer.isEmpty()) {
                        sb.append(answer.pop()).append(" ");
                    }
                    System.out.print(sb);
                    return;
                }
                if (index > 0 && !visited[index - 1]) {
                    visited[index - 1] = true;
                    route[index - 1] = index;
                    queue.add(index - 1);
                }
                if (index < 100000 && !visited[index + 1]) {
                    visited[index + 1] = true;
                    route[index + 1] = index;
                    queue.add(index + 1);
                }
                if (index * 2 <= 100000 && !visited[index * 2]) {
                    visited[index * 2] = true;
                    route[index * 2] = index;
                    queue.add(index * 2);
                }
            }
            count++;
        }
    }
}
