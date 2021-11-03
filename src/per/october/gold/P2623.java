package per.october.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P2623 {

    static int[] degree;
    static ArrayList<Integer>[] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken()); //가수의 수
        int M = Integer.parseInt(input.nextToken()); //보조 PD 의 수

        edges = new ArrayList[N + 1];
        degree = new int[N + 1];
        while (M-- > 0) {
            StringTokenizer pdInfo = new StringTokenizer(br.readLine());
            int number = Integer.parseInt(pdInfo.nextToken());
            int a = -1;
            for (int i = 0; i < number; i++) {
                int b = Integer.parseInt(pdInfo.nextToken());
                if (a != -1) {
                    if (null == edges[a]) edges[a] = new ArrayList<>();
                    edges[a].add(b);
                    degree[b]++;
                }
                a = b;
            }
        }
        topologicalSort(N);
    }

    static void topologicalSort(int N) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= N; i++) if (degree[i] == 0) queue.add(i);

        while (!queue.isEmpty()) {
            int index = queue.poll();
            sb.append(index).append("\n");
            count++;
            if (null != edges[index]) {
                for (int to : edges[index]) {
                    degree[to]--;
                    if (degree[to] == 0)
                        queue.add(to);
                }
            }
        }

        if (count != N) {
            System.out.println(0);
        } else {
            System.out.println(sb);
        }
    }
}
