package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P2533 {
    static int[] degree;
    static ArrayList<Integer>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine()); //트리 정점의 개수
        degree = new int[N + 1];
        tree = new ArrayList[N + 1];

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer edge = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(edge.nextToken());
            int b = Integer.parseInt(edge.nextToken());
            if (null == tree[a]) tree[a] = new ArrayList<>();
            if (null == tree[b]) tree[b] = new ArrayList<>();
            tree[a].add(b);
            tree[b].add(a);
            degree[a]++;
            degree[b]++;
        }
    }
}
