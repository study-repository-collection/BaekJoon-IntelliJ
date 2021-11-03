package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.System.in;


public class P2263 {
    static int[] inOrder = new int[100001];
    static int[] postOrder = new int[100001];
    static int[] preOrder = new int[100001];

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        int N = Integer.parseInt(br.readLine()); //노드의 개수

        StringTokenizer inOrders = new StringTokenizer(br.readLine());
        StringTokenizer postOrders = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            inOrder[i] = Integer.parseInt(inOrders.nextToken());
            postOrder[i] = Integer.parseInt(postOrders.nextToken());
        }
        solution(1, N, 1, N);
        System.out.println(sb);
    }

    static void solution(int inStart, int inEnd, int postStart, int postEnd) {
        if (inStart > inEnd || postStart > postEnd) return;
        sb.append(postOrder[postEnd]).append(" ");
        int mid = -1;
        for (int i = 1; i <= inEnd; i++) {
            if (inOrder[i] == postOrder[postEnd]) {
                mid = i;
                break;
            }
        }
        solution(inStart, mid - 1, postStart, postStart + mid - inStart - 1);
        solution(mid + 1, inEnd, postStart + mid - inStart, postEnd - 1);
    }
}
