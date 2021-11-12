package per.november.gold;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

public class P1939 {
    static ArrayList<Edge>[] adjacentList;


    static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken());//섬의 개수
        int M = Integer.parseInt(input.nextToken());//다리에 대한 정보
        adjacentList = new ArrayList[N + 1];
        while (M-- > 0) {
            StringTokenizer edgeInfo = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(edgeInfo.nextToken());
            int b = Integer.parseInt(edgeInfo.nextToken());
            int c = Integer.parseInt(edgeInfo.nextToken());
            if (null == adjacentList[a]) adjacentList[a] = new ArrayList<>();
            if (null == adjacentList[b]) adjacentList[b] = new ArrayList<>();
            adjacentList[a].add(new Edge(b, c));
            adjacentList[b].add(new Edge(a, c));
        }
        IntegerTokenizer st = new IntegerTokenizer(br.readLine());
        int start = st.nextInt();
        int finish = st.nextInt();
        System.out.println(solution(1, 1000000000, start, finish, N));
    }

    static int solution(int start, int end, int startCity, int finishCity, int N) {
        int answer = 0;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (canVisit(startCity, finishCity, N, mid)) {
                answer = Math.max(answer, mid);
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return answer;
    }

    static boolean canVisit(int start, int finish, int N, int weight) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        visited[start] = true;
        queue.add(start);
        while (!queue.isEmpty()) {
            int index = queue.poll();
            if (index == finish) return true;
            if (null != adjacentList[index]) {
                for (Edge edge : adjacentList[index]) {
                    if (!visited[edge.to] && edge.weight >= weight) {
                        queue.add(edge.to);
                        visited[edge.to] = true;
                    }
                }
            }
        }
        return false;
    }

    static class IntegerTokenizer extends java.util.StringTokenizer {

        public IntegerTokenizer(String str, String delim, boolean returnDelims) {
            super(str, delim, returnDelims);
        }

        public IntegerTokenizer(String str, String delim) {
            super(str, delim);
        }

        public IntegerTokenizer(String str) {
            super(str);
        }

        public int nextInt() {
            return Integer.parseInt(nextToken());
        }
    }
}
