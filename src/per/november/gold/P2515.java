package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P2515 {

    static Picture[] pictures;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //그림의 개수
        int S = Integer.parseInt(st.nextToken()); //보이는 부분의 세로 길이가 S 이상인 그림만사게됨
        pictures = new Picture[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer pictureInfo = new StringTokenizer(br.readLine());
            int H = Integer.parseInt(pictureInfo.nextToken());
            int C = Integer.parseInt(pictureInfo.nextToken());
            pictures[i] = new Picture(H, C);
        }
        Arrays.sort(pictures, Comparator.comparingInt(o -> o.height));
        System.out.println(solution(N, S));

    }

    static int solution(int N, int S) {
        int[] dp = new int[N];
        dp[0] = pictures[0].value;
        for (int i = 1; i < N; i++) {
            int index = binarySearch(0, i - 1, pictures[i].height - S);
            if (index == -1) {
                dp[i] = Math.max(pictures[i].value, dp[i - 1]);
            } else {
                dp[i] = Math.max(dp[index] + pictures[i].value, dp[i - 1]);
            }
        }
        return dp[N - 1];
    }

    static int binarySearch(int start, int end, int height) {
        int ret = -1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (pictures[mid].height > height) {
                end = mid - 1;
            } else {
                ret = mid;
                start = mid + 1;
            }
        }
        return ret;
    }

    static class Picture {
        final int height;
        final int value;

        public Picture(int height, int value) {
            this.height = height;
            this.value = value;
        }

    }
}
