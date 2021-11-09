package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P13023 {
    static ArrayList<Integer>[] adjacentList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        adjacentList = new ArrayList[N];

        while (M-- > 0) {
            StringTokenizer edgeInfo = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(edgeInfo.nextToken());
            int b = Integer.parseInt(edgeInfo.nextToken());
            if (null == adjacentList[a]) adjacentList[a] = new ArrayList<>();
            if (null == adjacentList[b]) adjacentList[b] = new ArrayList<>();
            adjacentList[a].add(b);
            adjacentList[b].add(a);
        }

        solution(N);
        System.out.println(find ? "1" : "0");
    }

    static boolean find = false;

    static void solution(int N) {
        for (int i = 0; i < N; i++) {
            if (find) return;
            dfs(i, new boolean[N], 1);
        }
    }

    static void dfs(int to, boolean[] visited, int depth) {
        if (find) return;
        visited[to] = true;
        if (depth == 5) {
            find = true;
            return;
        }

        if (null != adjacentList[to]) {
            for (int goTo : adjacentList[to]) {
                if (!visited[goTo]) {
                    dfs(goTo, visited, depth + 1);
                }
            }
        }
        visited[to] = false;
    }
}
