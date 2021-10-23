package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1717 {

    static final int UNION = 0;
    static final int CHECK = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stringTokenizer.nextToken()); //수의 개수
        int M = Integer.parseInt(stringTokenizer.nextToken()); //연산의 회수
        solution(br, M, N);
    }

    static void solution(BufferedReader br, int M, int N) throws IOException {
        int[] parent = new int[N + 1];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= N; i++) parent[i] = i;

        while (M-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int operation = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            switch (operation) {
                case UNION:
                    union(a, b, parent);
                    break;
                case CHECK:
                    if (check(a, b, parent)) sb.append("YES").append("\n");
                    else sb.append("NO").append("\n");
                    break;
            }
        }
        System.out.println(sb);
    }

    static boolean check(int a, int b, int[] parent) {
        a = find(a, parent);
        b = find(b, parent);
        return a == b;
    }

    static int find(int a, int[] parent) {
        if (a == parent[a]) return a;
        else return parent[a] = find(parent[a], parent);
    }

    static void union(int a, int b, int[] parent) {
        a = find(a, parent);
        b = find(b, parent);

        if (a > b) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }
    }
}
