package per.jongho.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1934 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer input = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(input.nextToken());
            int B = Integer.parseInt(input.nextToken());
            sb.append(choiSoGongBaeSu(A, B)).append("\n");
        }
        System.out.print(sb);
    }

    static int choiSoGongBaeSu(int A, int B) {
        int GCD = euclid(A, B);
        return (A / GCD) * B;
    }

    static int euclid(int a, int b) {
        int r = a % b;
        if (r == 0) return b;
        else return euclid(b, r);
    }
}
