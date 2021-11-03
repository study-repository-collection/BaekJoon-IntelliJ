package per.october.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P2251 {
    static int[] arr = new int[3];
    static boolean[][] visited = new boolean[201][201];
    static boolean[] answer = new boolean[201];
    static int A;
    static int B;
    static int C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        A = Integer.parseInt(stringTokenizer.nextToken());
        B = Integer.parseInt(stringTokenizer.nextToken());
        C = Integer.parseInt(stringTokenizer.nextToken());
        dfs(0,0,C);
        for(int i=0;i<201;i++){
            if(answer[i]){
                sb.append(i).append(" ");
            }
        }
        System.out.println(sb);
    }

    static void dfs(int a, int b, int c) {
        //A에 담긴 물 양이 0일 때 visited[B][C] 를 방문체크한다.
        if (visited[a][b]) return;
        if (a == 0) answer[c] = true;
        visited[a][b] = true;
        if (a + b > B) {
            dfs((a + b) - B, B, c);
        } else {
            dfs(0, a + b, c);
        }
        if (a + b > A) {
            dfs(A, (a + b) - A, c);
        } else {
            dfs(a + b, 0, c);
        }
        if (c + a > A) {
            dfs(A, b, (a + c) - A);
        } else {
            dfs(a + c, b, 0);
        }
        if (c + b > B) {
            dfs(a, B, (c + b) - B);
        } else {
            dfs(a, c + b, 0);
        }
        dfs(0, b, a + c);
        dfs(a, 0, b + c);
    }
}
