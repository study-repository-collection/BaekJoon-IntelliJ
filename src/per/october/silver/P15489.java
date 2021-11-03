package per.october.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P15489 {
    static int[][] triangle;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken()); //시작점 i 좌표
        int C = Integer.parseInt(st.nextToken()); //시작점 j 좌표
        int W = Integer.parseInt(st.nextToken()); //한 변의 길이

        triangle = new int[R + W + 1][R + W + 1];
        makeTriangle(R + W);
        System.out.println(solution(R, C, W));
    }

    static void makeTriangle(int size) {
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= i; j++) {
                if (j == 1 || j == i) {
                    triangle[i][j] = 1;
                } else {
                    triangle[i][j] = triangle[i - 1][j - 1] + triangle[i - 1][j];
                }
            }
        }
    }

    static int solution(int R, int C, int W) {
        int sum = 0;
        for (int i = R; i < R + W; i++) {
            for (int j = C; j < C + (i-R) + 1; j++) {
                sum += triangle[i][j];
            }
        }
        return sum;
    }

}
