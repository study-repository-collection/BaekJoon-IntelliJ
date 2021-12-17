package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

public class P16964 {
    static ArrayList<Integer>[] adjacentList;
    static int[] sequence;

    static int[] parent;
    static ArrayList<Integer> leapNodes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        int N = Integer.parseInt(br.readLine());
        adjacentList = new ArrayList[N + 1];
        parent = new int[N + 1];
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(input.nextToken());
            int b = Integer.parseInt(input.nextToken());
            if (null == adjacentList[a]) adjacentList[a] = new ArrayList<>();
            if (null == adjacentList[b]) adjacentList[b] = new ArrayList<>();
            adjacentList[a].add(b);
            adjacentList[b].add(a);
        }
        sequence = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        dfs(1, new boolean[N + 1], 0);
        leapNodes.sort(null);
        System.out.println(solution(N) ? 1 : 0);

    }

    static boolean solution(int N) {
        boolean[] use = new boolean[N + 1];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            if (Collections.binarySearch(leapNodes, sequence[i]) >= 0) {
                int temp = sequence[i];
                if (stack.isEmpty()) {
                    if (!use[parent[temp]])
                        return false;
                } else {
                    while (!stack.isEmpty()) {
                        if (stack.pop() == parent[temp]) {
                            use[parent[temp]] = true;
                            temp = parent[temp];
                        } else {
                            return false;
                        }
                    }
                }
            } else {
                stack.add(sequence[i]);
            }
        }
        return true;
    }

    static void toParent() {

    }

    static void dfs(int x, boolean[] visited, int before) {
        visited[x] = true;
        parent[x] = before;
        boolean isLeap = true;
        for (int nextTo : adjacentList[x]) {
            if (!visited[nextTo]) {
                isLeap = false;
                dfs(nextTo, visited, x);
            }
        }
        if (isLeap) {
            leapNodes.add(x);
        }
    }
}
