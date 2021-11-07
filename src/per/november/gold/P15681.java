package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P15681 {

    static ArrayList<Integer>[] trees;
    static int[] treesChild;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken()); //트리 정점의 수
        int R = Integer.parseInt(input.nextToken()); //루트의 번호
        int Q = Integer.parseInt(input.nextToken()); //쿼리의 수
        trees = new ArrayList[N + 1];
        treesChild = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            trees[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer treeInfo = new StringTokenizer(br.readLine());
            int U = Integer.parseInt(treeInfo.nextToken());
            int V = Integer.parseInt(treeInfo.nextToken());
            trees[V].add(U);
            trees[U].add(V);
        }
        initMemorization(new boolean[N + 1], R);
        while (Q-- > 0) {
            int Query = Integer.parseInt(br.readLine());
            sb.append(treesChild[Query]).append("\n");
        }
        System.out.println(sb);
    }


    static int initMemorization(boolean[] visited, int to) {
        visited[to] = true;
        int count = 1;

        for (int goTo : trees[to]) {
            if (!visited[goTo]) {
                count += initMemorization(visited, goTo);
            }
        }

        return treesChild[to] = count;

    }
}
