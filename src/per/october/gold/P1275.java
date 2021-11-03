package per.october.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1275 {
    static long[] arr;
    static long[] segmentTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken());//수의 개수
        int Q = Integer.parseInt(input.nextToken());//턴의 개수
        segmentTree = new long[N * 4];
        arr = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        initSegmentTree(0, N - 1, 1);
        while (Q-- > 0) {
            StringTokenizer turn = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(turn.nextToken());
            int y = Integer.parseInt(turn.nextToken());
            int a = Integer.parseInt(turn.nextToken());
            int b = Integer.parseInt(turn.nextToken());
            if (x > y) {
                sb.append(getSum(0, N - 1, 1, y - 1, x - 1)).append("\n");
            } else {
                sb.append(getSum(0, N - 1, 1, x - 1, y - 1)).append("\n");
            }
            update(0, N - 1, 1, a-1, b - arr[a - 1]);
            arr[a - 1] = b;
        }
        System.out.print(sb);
    }

    static long initSegmentTree(int start, int end, int node) {
        if (start == end) return segmentTree[node] = arr[start];

        int mid = (start + end) / 2;

        return segmentTree[node] = initSegmentTree(start, mid, node * 2) + initSegmentTree(mid + 1, end, node * 2 + 1);
    }

    static long getSum(int start, int end, int node, int left, int right) {
        if (start > right || end < left) return 0;
        if (start >= left && end <= right) return segmentTree[node];
        int mid = (start + end) / 2;
        return getSum(start, mid, node * 2, left, right) + getSum(mid + 1, end, node * 2 + 1, left, right);
    }

    static void update(int start, int end, int node, int index, long dif) {
        if (start > index || index > end) return;
        segmentTree[node] += dif;
        if (start == end) return;
        int mid = (start + end) / 2;
        update(start, mid, node * 2, index, dif);
        update(mid + 1, end, node * 2 + 1, index, dif);
    }
}
