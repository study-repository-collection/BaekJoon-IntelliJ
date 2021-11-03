package per.october.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P3665 {

    static int[] ranking;
    static int[] degree;
    static final int UNABLE = -1;
    static final int UN_ACCURATE = -2;
    static ArrayList<Integer>[] adjacentList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder realAnswer = new StringBuilder();
        int T = Integer.parseInt(br.readLine());//테스트 케이스의 개수
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());//팀의 개수
            StringBuilder sb = new StringBuilder();
            ranking = new int[n + 1];
            degree = new int[n + 1];
            adjacentList = new ArrayList[n + 1];
            StringTokenizer rankingInfo = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                ranking[i] = Integer.parseInt(rankingInfo.nextToken());
                degree[ranking[i]] = i - 1;
            }
            initAdjacentList(n);
            int m = Integer.parseInt(br.readLine());//상대적인 등수가 바뀐 쌍의 수
            while (m-- > 0) {
                StringTokenizer mInfo = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(mInfo.nextToken());//상대적인 등수가 바뀐 팀
                int b = Integer.parseInt(mInfo.nextToken());//상대적인 등수가 바뀐 팀
                swapRanking(a, b, n);
            }
            int result = solution(n, sb);
            if (result == 1) {
                realAnswer.append(sb).append("\n");
            } else if (result == UNABLE) {
                realAnswer.append("IMPOSSIBLE").append("\n");
            } else {
                realAnswer.append("?").append("\n");
            }
        }
        System.out.println(realAnswer);
    }


    static void swapRanking(int a, int b, int n) {
        //a가 b보다 순위가 앞서는 경우
        for (int i = 0; i < adjacentList[a].size(); i++) {
            if (adjacentList[a].get(i) == b) {
                degree[a]++;
                degree[b]--;
                adjacentList[a].remove(i);
                adjacentList[b].add(a);
                return;
            }
        }
        for (int i = 0; i < adjacentList[b].size(); i++) {
            if (adjacentList[b].get(i) == a) {
                degree[b]++;
                degree[a]--;
                adjacentList[b].remove(i);
                adjacentList[a].add(b);
                return;
            }
        }
    }

    static void initAdjacentList(int n) {
        for (int i = 1; i <= n - 1; i++) {
            adjacentList[ranking[i]] = new ArrayList<>();
            for (int j = i + 1; j <= n; j++) {
                adjacentList[ranking[i]].add(ranking[j]);
            }
        }
        adjacentList[ranking[n]] = new ArrayList<>();
    }

    static int solution(int n, StringBuilder sb) {
        Queue<Integer> sequence = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        boolean isUnAccurate = false;
        for (int i = 1; i <= n; i++) {
            if (degree[i] == 0) {
                sequence.add(i);
            }
        }
        if (sequence.size() > 1) {
            isUnAccurate = true;
        }

        while (!sequence.isEmpty()) {
            int next = sequence.poll();
            visited[next] = true;
            if (!sequence.isEmpty()) {
                isUnAccurate = true;
            }
            sb.append(next).append(" ");
            for (int to : adjacentList[next]) {
                degree[to]--;
                if (degree[to] == 0) {
                    sequence.add(to);
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) return UNABLE;
        }
        return isUnAccurate ? UN_ACCURATE : 1;
    }
}
