package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P2252 {
    static int[] degree;

    static ArrayList<Integer>[] adjacentList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        adjacentList = new ArrayList[N + 1];
        degree = new int[N + 1];

        while (M-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (null == adjacentList[a]) adjacentList[a] = new ArrayList<>();
            adjacentList[a].add(b);
            degree[b]++;
        }
        topologicalSort(N);
    }


    static void topologicalSort(int N) {
        Queue<Integer> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (degree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int index = queue.poll();
            sb.append(index).append(" ");
            if (null != adjacentList[index]) {
                for (int to : adjacentList[index]) {
                    degree[to]--;
                    if (degree[to] == 0) {
                        queue.add(to);
                    }
                }
            }
        }
        System.out.print(sb);
    }
}
