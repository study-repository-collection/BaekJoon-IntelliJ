package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1405 {
    static int[][] dxDy = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static double[] percentage = new double[4];
    static int[][] map = new int[29][29];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stringTokenizer.nextToken());
        for (int i = 0; i < 4; i++) {
            percentage[i] = Double.parseDouble(stringTokenizer.nextToken()) / 100;
        }
        int[][] visitCount = new int[29][29];
        visitCount[14][14] = 1;
        solution(14, 14, N, 0, visitCount, 1);
        System.out.print(sum);
    }

    static double sum = 0;

    //단순하지 않은 경우는 다 쳐낸다. -> 각 위치에 도착할 확률 = 모든 방향 확률의 곱하기
    static void solution(int x, int y, int N, int count, int[][] visitCount, double currentPercentage) {
        if (N == count) {
            sum += currentPercentage;
            return;
        }
        for (int i = 0; i < 4; i++) {
            int X = x + dxDy[i][0];
            int Y = y + dxDy[i][1];
            if (visitCount[Y][X] == 0) {
                visitCount[Y][X] = 1;
                if (percentage[i] != 0)
                    solution(X, Y, N, count + 1, visitCount, currentPercentage * percentage[i]);
                visitCount[Y][X] = 0;
            }
        }
    }
}
