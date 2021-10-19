package per.jongho.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P16928 {
    static int[] warps = new int[101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken());//사다리의 수
        int M = Integer.parseInt(input.nextToken());//뱀의 수
        int sum = N + M;
        while (sum-- > 0) {
            StringTokenizer warpInfo = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(warpInfo.nextToken());
            int end = Integer.parseInt(warpInfo.nextToken());
            warps[start] = end;
        }
        System.out.println(bfs());
    }

    static int bfs() {
        int count = 0;
        boolean[] visited = new boolean[101];
        Queue<Integer> queue = new LinkedList<>();
        visited[1] = true;
        queue.add(1);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int temp = queue.poll();
                if (temp == 100) {
                    return count;
                }
                for (int j = 1; j <= 6; j++) {
                    int temp2 = temp + j;
                    if (temp2 > 100) break;
                    if (warps[temp2] != 0) {
                        queue.add(warps[temp2]);
                    } else if (!visited[temp2]) {
                        queue.add(temp2);
                        visited[temp2] = true;
                    }
                }
            }
            count++;
        }
        return count;
    }

}
