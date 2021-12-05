package per.november.gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

public class P19238 {

    static final int WALL = 1;
    static final int EMPTY = 0;

    static final int[][] dxDy = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    static int[][] map;
    static Client[][] clientMap;
    static Taxi taxi;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //맵의 크기
        int M = Integer.parseInt(st.nextToken()); //승객의 수
        int K = Integer.parseInt(st.nextToken()); //초기 연료
        map = new int[N + 1][N + 1];
        clientMap = new Client[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer mapInfo = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(mapInfo.nextToken());
            }
        }
        StringTokenizer startPosition = new StringTokenizer(br.readLine());

        int y = Integer.parseInt(startPosition.nextToken());
        int x = Integer.parseInt(startPosition.nextToken());
        taxi = new Taxi(x, y, K);
        for (int i = 0; i < M; i++) {
            StringTokenizer clientInfo = new StringTokenizer(br.readLine());
            int sY = Integer.parseInt(clientInfo.nextToken());
            int sX = Integer.parseInt(clientInfo.nextToken());
            int fY = Integer.parseInt(clientInfo.nextToken());
            int fX = Integer.parseInt(clientInfo.nextToken());
            clientMap[sY][sX] = new Client(sX, sY, fX, fY);
        }
        System.out.println(solution(N, M));
    }

    static int solution(int N, int M) {
        while (M-- > 0) {
            if (!taxi.getClient(N)) {
                return -1;
            } else if (!taxi.goToObject(N)) {
                return -1;
            }
        }
        return taxi.k;
    }

    static class Taxi {
        int x;
        int y;
        int k;
        Client client;

        public Taxi(int x, int y, int k) {
            this.x = x;
            this.y = y;
            this.k = k;
        }

        public boolean getClient(int N) {
            Queue<Point> points = new LinkedList<>();
            boolean[][] visited = new boolean[N + 1][N + 1];
            PriorityQueue<Client> clients = new PriorityQueue<>((o1, o2) -> {
                if (o1.sY == o2.sY) {
                    return Integer.compare(o1.sX, o2.sX);
                } else return Integer.compare(o1.sY, o2.sY);
            });
            visited[y][x] = true;
            points.add(new Point(x, y));
            int count = 0;
            while (!points.isEmpty()) {
                int size = points.size();
                for (int i = 0; i < size; i++) {
                    Point point = points.poll();
                    if (clientMap[point.y][point.x] != null) {
                        clients.add(clientMap[point.y][point.x]);
                    }
                    for (int[] move : dxDy) {
                        int X = point.x + move[0];
                        int Y = point.y + move[1];
                        if (canVisit(X, Y, N) && !visited[Y][X] && map[Y][X] != WALL) {
                            visited[Y][X] = true;
                            points.add(new Point(X, Y));
                        }
                    }
                }
                if (!clients.isEmpty()) break;
                count++;
            }

            if (clients.isEmpty()) {
                return false;
            } else {
                client = clients.poll();
                clientMap[client.sY][client.sX] = null;
                y = client.sY;
                x = client.sX;
                k -= count;
               // System.out.println("taxi take client at " + client.sY + " " + client.sX);
                return true;
            }
        }

        public boolean goToObject(int N) {
            int count = 0;
            Queue<Point> points = new LinkedList<>();
            boolean[][] visited = new boolean[N + 1][N + 1];
            visited[y][x] = true;
            points.add(new Point(x, y));
            while (!points.isEmpty()) {
                int size = points.size();
                for (int i = 0; i < size; i++) {
                    Point point = points.poll();
                    if (point.y == client.fY && point.x == client.fX) {
                        y = point.y;
                        x = point.x;
                        k -= count;
                        client = null;
                        if (k < 0) {
                            return false;
                        } else {
                            k += (count * 2);
                           // System.out.println("taxi 성공적으로 client at " + point.y + " " + point.x);
                            return true;
                        }
                    }
                    for (int[] move : dxDy) {
                        int X = point.x + move[0];
                        int Y = point.y + move[1];
                        if (canVisit(X, Y, N) && !visited[Y][X] && map[Y][X] != WALL) {
                            visited[Y][X] = true;
                            points.add(new Point(X, Y));
                        }
                    }
                }
                count++;
            }
            return false;
        }

        static boolean canVisit(int x, int y, int N) {
            return x >= 1 && y >= 1 && x <= N && y <= N;
        }
    }

    static class Client {
        int sX;
        int sY;
        int fX;
        int fY;

        public Client(int sX, int sY, int fX, int fY) {
            this.sX = sX;
            this.sY = sY;
            this.fX = fX;
            this.fY = fY;
        }
    }
}



