package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P11505 {

    static final int MODIFY = 1;
    static final int GET = 2;
    static final long MOD = 1000000007;
    static long[] tree;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken()); //수의 개수
        int M = Integer.parseInt(input.nextToken()); //변경이 일어나는 횟수
        int K = Integer.parseInt(input.nextToken()); //구간의 곱을 구하는 횟수


        arr = new int[N + 1];
        tree = new long[N * 4];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        initTree(1, N, 1);
        for (int i = 0; i < M + K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); //명령
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            switch (a) {
                case MODIFY:
                    modify(1, N, 1, b, c);
                    arr[b] = c;
                    break;
                case GET:
                    sb.append(getSum(1, N, 1, b, c)).append("\n");
                    break;
            }
        }
        System.out.println(sb);
    }

    static long initTree(int start, int end, int node) {
        if (start == end) return tree[node] = arr[start];
        int mid = (start + end) / 2;
        return tree[node] = ((initTree(start, mid, node * 2) % MOD) * (initTree(mid + 1, end, node * 2 + 1) % MOD)) % MOD;
    }

    static long getSum(int start, int end, int node, int left, int right) {
        if (start > right || end < left) return 1;
        if (start >= left && end <= right) return tree[node];
        int mid = (start + end) / 2;
        return ((getSum(start, mid, node * 2, left, right) % MOD) * (getSum(mid + 1, end, node * 2 + 1, left, right) % MOD)) % MOD;
    }

    static long modify(int start, int end, int node, int index, long toChange) {
        if (index < start || index > end) return tree[node] % MOD;
        if (start == end) return tree[node] = toChange % MOD;
        int mid = (start + end) / 2;
        return tree[node] = ((modify(start, mid, node * 2, index, toChange) % MOD) * (modify(mid + 1, end, node * 2 + 1, index, toChange) % MOD)) % MOD;

    }
}
