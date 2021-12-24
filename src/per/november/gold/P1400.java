package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

public class P1400 {
    static final int[][] dxDy = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    static final char START = 'A';
    static final char ARRIVAL = 'B';
    static final char CANT = '.';
    static final char ROAD = '#';
    static char[][] map;
    static CrossWay[] crossWays;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            StringTokenizer mapInfo = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(mapInfo.nextToken());
            int M = Integer.parseInt(mapInfo.nextToken());
            if (N == 0 && M == 0) {
                break;
            }
            int maxCrossWay = -1;
            Point startPoint = null;
            Point endPoint = null;
            map = new char[N][M];
            for (int i = 0; i < N; i++) {
                String input = br.readLine();
                for (int j = 0; j < M; j++) {
                    map[i][j] = input.charAt(j);
                    if (map[i][j] >= '0' && map[i][j] <= '9') {
                        maxCrossWay = Math.max(maxCrossWay, map[i][j] - '0');
                    } else if (map[i][j] == START) {
                        startPoint = new Point(j, i, 0);
                    } else if (map[i][j] == ARRIVAL) {
                        endPoint = new Point(j, i, 0);
                    }
                }
            }

            if (maxCrossWay != -1) {
                crossWays = new CrossWay[maxCrossWay + 1];
                for (int i = 0; i <= maxCrossWay; i++) {
                    StringTokenizer crossWayInfo = new StringTokenizer(br.readLine());
                    int number = Integer.parseInt(crossWayInfo.nextToken()); //교차로 번호
                    char direction = crossWayInfo.nextToken().charAt(0); //교차로 방향
                    int ai = Integer.parseInt(crossWayInfo.nextToken()); //동서 (-) 방향 신호등이 켜진 시간
                    int bi = Integer.parseInt(crossWayInfo.nextToken()); //남북(|) 방향 신호가 켜진 시간
                    crossWays[number] = new CrossWay(direction, ai, bi);
                }
            }
            if (null == startPoint || endPoint == null) {
                sb.append("impossible").append("\n");
                br.readLine();
                continue;
            }
            int ret = solution(N, M, startPoint, endPoint);
            sb.append(ret == 987654321 ? "impossible" : ret).append("\n");
            br.readLine();
        }
        System.out.println(sb);
    }

    static int solution(int N, int M, Point startPoint, Point endPoint) {
        Queue<Point> points = new LinkedList<>();
        int[][] visited = new int[N][M];
        for (int i = 0; i < N; i++) Arrays.fill(visited[i], 987654321);
        points.add(startPoint);
        visited[startPoint.y][startPoint.x] = 0;
        while (!points.isEmpty()) {
            Point temp = points.poll();
            for (int[] move : dxDy) {
                int X = temp.x + move[0];
                int Y = temp.y + move[1];
                if (canVisit(X, Y, N, M)) {
                    int nextTime = temp.time + 1;
                    //이동하려는 곳이 도로라서 그냥 이동가능하면? 현재 최소시간보다 빨라야 이동가능
                    if ((map[Y][X] == ROAD || map[Y][X] == ARRIVAL) && visited[Y][X] > nextTime) {
                        visited[Y][X] = nextTime;
                        points.add(new Point(X, Y, nextTime));
                    }
                    //이동하려는 곳이 교차로면?
                    else if (map[Y][X] >= '0' && map[Y][X] <= '9') {
                        int crossWayNumber = map[Y][X] - '0';
                        CrossWay thisCrossWay = crossWays[crossWayNumber];
                        nextTime = thisCrossWay.nextTurnOnTime(temp.time + 1, temp, new Point(X, Y, 0));
                        if (visited[Y][X] > nextTime) {
                            visited[Y][X] = nextTime;
                            points.add(new Point(X, Y, nextTime));
                        }
                    }
                }
            }
        }
        return visited[endPoint.y][endPoint.x];
    }

    static boolean canVisit(int x, int y, int N, int M) {
        return x >= 0 && y >= 0 && x < M && y < N;
    }


    static class CrossWay {
        final char initialDirection;
        final int ai; //동서방향
        final int bi; //남북방향

        public CrossWay(char direction, int ai, int bi) {
            this.initialDirection = direction;
            this.ai = ai;
            this.bi = bi;
        }

        public int nextTurnOnTime(int time, Point originalPosition, Point thisPosition) {
            int direction = 0;
            //좌우 진입
            if (originalPosition.x != thisPosition.x) {
            } else {
                direction = 1;
            }
            int cycle = ai + bi;
            int northTimeStart = 1;
            int northTimeEnd = 1;
            int eastStartTime = 1;
            int eastEndTime = 1;
            if (initialDirection == '|') {
                northTimeEnd = 1 + bi - 1;
                eastStartTime = northTimeEnd + 1;
                eastEndTime = eastStartTime + ai - 1;
            } else {
                eastEndTime = eastStartTime + ai - 1;
                northTimeStart = eastEndTime + 1;
                northTimeEnd = northTimeStart + bi - 1;
            }
            if (direction == 0) {
                while (eastStartTime < time && eastEndTime < time) {
                    eastStartTime += cycle;
                    eastEndTime += cycle;
                }
                if (eastStartTime <= time && eastEndTime >= time) {
                    return time;
                } else {
                    return eastStartTime;
                }
            } else {
                while (northTimeStart < time && northTimeEnd < time) {
                    northTimeStart += cycle;
                    northTimeEnd += cycle;
                }
                if (northTimeStart <= time && northTimeEnd >= time) {
                    return time;
                } else {
                    return northTimeStart;
                }
            }
        }
    }

    static class Point {
        int x;
        int y;
        int time;

        public Point(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}
