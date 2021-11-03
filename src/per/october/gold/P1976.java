package per.october.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1976 {
    static ArrayList<Integer>[] adjacentList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        adjacentList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) adjacentList[i] = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int a = Integer.parseInt(input.nextToken());
                if (i == j) continue;
                if (a == 1) {
                    adjacentList[i].add(j);
                }
            }
        }
        int[] itinerary = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(solution(N, itinerary) ? "YES" : "NO");
    }

    static int findParent(int a, int[] parent) {
        if (a == parent[a]) return a;
        else return parent[a] = findParent(parent[a], parent);
    }

    static void union(int a, int b, int[] parent) {
        a = findParent(a, parent);
        b = findParent(b, parent);
        if (a == b) return;
        if (a > b) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }
    }

    static boolean solution(int N, int[] itinerary) {
        int[] parent = new int[N + 1];
        for (int i = 1; i <= N; i++) parent[i] = i;

        for (int i = 1; i <= N; i++) {
            for (int to : adjacentList[i]) {
                union(i, to, parent);
            }
        }

        int root = parent[itinerary[0]];
        for (int i = 1; i < itinerary.length; i++) {
            if (parent[itinerary[i]] != root)
                return false;
        }
        return true;
    }
}
