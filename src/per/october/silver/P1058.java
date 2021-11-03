package per.october.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.System.in;

public class P1058 {
    static int[][] friendMap;
    static int[] persons;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine()); //사람의 수
        friendMap = new int[N + 1][N + 1];
        persons = new int[N + 1];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                friendMap[i + 1][j + 1] = input.charAt(j) == 'Y' ? 1 : 0;
            }
        }
        solution(N);
        System.out.println(Arrays.stream(persons).max().getAsInt());
    }

    static void solution(int N) {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                if (k == i) continue;
                for (int j = 1; j <= N; j++) {
                    if (friendMap[k][i] == 1) {
                        persons[k]++;
                        break;
                    } else if (friendMap[k][j] == 1 && friendMap[j][i] == 1) {
                        persons[k]++;
                        break;
                    }
                }
            }
        }
    }
}
