package per.jongho.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1476 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer input = new StringTokenizer(br.readLine());

        int E = Integer.parseInt(input.nextToken()); //1<= <=15
        int S = Integer.parseInt(input.nextToken()); //1<= <=28
        int M = Integer.parseInt(input.nextToken()); //1<= <=19
        if (E == 1 && S == 1 && M == 1) System.out.println(1);
        else System.out.println(solution(E, S, M));
    }

    static int solution(int E, int S, int M) {
        int num = 1;
        int tempE = 1;
        int tempS = 1;
        int tempM = 1;
        do {
            num++;
            tempE = (tempE + 1) % 16 == 0 ? 1 : tempE + 1;
            tempS = (tempS + 1) % 29 == 0 ? 1 : tempS + 1;
            tempM = (tempM + 1) % 20 == 0 ? 1 : tempM + 1;
        } while (tempE != E || tempS != S || tempM != M);
        return num;
    }
}
