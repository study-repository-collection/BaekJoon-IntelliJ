package per.october.bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

import static java.lang.System.in;

public class P1009 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine()); //테스트 케이스의 개수
        while (T-- > 0) {
            String[] input = br.readLine().split(" ");
            int result = solution(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
            System.out.println(result == 0 ? 10 : result);
        }
    }

    public static int solution(int a, int b) {
        BigInteger bigA = BigInteger.valueOf(a);
        BigInteger result = bigA.modPow(BigInteger.valueOf(b), BigInteger.valueOf(10));
        return result.intValue();
    }
}
