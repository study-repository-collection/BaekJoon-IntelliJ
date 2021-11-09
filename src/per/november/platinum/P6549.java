package per.november.platinum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P6549 {

    static int[] sequence;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            if (input.countTokens() == 1) {
                break;
            }
            int N = Integer.parseInt(input.nextToken());
            sequence = new int[N];
            for (int i = 0; i < N; i++) {
                sequence[i] = Integer.parseInt(input.nextToken());
            }
            sb.append(getMaxWidth(0, N - 1)).append("\n");
        }

        System.out.println(sb);
    }


    static long getMaxWidth(int start, int end) {
        if (start == end) return sequence[start];
        int mid = (start + end) / 2;
        long max = Math.max(getMaxWidth(start, mid), getMaxWidth(mid + 1, end));
        max = Math.max(getMidWidth(start, end, (start + end) / 2), max);
        return max;
    }

    static long getMidWidth(int start, int end, int mid) {
        int toLeft = mid;
        int toRight = mid;
        long height = sequence[mid];
        long maxArea = height;

        while (start < toLeft && end > toRight) {
            if (sequence[toLeft - 1] < sequence[toRight + 1]) {
                toRight++;
                height = Math.min(height, sequence[toRight]);
            } else {
                toLeft--;
                height = Math.min(height, sequence[toLeft]);
            }

            maxArea = Math.max(maxArea, height * (toRight - toLeft + 1));
        }

        while (toRight < end) {
            height = Math.min(height, sequence[++toRight]);
            maxArea = Math.max(maxArea, height * (toRight - toLeft + 1));
        }

        while (toLeft > start) {
            height = Math.min(height, sequence[--toLeft]);
            maxArea = Math.max(maxArea, height * (toRight - toLeft + 1));

        }
        return maxArea;
    }
}
