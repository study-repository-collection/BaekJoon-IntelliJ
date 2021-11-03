package per.october.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P11437 {


    static int[] root;
    static int[] levels;
    static ArrayList<Integer>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        tree = new ArrayList[N + 1];
        levels = new int[N + 1];
        root = new int[N + 1];
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer rootInfo = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(rootInfo.nextToken());
            int b = Integer.parseInt(rootInfo.nextToken());
            if (null == tree[a]) tree[a] = new ArrayList<>();
            if (null == tree[b]) tree[b] = new ArrayList<>();
            tree[a].add(b);
            tree[b].add(a);
        }
        decideLevel(1, 1, 1);
        int M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(findParent(a, b)).append("\n");
        }
        System.out.print(sb);
    }

    static int findParent(int a, int b) {
        if (levels[a] < levels[b]) {
            int temp = a;
            a = b;
            b = temp;
        }
        while (levels[a] != levels[b]) {
            a = root[a];
        }

        while (a != b) {
            a = root[a];
            b = root[b];
        }
        return a;
    }

    static void decideLevel(int from, int to, int level) {
        levels[to] = level;
        root[to] = from;
        for (int goTo : tree[to]) {
            if (goTo != from) {
                decideLevel(to, goTo, level + 1);
            }
        }
    }
}
