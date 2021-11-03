package per.october.gold;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P2357 {
    static int[] arr;
    static Point[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //N개의 정수
        int M = Integer.parseInt(st.nextToken()); //M개의 쌍
        tree = new Point[N * 4];
        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        initSegmentTree(1, N, 1);
        while (M-- > 0) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(input.nextToken());
            int b = Integer.parseInt(input.nextToken());
            Point point = getAnswer(1, N, 1, a, b);
            sb.append(point.x).append(" ").append(point.y).append("\n");
        }
        System.out.print(sb);
    }

    static Point initSegmentTree(int start, int end, int node) {
        if (start == end) {
            return tree[node] = new Point(arr[start], arr[start]);
        }
        int mid = (start + end) / 2;

        Point pointLeft = initSegmentTree(start, mid, node * 2);
        Point pointRight = initSegmentTree(mid + 1, end, node * 2 + 1);
        return tree[node] = new Point(Math.min(pointLeft.x, pointRight.x), Math.max(pointLeft.y, pointRight.y));
    }

    static Point getAnswer(int start, int end, int node, int left, int right) {
        if (start > right || end < left) return null;

        if (start >= left && end <= right) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        Point pointLeft = getAnswer(start, mid, node * 2, left, right);
        Point pointRight = getAnswer(mid + 1, end, node * 2 + 1, left, right);
        if (null == pointLeft && null == pointRight) {
            return null;
        } else if (null == pointLeft) {
            return pointRight;
        } else if (null == pointRight) {
            return pointLeft;
        } else {
            return new Point(Math.min(pointLeft.x, pointRight.x), Math.max(pointLeft.y, pointRight.y));
        }
    }
}
