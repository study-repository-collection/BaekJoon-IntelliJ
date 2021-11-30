package per.november.platinum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P10999 {
    static long[] arr;
    static long[] tree;
    static long[] lazyArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken()); //수의 개수
        int M = Integer.parseInt(input.nextToken()); //수의 변경이 일어나는 횟수
        int K = Integer.parseInt(input.nextToken()); //구간의 합을 구하는 횟수
        arr = new long[N + 1];
        tree = new long[N * 4];
        lazyArr = new long[N * 4];
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
        int number = M + K;
        initSegmentTree(1, N, 1);
        while (number-- > 0) {
            StringTokenizer operationInfo = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(operationInfo.nextToken());
            int b;
            int c;
            long d;
            b = Integer.parseInt(operationInfo.nextToken());
            c = Integer.parseInt(operationInfo.nextToken());
            if (a == 1) {
                d = Long.parseLong(operationInfo.nextToken());
                modifySum(1, N, 1, b, c, d);
            } else {
                sb.append(getSum(1, N, 1, b, c)).append("\n");
            }
        }
        System.out.print(sb);
    }

    static long initSegmentTree(int start, int end, int node) {
        if (start == end) return tree[node] = arr[start];
        int mid = (start + end) / 2;
        return tree[node] = initSegmentTree(start, mid, node * 2) + initSegmentTree(mid + 1, end, node * 2 + 1);
    }

    static void updateLazy(int node, int start, int end) {
        if (lazyArr[node] != 0) {
            tree[node] += (end - start + 1) * lazyArr[node];
            if (start != end) {
                lazyArr[node * 2] += lazyArr[node];
                lazyArr[node * 2 + 1] += lazyArr[node];
            }
            lazyArr[node] = 0;
        }
    }


    static long getSum(int start, int end, int node, int b, int c) {
        updateLazy(node, start, end);
        if (start > c || end < b) return 0;
        if (start >= b && end <= c) return tree[node];
        int mid = (start + end) / 2;
        return getSum(start, mid, node * 2, b, c) + getSum(mid + 1, end, node * 2 + 1, b, c);
    }

    //수 변경 연산이 최대 N = 4000000까지 소모할 수 있따  = 4000000*10000의 시간이 소요될 수 있음 (최대)
    static void modifySum(int start, int end, int node, int b, int c, long sum) {
        //아직 변경을 적용안한 노드일 경우, 변경을 적용하고 자식 노드들에게 물려줌
        updateLazy(node, start, end);
        //탐색 구간이 범위를 완전히 벗어난 경우 탈출한다.
        if (start > c || end < b) return;
        //만약 현재 탐색 구간이 갱신 구간에 완전히 포함될 경우
        if (start >= b && end <= c) {
            tree[node] += sum * (end - start + 1);
            if (start != end) {
                lazyArr[node * 2] += sum;
                lazyArr[node * 2 + 1] += sum;
            }
        }
        //현재 탐색 구간이 완전히 포함되지 않았으면, 반씩 나눠서 탐색 진행
        else {
            int mid = (start + end) / 2;
            modifySum(start, mid, node * 2, b, c, sum);
            modifySum(mid + 1, end, node * 2 + 1, b, c, sum);
            //현재 노드는 자식 노드의 변경을 적용해야한다.
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }
    }
}
