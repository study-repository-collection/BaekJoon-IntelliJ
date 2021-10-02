package per.jongho.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;

public class P11653 {

    static final int UNDEFINED = 0;
    static final int PRIME = 2;
    static final int NON_PRIME = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        int N = Integer.parseInt(br.readLine());
        if (N == 1) return;
        solution(N);
    }

    static void solution(int N) {
        int size = N;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 2; i <= Math.sqrt(size); i++) {
            while (N % i == 0) {
                stringBuilder.append(i).append("\n");
                N = N / i;
            }
        }
        System.out.print(stringBuilder);
        if (N != 1) {
            System.out.println(N);
        }
    }
}
