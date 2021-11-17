package per.november.platinum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

public class P3860Compare {
    //Answer Code
    static int w, h, map[][];
    static long dist[][];
    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, 1, 0, -1};

    static class Edge {
        Point s, e;
        int w;

        Edge(Point s, Point e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }
    }

    //My Code
    static final int[][] dxDy = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    static int[][] myMap;
    static final int GRAVE = 3;
    static final int EMPTY = 0;
    static final int HOLE = 2;
    static ArrayList<MyEdge> edges = new ArrayList<>();
    static LinkedList<Edge> list;

    static class MyEdge {
        final int startX;
        final int startY;
        final int toX;
        final int toY;
        final int weight;

        public MyEdge(int startX, int startY, int toX, int toY, int weight) {
            this.startX = startX;
            this.startY = startY;
            this.toX = toX;
            this.toY = toY;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        long count = 0;
        while (true) {
            w = random.nextInt(30) + 1;
            h = random.nextInt(30) + 1;
            int g = 1;
            if (w * h != 1) {
                g = random.nextInt(w * h - 1);
            }
            edges.clear();
            myMap = new int[h][w];
            map = new int[w][h];
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    myMap[i][j] = EMPTY;
                }
            }
            boolean[][] visited = new boolean[h][w];
            //묘지를 입력받는다.
            for (int i = 0; i < g; ) {
                int x = random.nextInt(w);
                int y = random.nextInt(h);
                if (!visited[y][x]) {
                    i++;
                    visited[y][x] = true;
                    myMap[y][x] = GRAVE;
                    map[x][y] = -1;
                }
            }
            int e = 0;
            if ((w * h - 1 - g) > 0) {
                e = random.nextInt((w * h - 1 - g)) / 2;
            }
            list = new LinkedList<>();
            dist = new long[w][h];
            for (int i = 0; i < e; ) {
                int x1 = random.nextInt(w);
                int y1 = random.nextInt(h);
                int x2 = random.nextInt(w);
                int y2 = random.nextInt(h);
                if (!visited[y1][x1] && !visited[y2][x2]) {
                    visited[y1][x1] = true;
                    visited[y2][x2] = true;
                    int t = random.nextInt(20000) - 10000;
                    i++;
                    myMap[y1][x1] = HOLE;
                    map[x1][y1] = 1;
                    list.add(new Edge(new Point(x1, y1), new Point(x2, y2), t));
                    //귀신 구멍의 경우 바로 간선을 추가한다.
                    edges.add(new MyEdge(x1, y1, x2, y2, t));
                }
            }
            searchPath1123();
            boolean cycle = bellmanFord();
            String temp1;
            if (cycle) {
                temp1 = "Never";
            } else {
                if (dist[w - 1][h - 1] != Long.MAX_VALUE)
                    temp1 = String.valueOf(dist[w - 1][h - 1]);
                else
                    temp1 = "Impossible";
            }
            makeAdjacentList(w, h);
            int ret = solution(w, h);
            String temp2 = "";
            if (ret == INF)
                temp2 = "Impossible";
            else if (ret == -1) {
                temp2 = "Never";
            } else {
                temp2 = String.valueOf(ret);
            }
            if (!temp1.equals(temp2)) {
                System.out.println(count + " " + temp1 + " " + temp2);
                break;
            } else {
                System.out.println(count++ + " " + temp1 + " " + temp2);
            }
        }

    }

    static void makeAdjacentList(int W, int H) {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                //현재 지점이 묘비거나 귀신 구멍일 경우는 간선을 추가하지 않는다.
                if (myMap[i][j] == GRAVE || myMap[i][j] == HOLE) continue;
                //현재 지점이 도착 지점일 경우에 간선을 추가하지 않는다.
                if (i == H - 1 && j == W - 1) continue;
                for (int[] move : dxDy) {
                    int X = j + move[0];
                    int Y = i + move[1];
                    if (canVisit(X, Y, W, H) && myMap[Y][X] != GRAVE) {
                        edges.add(new MyEdge(j, i, X, Y, 1));
                    }
                }
            }
        }
    }

    static final int INF = 1987654321;

    static int solution(int W, int H) {
        int[][] myDist = new int[H][W];
        for (int i = 0; i < H; i++) {
            Arrays.fill(myDist[i], INF);
        }
        //시작 지점의 거리를 0으로 표시
        myDist[0][0] = 0;
        int numberOfVertex = H * W;
        for (int n = 0; n < numberOfVertex; n++) {
            boolean isUpdate = false;
            for (MyEdge edge : edges) {
                if (myDist[edge.startY][edge.startX] != INF && myDist[edge.toY][edge.toX] > myDist[edge.startY][edge.startX] + edge.weight) {
                    myDist[edge.toY][edge.toX] = myDist[edge.startY][edge.startX] + edge.weight;
                    isUpdate = true;
                }
            }
            //음의 사이클이 존재한다면 바로 -1 반환
            if (isUpdate && n == numberOfVertex - 1) {
                return -1;
            }
        }
//음의 사이클이 존재하지 않는다면, 도착지점까지의 거리 반환
        return myDist[H - 1][W - 1];
    }

    static boolean canVisit(int x, int y, int W, int H) {
        return (x >= 0 && y >= 0 && x < W && y < H);
    }

    public static void searchPath1123() {
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if (i == w - 1 && j == h - 1)
                    continue;
                if (map[i][j] != 0)
                    continue;

                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if (check(nx, ny) && map[nx][ny] != -1) {
                        list.add(new Edge(new Point(i, j), new Point(nx, ny), 1));
                    }
                }
            }
        }
    }

    public static boolean check(int x, int y) {
        return x >= 0 && y >= 0 && x < w && y < h;
    }

    public static boolean bellmanFord() {
        long INF = Long.MAX_VALUE;
        for (int i = 0; i < w; i++)
            Arrays.fill(dist[i], INF);
        dist[0][0] = 0;
        boolean update = false;

        for (int i = 0; i < w * h; i++) {
            for (Edge edge : list) {
                Point st = edge.s;
                Point en = edge.e;

                if (dist[st.x][st.y] != INF && dist[en.x][en.y] > dist[st.x][st.y] + edge.w) {
                    dist[en.x][en.y] = dist[st.x][st.y] + edge.w;
                    update = true;
                }
            }
            if (!update)
                break;
        }

        if (update) {
            for (Edge edge : list) {
                Point st = edge.s;
                Point en = edge.e;

                if (dist[st.x][st.y] != INF && dist[en.x][en.y] > dist[st.x][st.y] + edge.w) {
                    return true;
                }
            }
        }

        return false;
    }

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
