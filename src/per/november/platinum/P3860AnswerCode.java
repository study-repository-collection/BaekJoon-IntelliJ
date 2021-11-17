package per.november.platinum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class P3860AnswerCode {
    static int w, h, map[][];
    static long dist[][];
    static int dx[] = {-1,0,1,0};
    static int dy[] = {0,1,0,-1};
    static LinkedList<Edge> list;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        w = Integer.parseInt(stz.nextToken());
        h = Integer.parseInt(stz.nextToken());

        while(w != 0) {
            map = new int[w][h];

            //묘비
            int g = Integer.parseInt(br.readLine());
            for(int i = 0; i < g; i++){
                stz = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(stz.nextToken());
                int y = Integer.parseInt(stz.nextToken());
                map[x][y] = -1;
            }

            //귀신 구멍
            int e = Integer.parseInt(br.readLine());
            list = new LinkedList<>();
            dist = new long[w][h];

            for(int i = 0; i < e; i++) {
                stz = new StringTokenizer(br.readLine());
                int x1 = Integer.parseInt(stz.nextToken());
                int y1 = Integer.parseInt(stz.nextToken());
                int x2 = Integer.parseInt(stz.nextToken());
                int y2 = Integer.parseInt(stz.nextToken());
                int t = Integer.parseInt(stz.nextToken());
                map[x1][y1] = 1;
                list.add(new Edge(new Point(x1, y1), new Point(x2, y2), t));
            }

            searchPath();
            boolean cycle = bellmanFord();
            if(cycle) {
                System.out.println("Never");
            }
            else {
                if(dist[w-1][h-1] != Long.MAX_VALUE)
                    System.out.println(dist[w-1][h-1]);
                else
                    System.out.println("Impossible");
            }
            stz = new StringTokenizer(br.readLine());
            w = Integer.parseInt(stz.nextToken());
            h = Integer.parseInt(stz.nextToken());
        }
    }

    public static void searchPath() {
        for(int i = 0; i < w; i++) {
            for(int j = 0; j < h; j++) {
                if(i == w-1 && j == h-1)
                    continue;
                if(map[i][j] != 0)
                    continue;

                for(int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if(check(nx, ny) && map[nx][ny] != -1) {
                        list.add(new Edge(new Point(i, j), new Point(nx ,ny), 1));
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
        for(int i = 0; i < w; i++)
            Arrays.fill(dist[i], INF);
        dist[0][0] = 0;
        boolean update = false;

        for(int i = 0; i < w*h; i++) {
            for(Edge edge : list) {
                Point st = edge.s;
                Point en = edge.e;

                if(dist[st.x][st.y] != INF && dist[en.x][en.y] > dist[st.x][st.y] + edge.w) {
                    dist[en.x][en.y] = dist[st.x][st.y] + edge.w;
                    update = true;
                }
            }
            if(!update)
                break;
        }

        if(update) {
            for(Edge edge : list) {
                Point st = edge.s;
                Point en = edge.e;

                if(dist[st.x][st.y] != INF && dist[en.x][en.y] > dist[st.x][st.y] + edge.w) {
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

    static class Edge {
        Point s, e;
        int w;

        Edge(Point s, Point e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }
    }
}
