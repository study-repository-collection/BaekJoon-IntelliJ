package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P2225 {
    static final int MOD = 1000000000;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken());
        //0부터 N까지의 정수 K 개를 더해서 그 합이 N 이 되는 경우의 수
        int K = Integer.parseInt(input.nextToken());
        arr = new int[N + 1][K + 1];
        makeArr(N, K);
    }

    static void makeArr(int N, int K) {
        for (int i = 1; i <= N; i++) {
            arr[i][1] = 1;
        }
        //arr[i][j] = j개의 숫자를 써 i를 만들 수 있는 경우의 수로
        //arr[i][j] += arr[i-1...i][j-1]
        for (int i = 2; i <= K; i++) {
            for (int j = 1; j <= N; j++) {
                arr[j][i] += arr[j][i - 1] + 1;
                for (int k = 1; k <= j; k++) {
                    arr[j][i] = (arr[j][i] + arr[j - k][i - 1] % MOD) % MOD;
                }
            }
        }
        System.out.println(arr[N][K]);
    }
}
