package per.october.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1516 {

    static int[] degree;

    static int[] buildTime;
    static ArrayList<Integer>[] adjacentList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        degree = new int[N + 1];
        buildTime = new int[N + 1];
        adjacentList = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            StringTokenizer info = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(info.nextToken());
            buildTime[i] = time;
            while (info.hasMoreTokens()) {
                int a = Integer.parseInt(info.nextToken());
                if (a != -1) {
                    degree[i]++;
                    if (adjacentList[a] == null) adjacentList[a] = new ArrayList<>();
                    adjacentList[a].add(i);
                }
            }
        }
        topologicalSort(N);
    }

    static void topologicalSort(int N) {
        Queue<Integer> zeroDegree = new LinkedList<>();
        int[] buildTimes = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            if (degree[i] == 0) {
                zeroDegree.add(i);
            }
        }

        while (!zeroDegree.isEmpty()) {
            int city = zeroDegree.poll();
            if (null != adjacentList[city]) {
                for (int to : adjacentList[city]) {
                    degree[to]--;
                    buildTimes[to] = Math.max(buildTimes[to], buildTimes[city] + buildTime[city]);
                    if (degree[to] == 0) {
                        zeroDegree.add(to);
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(buildTimes[i] + buildTime[i]).append("\n");
        }
        System.out.print(sb);
    }
}
