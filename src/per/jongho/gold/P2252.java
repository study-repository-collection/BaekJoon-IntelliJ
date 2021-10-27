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
        //진입 차수가 0인 모든 정점을 큐에 삽입한다.
        for (int i = 1; i <= N; i++) {
            if (degree[i] == 0) {
                queue.add(i);
            }
        }
        //큐가 빌 때까지
        while (!queue.isEmpty()) {
            int index = queue.poll();
            //위상 순서를 스트링 빌더에 추가한다.
            sb.append(index).append(" ");
            //index번 정점에 간선이 존재할 경우
            if (null != adjacentList[index]) {
                for (int to : adjacentList[index]) {
                    //간선의 도착점의 차수를 줄인다.
                    degree[to]--;
                    //간선 도착점의 차수가 0이라면, 큐에 삽입한다.
                    if (degree[to] == 0) {
                        queue.add(to);
                    }
                }
            }
        }
        System.out.print(sb);
    }
}
