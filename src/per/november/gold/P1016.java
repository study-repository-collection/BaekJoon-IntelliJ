package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1016 {
    static boolean[] jegopnono;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer input = new StringTokenizer(br.readLine());
        long min = Long.parseLong(input.nextToken());
        long max = Long.parseLong(input.nextToken());
        long size = max - min;
        jegopnono = new boolean[(int) size + 2];
        //index = 0 = min
        Arrays.fill(jegopnono, true);
        //1보다 큰 제곱수로 나누어 떨어지지 않을 때, 그 수를 제곱 ㄴㄴ 수라고 한다. 제곱수는 정수의 제곱이다.
        //min보다 크거나 같고 max 보다 작거나 같은 제곱 ㄴㄴ 수가 몇 개인지 출력하라
        //1 ~ 10 = 7  1,2,3,5,6,7,10 ; min과 max 는 최대 100만 차이다 !
        //제곱 수 4,9,16,25.....
        for (long i = 2; i * i <= max; i++) {
            long number = i * i;
            long start = min / number;
            if ((number * start) - min < 0) {
                start++;
            }
            for (long j = start; j * number <= max; j++) {
                int index = (int) (j * number - min);
                jegopnono[index] = false;
            }


        }
        int count = 0;
        for (long i = 0; i + min <= max; i++) {
            if (jegopnono[(int) i]) count++;
        }
        System.out.println(count);
    }
}