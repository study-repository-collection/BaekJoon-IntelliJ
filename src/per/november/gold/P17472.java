package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P17472 {
    static int[][] map;
    static final int LAND = 1;
    static final int SEA = 0;
    static final int[][] dxDy = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    static class Edge {
        final int a;
        final int b;
        final int length;

        public Edge(int a, int b, int length) {
            this.a = a;
            this.b = b;
            this.length = length;
        }
    }

    static PriorityQueue<Edge> edges = new PriorityQueue<>(Comparator.comparingInt(o -> o.length));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer input = new StringTokenizer(br.readLine());
        //N과 M은 크기가 1이상 10이하
        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        //섬의 개수는 2개 이상 6개 이하
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            StringTokenizer mapInfo = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(mapInfo.nextToken());

            }
        }
        //섬을 모두 구한 뒤, 섬을 잇는 최소 간선을 모두 찾아주자, 그런 다음 최소 스패닝 트리를 구성하면 정답이 된다.
        int landCount = makeLandNumber(N, M);
        makeEdge(N, M);
        System.out.println(kruskal(landCount));
    }

    static int kruskal(int landCount) {
        int answer = 0;
        int[] parent = new int[landCount + 2];
        for (int i = 2; i <= landCount + 1; i++) {
            parent[i] = i;
        }
        while (!edges.isEmpty()) {
            Edge edge = edges.poll();
            int aParent = find(edge.a, parent);
            int bParent = find(edge.b, parent);
            if (aParent != bParent) {
                union(edge.a, edge.b, parent);
                answer += edge.length;
            }
        }
        int root = parent[2];
        for (int i = 3; i <= landCount + 1; i++) {
            if (find(i, parent) != root) {
                return -1;
            }
        }
        return answer;
    }

    static void makeEdge(int N, int M) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0) {
                    for (int k = 0; k < 4; k++) {
                        makeEdgeByDirection(k, N, M, map[i][j], j, i);
                    }
                }
            }
        }
    }

    static void makeEdgeByDirection(int direction, int N, int M, int startLandNumber, int x, int y) {
        int length = 0;
        x += dxDy[direction][0];
        y += dxDy[direction][1];
        while (canVisit(x, y, N, M) && map[y][x] == 0) {
            x += dxDy[direction][0];
            y += dxDy[direction][1];
            length++;
        }
        if (canVisit(x, y, N, M) && map[y][x] != startLandNumber && map[y][x] != 0 && length > 1) {
            edges.add(new Edge(startLandNumber, map[y][x], length));
        }
    }

    /**
     * 섬을 모두 구한 뒤 섬의 개수를 반환하는 함수
     *
     * @param N 맵의 높이
     * @param M 맵의 넓이
     */
    static int makeLandNumber(int N, int M) {
        int currentLandNumber = 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    dfs(j, i, N, M, currentLandNumber++);
                }
            }
        }
        return currentLandNumber - 2;
    }

    static void dfs(int x, int y, int N, int M, int landNumber) {
        map[y][x] = landNumber;
        for (int[] move : dxDy) {
            int X = x + move[0];
            int Y = y + move[1];
            if (canVisit(X, Y, N, M) && map[Y][X] == 1) {
                dfs(X, Y, N, M, landNumber);
            }
        }
    }

    static boolean canVisit(int x, int y, int N, int M) {
        return x >= 0 && y >= 0 && x < M && y < N;
    }

    //다리의 방향이 중간에 바뀌면 안됨, 다리의 길이는 최소 2이상이어야 함
    static int find(int a, int[] parent) {
        if (a == parent[a]) return a;
        else return parent[a] = find(parent[a], parent);
    }

    static void union(int a, int b, int[] parent) {
        a = find(a, parent);
        b = find(b, parent);
        if (a == b) return;
        if (a > b) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }
    }
}
