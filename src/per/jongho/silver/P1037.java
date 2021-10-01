package per.jongho.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.System.in;

public class P1037 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine()); //진짜 약수의 개수
        int[] realMeasure = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(solution(realMeasure));
    }

    public static int solution(int[] realMeasure) {
        return Arrays.stream(realMeasure).max().getAsInt() * Arrays.stream(realMeasure).min().getAsInt();
    }
}
