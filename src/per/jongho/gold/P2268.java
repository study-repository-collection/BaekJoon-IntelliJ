package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P2268 {

    static final int SUM = 0;
    static final int MODIFY = 1;

    static long[] arr;
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        arr = new long[N + 1];
        tree = new long[N * 4];

        while (M-- > 0) {
            StringTokenizer operation = new StringTokenizer(br.readLine());
            int oper = Integer.parseInt(operation.nextToken());
            int a = Integer.parseInt(operation.nextToken());
            int b = Integer.parseInt(operation.nextToken());

            switch (oper) {
                case SUM:
                    if (a > b) {
                        sb.append(getSum(1, N, 1, b, a)).append("\n");
                    } else {
                        sb.append(getSum(1, N, 1, a, b)).append("\n");
                    }
                    break;
                case MODIFY:
                    modify(1, N, 1, a, b - arr[a]);
                    arr[a] = b;
                    break;
            }
        }
        System.out.print(sb);
    }

    public static void modify(int start, int end, int node, int index, long dif) {
        if (index < start || index > end) return;
        tree[node] += dif;
        if (start == end) return;
        int mid = (start + end) / 2;
        modify(start, mid, node * 2, index, dif);
        modify(mid + 1, end, node * 2 + 1, index, dif);
    }

    public static long getSum(int start, int end, int node, int left, int right) {
        if (start > right || end < left) return 0;
        if (start >= left && end <= right) return tree[node];
        int mid = (start + end) / 2;
        return getSum(start, mid, node * 2, left, right) + getSum(mid + 1, end, node * 2 + 1, left, right);
    }
}
