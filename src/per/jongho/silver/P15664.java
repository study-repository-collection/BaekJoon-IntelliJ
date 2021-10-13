package per.jongho.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

public class P15664 {
    static ArrayList<Integer> arrayList = new ArrayList<>();
    static HashMap<String, Boolean> hashMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        StringTokenizer st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) arrayList.add(Integer.parseInt(st.nextToken()));

        arrayList.sort(Comparator.naturalOrder());

        solution(0, M, new int[M], 0);
        System.out.print(sb);
    }

    static StringBuilder sb = new StringBuilder();

    static void solution(int cnt, int M, int[] arr, int index) {
        if (cnt == M) {
            if (!hashMap.containsKey(Arrays.toString(arr))) {
                for (int i = 0; i < M; i++) sb.append(arr[i]).append(" ");
                sb.append("\n");
                hashMap.put(Arrays.toString(arr), true);
            }
            return;
        }

        for (int i = index; i < arrayList.size(); i++) {
            arr[cnt] = arrayList.get(i);
            solution(cnt + 1, M, arr, i + 1);
        }
    }
}
