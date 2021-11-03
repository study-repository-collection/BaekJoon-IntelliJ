package per.october.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1068 {

    static ArrayList<Integer>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N];
        int root = -1;
        StringTokenizer input = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int A = Integer.parseInt(input.nextToken());
            if (A != -1) {
                if (null == tree[A]) tree[A] = new ArrayList<>();
                tree[A].add(i);
            } else {
                root = i;
            }
        }
        int delete = Integer.parseInt(br.readLine());
        if (delete == root) {
            System.out.println(0);
            return;
        }
        deleteNode(delete, N);
        dfs(root, new boolean[N]);
        System.out.println(leaf);
    }

    static int leaf = 0;

    static void deleteNode(int a, int N) {
        for (int i = 0; i < N; i++) {
            if (null != tree[i] && tree[i].contains(a)) {
                tree[i].remove(Integer.valueOf(a));
                return;
            }
        }
    }

    static void dfs(int to, boolean[] visited) {
        visited[to] = true;
        if (null != tree[to] && !tree[to].isEmpty()) {
            for (int i = 0; i < tree[to].size(); i++) {
                if (!visited[tree[to].get(i)]) {
                    dfs(tree[to].get(i), visited);
                }
            }
        } else {
            leaf++;
        }
    }
}
