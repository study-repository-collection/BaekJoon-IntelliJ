package per.november.gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P10711 {
    static int[][] map;
    static final int EMPTY = -100000000;
    static final int[][] dxDy = {{-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer mapInfo = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(mapInfo.nextToken());
        int M = Integer.parseInt(mapInfo.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                if (input.charAt(j) == '.') {
                    map[i][j] = EMPTY;
                } else {
                    map[i][j] = input.charAt(j) - '0';
                }
            }
        }
        System.out.println(solution(N, M));
    }

    static int solution(int N, int M) {
        return bfs(initEmptyCount(N, M));
    }


    static int bfs(Queue<Point> points) {
        int count = 0;
        while (!points.isEmpty()) {
            //Queue에 들어가 있는 좌표가 모두 이번 턴에 무너지는 모래성이므로, 사이즈만큼 반복 진행
            int size = points.size();
            while (size-- > 0) {
                Point temp = points.poll();
                //무너진 모래성 좌표 주변의 강도를 전부 1씩 낮춰줌
                for (int[] move : dxDy) {
                    int X = temp.x + move[0];
                    int Y = temp.y + move[1];
                    map[Y][X]--;
                    //만약 강도가 0이 되버리는 모래성이 있으면, 무너질 예정이므로 큐에 추가한다.
                    if (map[Y][X] == 0) {
                        points.add(new Point(X, Y));
                    }
                }
            }
            count++;
        }
        return count;
    }

    //첫 파도에 의해 무너질 모래성들을 전부 Queue 에다 집어넣는다.
    static Queue<Point> initEmptyCount(int N, int M) {
        Queue<Point> points = new LinkedList<>();
        for (int i = 1; i < N - 1; i++) {
            for (int j = 1; j < M - 1; j++) {
                //만약 현재 좌표가 모래성이면, 주변 빈 칸을 비교해 강도를 낮춘다.
                if (map[i][j] > 0) {
                    for (int[] move : dxDy) {
                        int X = j + move[0];
                        int Y = i + move[1];
                        if (map[Y][X] == EMPTY) {
                            map[i][j]--;
                        }
                    }
                    //만약 강도가 0 이하가 되면 부서진다는 뜻이므로 큐에 추가한다.
                    if (map[i][j] <= 0) {
                        points.add(new Point(j, i));
                    }
                }
            }
        }
        return points;
    }
}
