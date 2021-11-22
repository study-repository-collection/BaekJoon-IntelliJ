package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P15684 {
    static int[][] ladder;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken()); //세로선의 개수
        int M = Integer.parseInt(input.nextToken()); //가로선의 개수
        int H = Integer.parseInt(input.nextToken()); //가로선을 넣을 수 있는 위치의 개수
        ladder = new int[H + 2][N + 1];
        while (M-- > 0) {
            StringTokenizer lineInfo = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(lineInfo.nextToken());
            int b = Integer.parseInt(lineInfo.nextToken());
            //b와 b+1번 세로선을 a번 점선에서 연결
            ladder[a][b] = b + 1;
            ladder[a][b + 1] = b;
        }
        System.out.println(solution(N, H));
    }

    static int solution(int N, int H) {
        for (int i = 0; i <= 3; i++) {
            success = false;
            installLine(N, H, i, 0);
            if (success) {
                return i;
            }
        }
        return -1;
    }


    static boolean success = false;

    static void installLine(int N, int H, int count, int index) {
        if (success) return;
        if (count == index) {
            success = isSuccess(N, H);
            return;
        }
        for (int i = 1; i <= H; i++) {
            for (int j = 2; j <= N; j++) {
                if (ladder[i][j] == 0 && ladder[i][j - 1] == 0) {
                    ladder[i][j] = j - 1;
                    ladder[i][j - 1] = j;
                    installLine(N, H, count, index + 1);
                    ladder[i][j] = 0;
                    ladder[i][j - 1] = 0;
                }
            }
        }
    }

    static boolean isSuccess(int N, int H) {
        for (int i = 1; i <= N; i++) {
            int currentPosition = i;
            for (int j = 1; j <= H; j++) {
                if (ladder[j][currentPosition] > 0) {
                    currentPosition = ladder[j][currentPosition];
                }
            }
            if (currentPosition != i) {
                return false;
            }
        }
        return true;
    }
}
