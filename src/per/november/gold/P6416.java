package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

public class P6416 {

    static ArrayList<Integer>[] adjacentList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        Queue<Edge> edges = new LinkedList<>();
        int testCase = 1;

        int max = -1;
        while (true) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            boolean testCaseFinish = false;
            while (input.hasMoreTokens()) {
                int a = Integer.parseInt(input.nextToken());
                int b = Integer.parseInt(input.nextToken());
                if (a == -1 && b == -1) {
                    System.out.println(sb);
                    return;
                }
                if (a == 0 && b == 0) {
                    testCaseFinish = true;
                    break;
                }
                max = Math.max(max, a);
                max = Math.max(max, b);
                edges.add(new Edge(a, b));
            }
            if (testCaseFinish) {
                if (edges.isEmpty()) {
                    sb.append("Case ").append(testCase).append(" is ").append("a tree").append("\n");
                } else if (!checkEdgeCount(edges)) {
                    sb.append("Case ").append(testCase).append(" is not ").append("a tree").append("\n");
                } else {
                    makeTree(edges, max);
                    boolean[] visited = new boolean[max + 1];
                    isTree(max, visited);
                    boolean isAnswer = true;
                    for (int i = 1; i <= max; i++) {
                        if (adjacentList[i] != null && !visited[i]) {
                            isAnswer = false;
                            break;
                        }
                    }
                    if (isAnswer) {
                        sb.append("Case ").append(testCase).append(" is ").append("a tree").append("\n");
                    } else {
                        sb.append("Case ").append(testCase).append(" is not a tree").append("\n");
                    }
                }
                edges.clear();
                max = -1;
                testCase++;
                StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
                if (stringTokenizer.hasMoreTokens()) {
                    System.out.println(sb);
                    return;
                }
            }

        }
    }

    static boolean checkEdgeCount(Queue<Edge> edges) {
        HashMap<Integer, Boolean> hashMap = new HashMap<>();
        for (Edge edge : edges) {
            hashMap.put(edge.a, false);
            hashMap.put(edge.b, false);
        }
        if (edges.size() != hashMap.keySet().size() - 1) {
            return false;
        }
        return true;
    }

    static void isTree(int to, boolean[] visited) {
        visited[to] = true;
        if (null != adjacentList[to])
            for (int goTo : adjacentList[to]) {
                if (!visited[goTo]) {
                    isTree(goTo, visited);
                }
            }
    }

    static void makeTree(Queue<Edge> edges, int max) {
        adjacentList = new ArrayList[max + 1];
        while (!edges.isEmpty()) {
            Edge edge = edges.poll();
            if (null == adjacentList[edge.a]) adjacentList[edge.a] = new ArrayList<>();
            if (null == adjacentList[edge.b]) adjacentList[edge.b] = new ArrayList<>();
            adjacentList[edge.a].add(edge.b);
            adjacentList[edge.b].add(edge.a);
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
