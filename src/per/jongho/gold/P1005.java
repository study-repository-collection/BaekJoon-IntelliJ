package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1005 {
    static int[] time;
    static ArrayList<Integer>[] adjacentList;
    static int[] degree; //진입 차수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());//테스트 케이스의 개수
        while (T-- > 0) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(input.nextToken());//건물의 개수
            int K = Integer.parseInt(input.nextToken());//건물 규칙
            adjacentList = new ArrayList[N + 1];
            time = new int[N + 1];
            degree = new int[N + 1];
            StringTokenizer timeInfo = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                time[i] = Integer.parseInt(timeInfo.nextToken());
            }
            //건물 규칙
            while (K-- > 0) {
                StringTokenizer edge = new StringTokenizer(br.readLine());
                int v1 = Integer.parseInt(edge.nextToken());
                int v2 = Integer.parseInt(edge.nextToken());
                if (null == adjacentList[v1]) adjacentList[v1] = new ArrayList<>();
                adjacentList[v1].add(v2);
                degree[v2]++;
            }
            int W = Integer.parseInt(br.readLine()); //지어야 할 건물의 번호
            sb.append(topologicalSort(N, W)).append("\n");
        }
        System.out.print(sb);
    }


    /**
     * 위상정렬을 수행하는 함수
     *
     * @param N 건물의 개수
     * @param W 지어야할 건물
     */
    static long topologicalSort(int N, int W) {
        Queue<Integer> queue = new LinkedList<>();
        long[] answer = new long[N + 1];
        for (int i = 1; i <= N; i++) if (degree[i] == 0) queue.add(i);
        while (!queue.isEmpty()) {
            int index = queue.poll();
            if (null != adjacentList[index]) {
                for (int to : adjacentList[index]) {
                    degree[to]--;
                    answer[to] = Math.max(answer[to], answer[index] + time[index]);
                    if (degree[to] == 0) {
                        queue.add(to);
                    }
                }
            }
        }
        return answer[W] + time[W];
    }
}
