package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;

public class P2749 {

    static final long mod = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        long N = Long.parseLong(br.readLine());
        if (N == 0) {
            System.out.println(0);
            return;
        } else if (N == 1) {
            System.out.println(1);
            return;
        }
        System.out.println(solution(N));
    }

    static long solution(long number) {
        long[][] ret = pow(matrix, number - 1);
        return ret[0][0] % mod;
    }

    static long[][] pow(long[][] origin, long number) {
        if (number == 1) return origin;

        long ret[][] = pow(origin, number / 2);
        ret = multiplyMatrix(ret, ret);


        if ((number & 1) == 1) {
            ret = multiplyMatrix(ret, origin);
        }
        return ret;
    }

    static long[][] matrix = {{1, 1}, {1, 0}};

    static long[][] multiplyMatrix(long[][] A, long[][] B) {
        long[][] ret = new long[2][2];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                int sum = 0;
                for (int k = 0; k < 2; k++) {
                    sum += (A[i][k] * B[k][j]) % mod;
                }
                ret[i][j] = sum % mod;
            }
        }
        return ret;
    }

}
