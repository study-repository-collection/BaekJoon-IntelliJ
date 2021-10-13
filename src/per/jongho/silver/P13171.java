package per.jongho.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;

public class P13171 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        long A = Long.parseLong(br.readLine()) % MOD;
        long X = Long.parseLong(br.readLine());
        System.out.println(solution(A, X));
    }

    static long solution(long A, long X) {
        if (X == 1) return A;

        long ret = solution(A, X / 2) % MOD;


        long result = (ret * ret) % MOD;

        //X가 홀수일 때
        if ((X & 1) == 1) {
            result = (result * A) % MOD;
        }
        return result;
    }


    static final long MOD = 1000000007L;
}
