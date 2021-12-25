package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P3980 {
    static int[][] ability = new int[11][11];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int C = Integer.parseInt(br.readLine());
        while (C-- > 0) {
            max = -1;
            for (int i = 0; i < 11; i++) {
                StringTokenizer input = new StringTokenizer(br.readLine());
                for (int j = 0; j < 11; j++) {
                    ability[i][j] = Integer.parseInt(input.nextToken());
                }
            }
            solution(0, 0, new boolean[11]);
            sb.append(max).append("\n");
        }
        System.out.println(sb.toString());
    }

    static void solution(int index, int score, boolean[] use) {
        if (index == 11) {
            max = Math.max(max, score);
            return;
        }
        for (int i = 0; i < 11; i++) {
            if (ability[index][i] != 0 && !use[i]) {
                use[i] = true;
                solution(index + 1, score + ability[index][i], use);
                use[i] = false;
            }
        }
    }

    static int max = -1;
}
