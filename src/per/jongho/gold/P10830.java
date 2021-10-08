package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P10830 {
    static int[][] matrix;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken()); //행렬의 크기
        long B = Long.parseLong(input.nextToken()); //행렬의 B 제곱
        matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer matrixInput = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(matrixInput.nextToken())%MOD;
            }
        }
        int[][] answer = solution(matrix, B, N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(answer[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static int[][] solution(int[][] origin, long B, int N) {
        if (B == 1) return origin;

        int[][] ret = solution(origin, B / 2, N);

        ret = multiplyMatrix(ret, ret, N);

        if ((B & 1) == 1) {
            ret = multiplyMatrix(ret, origin, N);
        }
        return ret;
    }

    static final int MOD = 1000;

    static int[][] multiplyMatrix(int[][] A, int[][] B, int N) {
        int[][] ret = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int sum = 0;
                for (int k = 0; k < N; k++) {
                    sum += (A[i][k] * B[k][j]) % MOD;
                }
                ret[i][j] = sum % MOD;
            }
        }
        return ret;
    }
}
