package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P17837 {

    static final int WHITE = 0;
    static final int RED = 1;
    static final int BLUE = 2;
    static int[][] map;
    static Queue<Integer>[][] horseMap;
    static int[][] dxDy = {{0, 0}, {1, 0}, {-1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer mapInfo = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(mapInfo.nextToken()); //맵의 크기
        int K = Integer.parseInt(mapInfo.nextToken()); //말의 개수
        map = new int[N + 1][N + 1];
        horseMap = new Queue[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                horseMap[i][j] = new LinkedList<>();
                map[i][j] = Integer.parseInt(input.nextToken());
            }
        }
        while (K-- > 0) {
            StringTokenizer horseInfo = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(horseInfo.nextToken());
            int x = Integer.parseInt(horseInfo.nextToken());
            int direction = Integer.parseInt(horseInfo.nextToken());
            horseMap[y][x].add(direction);
        }
    }

    static int solution(int N) {
        int turn = 0;


        return -1;
    }
}
