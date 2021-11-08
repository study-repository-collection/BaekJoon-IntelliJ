package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

import static java.lang.System.in;

public class P2668 {
    static int[] sequence = new int[101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        for (int i = 1; i <= N; i++) {
            sequence[i] = Integer.parseInt(br.readLine());
        }

        boolean[] visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                dfs(visited, N, i);
            }
        }
        int count = 0;
        for (int i = 1; i <= N; i++) {
            if (visited[i]) {
                sb.append(i).append("\n");
                count++;
            }
        }
        sb.insert(0, count + "\n");
        System.out.println(sb);
    }


    static void dfs(boolean[] visited, int N, int start) {
        ArrayList<Integer> route = new ArrayList<>();
        route.add(start);
        int temp = start;
        visited[start] = true;
        while (true) {
            if (sequence[temp] == start) {
                return;
            }
            if (!visited[sequence[temp]]) {
                visited[sequence[temp]] = true;
                temp = sequence[temp];
                route.add(temp);
            } else {
                for (int value : route) {
                    visited[value] = false;
                }
                return;
            }
        }
    }

}
