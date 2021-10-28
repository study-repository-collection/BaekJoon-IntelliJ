package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P2042 {
    static long[] sequence;
    static long[] tree;
    static final int UPDATE = 1;
    static final int GET_SUM = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken()); //수의 개수
        int M = Integer.parseInt(input.nextToken()); //수의 변경이 일어나는 횟수
        int K = Integer.parseInt(input.nextToken()); //구간의 합을 구하는 횟수


        sequence = new long[N];
        tree = new long[N * 4];
        for (int i = 0; i < N; i++) sequence[i] = Integer.parseInt(br.readLine());
        initSegmentTree(0, N - 1, 1);
        int number = M + K;
        while (number-- > 0) {
            StringTokenizer operation = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(operation.nextToken()); //작업
            int b = Integer.parseInt(operation.nextToken());
            long c = Long.parseLong(operation.nextToken());
            switch (a) {
                case UPDATE:
                    update(0, N - 1, 1, b - 1, c - sequence[b - 1]);
                    sequence[b - 1] = c;
                    break;
                case GET_SUM:
                    sb.append(getSum(0, N - 1, 1, b - 1, (int) (c - 1))).append("\n");
                    break;
            }
        }
        System.out.print(sb);
    }

    static long initSegmentTree(int start, int end, int node) {
        if (start == end) return tree[node] = sequence[start];
        int mid = (start + end) / 2;
        return tree[node] = initSegmentTree(start, mid, node * 2) + initSegmentTree(mid + 1, end, node * 2 + 1);
    }

    static long getSum(int start, int end, int node, int left, int right) {
        //범위를 벗어나면 종료한다.
        if (start > right || end < left) return 0;
        if (start >= left && end <= right) return tree[node];
        int mid = (start + end) / 2;
        return getSum(start, mid, node * 2, left, right) + getSum(mid + 1, end, node * 2 + 1, left, right);
    }

    static void update(int start, int end, int node, int index, long dif) {
        //범위를 벗어나면 종료한다.
        if (index < start || index > end) return;

        tree[node] += dif;
        if (start == end) return;
        int mid = (start + end) / 2;
        update(start, mid, node * 2, index, dif);
        update(mid + 1, end, node * 2 + 1, index, dif);
    }
}
