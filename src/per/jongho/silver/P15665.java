package per.jongho.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P15665 {

    static ArrayList<Integer> arrayList = new ArrayList<>();
    static HashMap<String, Boolean> hashMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        StringTokenizer input = new StringTokenizer(br.readLine());

        while (input.hasMoreTokens()) {
            int a = Integer.parseInt(input.nextToken());
            arrayList.add(a);
        }
        arrayList.sort(null);
        solution2(0, M, new int[M]);
        System.out.print(sb);
    }

    static void solution2(int cnt, int M, int[] arr) {
        if (cnt == M) {
            for (int i = 0; i < M; i++) sb.append(arr[i]).append(" ");
            sb.append("\n");
            return;
        }

        int num = 0;
        for (int i = 0; i < arrayList.size(); i++) {
            int a = arrayList.get(i);
            if (num != a) {
                arr[cnt] = a;
                solution2(cnt + 1, M, arr);
                num = a;
            }
        }
    }

    static StringBuilder sb = new StringBuilder();

    static void solution(int cnt, int M, int[] arr) {
        if (cnt == M) {
            if (!hashMap.containsKey(Arrays.toString(arr))) {
                for (int i = 0; i < M; i++) sb.append(arr[i]).append(" ");
                sb.append("\n");
                hashMap.put(Arrays.toString(arr), true);
            }
            return;
        }

        for (int i = 0; i < arrayList.size(); i++) {
            arr[cnt] = arrayList.get(i);
            solution(cnt + 1, M, arr);
        }
    }
}
