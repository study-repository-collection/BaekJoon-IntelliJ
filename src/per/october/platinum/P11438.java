package per.october.platinum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P11438 {

    static int[] levels;
    static int[][] rootNodeCollection;
    static ArrayList<Integer>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) tree[i] = new ArrayList<>();
        levels = new int[N + 1];
        rootNodeCollection = new int[N + 1][21];


        for (int i = 0; i < N - 1; i++) {
            StringTokenizer nodeInfo = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(nodeInfo.nextToken());
            int b = Integer.parseInt(nodeInfo.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }
        decideLevel(1, 1, new ArrayList<>());
        int M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(input.nextToken());
            int b = Integer.parseInt(input.nextToken());
            sb.append(solution(a, b)).append("\n");
        }
        System.out.println(sb);
    }


    static int solution(int a, int b) {
        if (levels[a] < levels[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        while (levels[a] != levels[b]) {
            int length = levels[a] - levels[b];
            int i = 1;
            int count = 0;
            while (i << 1 <= length && count < 20) {
                i = i << 1;
                count++;
            }
            a = rootNodeCollection[a][count];
        }

        while (a != b) {
            int count = 0;
            for (; count < 20; count++) {
                if (rootNodeCollection[a][count] == 0 || (rootNodeCollection[a][count] == rootNodeCollection[b][count])) {
                    count = Math.max(0, count - 1);
                    break;
                }
            }
            a = rootNodeCollection[a][count];
            b = rootNodeCollection[b][count];
        }
        return a;
    }

    static void decideLevel(int level, int to, ArrayList<Integer> rootNode) {
        levels[to] = level;
        int size = rootNode.size();
        int count = 0;
        for (int i = 1; i <= size && count < 20; i = i << 1, count++) {
            rootNodeCollection[to][count] = rootNode.get(size - i);
        }

        rootNode.add(to);
        for (int goTo : tree[to]) {
            if (goTo != rootNodeCollection[to][0]) {

                decideLevel(level + 1, goTo, rootNode);
            }
        }
        rootNode.remove(rootNode.size() - 1);
    }
}
