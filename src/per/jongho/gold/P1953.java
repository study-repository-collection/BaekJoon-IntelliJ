package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1953 {
    static ArrayList<Integer>[] hatePerson;
    static ArrayList<Integer> A = new ArrayList<>();
    static ArrayList<Integer> B = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        hatePerson = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            st.nextToken();
            hatePerson[i] = new ArrayList<>();
            while (st.hasMoreTokens()) {
                int B = Integer.parseInt(st.nextToken());
                hatePerson[i].add(B);
            }
        }
        //입력 종료

        boolean[] visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                dfs(i, visited, ATEAM);
            }
        }
        A.sort(null);
        B.sort(null);
        sb.append(A.size()).append("\n");
        for (int a : A) {
            sb.append(a).append(" ");
        }
        sb.append("\n").append(B.size()).append("\n");
        for (int b : B) {
            sb.append(b).append(" ");
        }
        System.out.print(sb);
    }

    static final int ATEAM = 0;
    static final int BTEAM = 1;

    static void dfs(int to, boolean[] visited, int team) {
        visited[to] = true;
        if (team == ATEAM) {
            A.add(to);
        } else {
            B.add(to);
        }
        for (int toGo : hatePerson[to]) {
            if (!visited[toGo]) {
                dfs(toGo, visited, team == ATEAM ? BTEAM : ATEAM);
            }
        }
    }
}
