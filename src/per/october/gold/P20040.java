package per.october.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P20040 {
    static Queue<Edge> queue = new LinkedList<>();

    static class Edge {
        final int start;
        final int finish;

        public Edge(int start, int finish) {
            this.start = start;
            this.finish = finish;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken()); //점의 개수
        int M = Integer.parseInt(input.nextToken()); //선분의 개수
        int tempM = M;
        while (tempM-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int finish = Integer.parseInt(st.nextToken());
            queue.add(new Edge(start, finish));
        }
        int result;
        System.out.println((result = solution(N)) == M + 1 ? 0 : result);
    }

    static int find(int a, int[] parent) {
        if (a == parent[a]) return a;
        else return parent[a] = find(parent[a], parent);
    }

    static void union(int a, int b, int[] parent) {
        a = find(a, parent);
        b = find(b, parent);

        if (a > b) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }
    }

    static int solution(int N) {
        int[] parent = new int[N];
        int count = 0;
        for (int i = 0; i < N; i++) parent[i] = i;
        while (!queue.isEmpty()) {
            Edge edge = queue.poll();
            count++;
            int aParent = find(edge.start, parent);
            int bParent = find(edge.finish, parent);

            if (aParent != bParent) {
                union(edge.start, edge.finish, parent);
            } else {
                return count;
            }
        }
        return count + 1;
    }
}
