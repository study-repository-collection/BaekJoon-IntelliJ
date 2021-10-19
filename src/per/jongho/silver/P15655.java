package per.jongho.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P15655 {

    static int N,M;
    static int[] arr;
    static int[] res;
    static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        arr = new int[N];
        input = br.readLine().split(" ");
        for (int i = 0; i < N ; i++){
            arr[i] = Integer.parseInt(input[i]);
        }

        Arrays.sort(arr);

        res = new int[M];

        dfs(0,0);

        System.out.print(sb.toString());
    }

    static void dfs(int start, int cnt){

        if (cnt == M){

            for (int i = 0; i < M ; i++){
                sb.append(res[i]).append(' ');
            }
            sb.append('\n');
            return;
        }

        for (int i = start; i < N ; i++){

            res[cnt] = arr[i];
            dfs(i+1, cnt+1);
        }

    }
}