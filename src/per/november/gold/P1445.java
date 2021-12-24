package per.november.gold;

import javax.print.attribute.standard.Finishings;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1445 {
    static char[][] map;
    static final char GARBAGE = 'g';
    static final char NEAR = 'n';
    static final char START = 'S';
    static final char FINISH = 'F';
    static final char EMPTY = '.';
    static final int[][] dxDy = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        Point startPoint = null;
        Point endPoint = null;
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);
                if (map[i][j] == START) {
                    startPoint = new Point(j, i);
                } else if (map[i][j] == FINISH) {
                    endPoint = new Point(j, i);
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == GARBAGE) {
                    for (int[] move : dxDy) {
                        int X = j + move[0];
                        int Y = i + move[1];
                        if (canVisit(X, Y, N, M) && map[Y][X] != START && map[Y][X] != FINISH && map[Y][X] != GARBAGE) {
                            map[Y][X] = NEAR;
                        }
                    }
                }
            }
        }
        solution(N, M, startPoint, endPoint);
    }

    static void solution(int N, int M, Point startPoint, Point endPoint) {
        Queue<Point> points = new LinkedList<>();
        MapInfo[][] visited = new MapInfo[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = new MapInfo();
            }
        }

        visited[startPoint.y][startPoint.x].nearCount = 0;
        visited[startPoint.y][startPoint.x].garbageCount = 0;

        points.add(startPoint);

        while (!points.isEmpty()) {
            Point point = points.poll();
            for (int[] move : dxDy) {
                int X = point.x + move[0];
                int Y = point.y + move[1];
                if (canVisit(X, Y, N, M)) {
                    if (map[Y][X] == GARBAGE) {
                        if (visited[Y][X].garbageCount > point.garbageCount + 1) {
                            visited[Y][X].garbageCount = point.garbageCount + 1;
                            visited[Y][X].nearCount = point.nearCount;
                            points.add(new Point(X, Y, point.garbageCount + 1, point.nearCount));
                        } else if (visited[Y][X].garbageCount == point.garbageCount + 1 && visited[Y][X].nearCount > point.nearCount) {
                            visited[Y][X].nearCount = point.nearCount;
                            points.add(new Point(X, Y, point.garbageCount + 1, point.nearCount));
                        }
                    } else if (map[Y][X] == NEAR) {
                        if (visited[Y][X].garbageCount > point.garbageCount) {
                            visited[Y][X].garbageCount = point.garbageCount;
                            visited[Y][X].nearCount = point.nearCount + 1;
                            points.add(new Point(X, Y, point.garbageCount, point.nearCount + 1));
                        } else if (visited[Y][X].garbageCount == point.garbageCount && visited[Y][X].nearCount > point.nearCount + 1) {
                            visited[Y][X].nearCount = point.nearCount + 1;
                            points.add(new Point(X, Y, point.garbageCount, point.nearCount + 1));
                        }
                    } else {
                        if (visited[Y][X].garbageCount > point.garbageCount) {
                            visited[Y][X].garbageCount = point.garbageCount;
                            visited[Y][X].nearCount = point.nearCount;
                            points.add(new Point(X, Y, point.garbageCount, point.nearCount));
                        } else if (visited[Y][X].garbageCount == point.garbageCount && visited[Y][X].nearCount > point.nearCount) {
                            visited[Y][X].nearCount = point.nearCount;
                            points.add(new Point(X, Y, point.garbageCount, point.nearCount));
                        }
                    }
                }
            }
        }
        MapInfo mapInfo = visited[endPoint.y][endPoint.x];
        System.out.println(mapInfo.garbageCount + " " + mapInfo.nearCount);
        ;
    }

    static boolean canVisit(int x, int y, int N, int M) {
        return x >= 0 && y >= 0 && x < M && y < N;
    }

    static class MapInfo {
        int garbageCount = 987654321;
        int nearCount = 987654321;
    }

    static class Point {
        int x;
        int y;
        int garbageCount = 0;
        int nearCount = 0;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Point(int x, int y, int garbageCount, int nearCount) {
            this.x = x;
            this.y = y;
            this.garbageCount = garbageCount;
            this.nearCount = nearCount;
        }
    }
}
