package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P2056 {


    static int[] arr;
    static int[] degree;
    static ArrayList<Integer>[] adjacentList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        adjacentList = new ArrayList[N + 1];
        degree = new int[N + 1];
        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(input.nextToken());
            arr[i] = weight;
            int number = Integer.parseInt(input.nextToken());
            while (input.hasMoreTokens()) {
                int a = Integer.parseInt(input.nextToken());
                if (null == adjacentList[a]) adjacentList[a] = new ArrayList<>();
                adjacentList[a].add(i);
                degree[i]++;
            }
        }
        System.out.println(solution(N));
    }

    static int solution(int N) {
        int[] startTime = new int[N + 1];
        Queue<Integer> zeroDegrees = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (degree[i] == 0) {
                zeroDegrees.add(i);
                startTime[i] = 0;
            }
        }

        while (!zeroDegrees.isEmpty()) {
            int index = zeroDegrees.poll();
            if (null != adjacentList[index])
                for (int to : adjacentList[index]) {
                    degree[to]--;
                    if (degree[to] == 0) {
                        zeroDegrees.add(to);
                    }
                    startTime[to] = Math.max(startTime[to], startTime[index] + arr[index]);
                }
        }
        int max = -1;
        for (int i = 1; i <= N; i++) {
            max = Math.max(max, startTime[i] + arr[i]);
        }
        return max;
    }
}
