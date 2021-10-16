package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P13172 {
    static final long MOD = 1000000007;
    static long N = 1;
    static long S = 0;

    //Q = a * b^-1 mod X
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        int M = Integer.parseInt(br.readLine());//주사위의 개수
        while (M-- > 0) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            long tempN = Long.parseLong(input.nextToken()) % MOD; //주사위의 면의 개수
            long tempS = Long.parseLong(input.nextToken()) % MOD; //주사위에 적힌 수의 합
            sum(tempN, tempS);
        }
        if (S % N == 0) {
            System.out.println(S % N);
        } else {
            long GCD = euclid(Math.max(S, N), Math.min(S, N));
            N /= GCD;
            S /= GCD;
            System.out.println(getNumber(S, getB1(N, MOD - 2)));
        }
    }

    static void sum(long tempN, long tempS) {
        S *= tempN;
        tempS *= N;
        N = N * tempN;
        S += tempS;
        S = S % MOD;
        N = N % MOD;
    }

    static long getNumber(long a, long b) {
        return (a * b) % MOD;
    }

    static long getB1(long a, long b) {
        if (b == 1) return a;

        long ret = getB1(a, b / 2);

        long answer = (ret * ret) % MOD;

        if ((b & 1) == 1) {
            answer = (answer * a) % MOD;
        }
        return answer;
    }

    static long euclid(long a, long b) {
        long r = a % b;
        if (r == 0) {
            return b;
        } else {
            return euclid(b, r);
        }
    }
}
