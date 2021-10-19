package per.jongho.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P15656 {
    static ArrayList<Integer> arr = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        StringTokenizer input = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr.add(Integer.parseInt(st.nextToken()));

        arr.sort(Comparator.naturalOrder());
        solution(0, M, new int[M]);
        System.out.print(sb);
    }

    static StringBuilder sb = new StringBuilder();

    static void solution(int cnt, int M, int[] list) {
        if (cnt == M) {
            for (int i = 0; i < M; i++) {
                sb.append(list[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < arr.size(); i++) {
            list[cnt] = arr.get(i);
            solution(cnt + 1, M, list);
        }
    }
}
