package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P14719 {
    static int[][] arr;
    static final int WALL = 1;
    static final int EMPTY = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken()); //2차원 세계의 높이
        int W = Integer.parseInt(st.nextToken()); //2차원 세계의 길이
        arr = new int[H + 1][W + 1];
        StringTokenizer heightInfo = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            int height = Integer.parseInt(heightInfo.nextToken());
            for (int j = 0; j <= height; j++) {
                arr[j][i] = WALL;
            }
        }

        System.out.println(solution(H, W));
    }

    static int solution(int H, int W) {
        int total = 0;

        for (int i = 1; i <= H; i++) {
            total += getWaterCount(H, W, i);
        }

        return total;
    }

    static int getWaterCount(int H, int W, int level) {
        int sum = 0;
        int currentLeft = 0;
        int currentSum = 0;
        for (int i = 0; i < W; i++) {
            if (arr[level][i] == EMPTY) {
                if (currentLeft != 0) {
                    currentSum++;
                }
            } else if (arr[level][i] == WALL) {
                if (currentLeft == 0) {
                    currentLeft = 1;
                } else {
                    sum += currentSum;
                    currentSum = 0;
                }
            }
        }
        return sum;
    }
}
