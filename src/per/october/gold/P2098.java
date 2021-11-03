package per.october.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P2098 {

    static ArrayList<Integer>[] adjacentList;
    static Vertex[] vertices;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int K = Integer.parseInt(br.readLine());//테스트 케이스의 개수
        while (K-- > 0) {
            StringTokenizer info = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(info.nextToken()); //정점의 개수
            int E = Integer.parseInt(info.nextToken()); //간선의 개수
            vertices = new Vertex[V + 1];
            adjacentList = new ArrayList[V + 1];
            for (int i = 1; i <= V; i++) {
                vertices[i] = new Vertex();
            }
            while (E-- > 0) {
                StringTokenizer input = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(input.nextToken());
                int B = Integer.parseInt(input.nextToken());
                if (null == adjacentList[A]) {
                    adjacentList[A] = new ArrayList<>();
                }
                if (null == adjacentList[B]) {
                    adjacentList[B] = new ArrayList<>();
                }
                adjacentList[A].add(B);
                adjacentList[B].add(A);
            }
            sb.append(solution(V)).append("\n");
        }
        System.out.println(sb);
    }

    static boolean isBipartite = true;

    static String solution(int V) {
        isBipartite = true;
        boolean[] visited = new boolean[V + 1];
        for (int i = 1; i <= V; i++) {
            if (!visited[i]) {
                dfs(i, visited, Color.RED);
            }
        }
        return isBipartite ? "YES" : "NO";
    }

    static void dfs(int to, boolean[] visited, Color color) {
        visited[to] = true;
        vertices[to].color = color;
        if (adjacentList[to] != null) {
            for (int goTo : adjacentList[to]) {
                if (vertices[goTo].color == Color.UNDEFINED) {
                    dfs(goTo, visited, color == Color.RED ? Color.BLUE : Color.RED);
                } else if (vertices[goTo].color != (color == Color.RED ? Color.BLUE : Color.RED)) {
                    isBipartite = false;
                    return;
                }
            }
        }
    }


    static class Vertex {
        Color color = Color.UNDEFINED;
    }

    enum Color {
        RED,
        BLUE,
        UNDEFINED
    }
}
