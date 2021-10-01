package per.jongho.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1051 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] square = new int[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                square[i][j] = input.charAt(j) - '0';
            }
        }
        System.out.println(solution(square));
    }

    static int solution(int[][] square) {
        int max = -1; //정사각형 최대 크기
        int maxSquareLength = Math.min(square.length, square[0].length); //정사각형 한 변의 최대 길이
        for (int i = 1; i <= maxSquareLength; i++) {
            for (int j = 0; j < square.length; j++) {
                for (int k = 0; k < square[0].length; k++) {
                    if (isSatisfy(square, j, k, i)) {
                        max = i * i;
                    }
                }
            }
        }
        return max;
    }

    static boolean isSatisfy(int[][] square, int startY, int startX, int size) {
        try {
            int value = square[startY][startX];
            return ((value == square[startY + size - 1][startX]) && (value == square[startY][startX + size - 1]) && (value == square[startY + size - 1][startX + size - 1]));
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }


}
