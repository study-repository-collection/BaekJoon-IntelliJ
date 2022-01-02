package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P13418 {

    static ArrayList<Edge> upEdge = new ArrayList<>();
    static ArrayList<Edge> downEdge = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken());//건물의 개수
        int M = Integer.parseInt(input.nextToken());//도로의 개수

        while (M-- > -1) {
            StringTokenizer edgeInfo = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(edgeInfo.nextToken());
            int b = Integer.parseInt(edgeInfo.nextToken());
            int c = Integer.parseInt(edgeInfo.nextToken());
            if (c == 0) {
                upEdge.add(new Edge(a, b));
            } else {
                downEdge.add(new Edge(a, b));
            }
        }
        System.out.println(worstRoute(N, M) - bestRoute(N, M));
    }

    static int bestRoute(int N, int M) {
        int[] parent = new int[N + 1];
        int answer = 0;
        for (int i = 1; i <= N; i++) parent[i] = i;

        for (Edge edge : downEdge) {
            int a = find(edge.a, parent);
            int b = find(edge.b, parent);
            if (a != b) {
                union(a, b, parent);
            }
        }
        for (Edge edge : upEdge) {
            int a = find(edge.a, parent);
            int b = find(edge.b, parent);
            if (a != b) {
                union(a, b, parent);
                answer++;
            }
        }
        return answer * answer;
    }

    static int worstRoute(int N, int M) {
        int[] parent = new int[N + 1];
        int answer = 0;
        for (int i = 1; i <= N; i++) parent[i] = i;

        for (Edge edge : upEdge) {
            int a = find(edge.a, parent);
            int b = find(edge.b, parent);
            if (a != b) {
                union(a, b, parent);
                answer++;
            }
        }
        for (Edge edge : downEdge) {
            int a = find(edge.a, parent);
            int b = find(edge.b, parent);
            if (a != b) {
                union(a, b, parent);
            }
        }
        return answer * answer;
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

    static class Edge {
        int a;
        int b;

        public Edge(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}
