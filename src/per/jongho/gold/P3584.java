package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P3584 {
    static int[] whoIsRoot;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            whoIsRoot = new int[N + 1];
            for (int i = 0; i < N - 1; i++) {
                StringTokenizer edge = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(edge.nextToken());
                int b = Integer.parseInt(edge.nextToken());
                whoIsRoot[b] = a;
            }
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(toRoot(a, b)).append("\n");
        }
        System.out.print(sb);
    }

    static int toRoot(int a, int b) {
        ArrayList<Integer> aRoot = new ArrayList<>();
        ArrayList<Integer> bRoot = new ArrayList<>();
        aRoot.add(a);
        bRoot.add(b);
        while (whoIsRoot[a] != 0) {
            a = whoIsRoot[a];
            aRoot.add(a);
        }
        while (whoIsRoot[b] != 0) {
            b = whoIsRoot[b];
            bRoot.add(b);
        }
        for (Integer value : aRoot) {
            for (Integer integer : bRoot) {
                if (value.equals(integer)) {
                    return value;
                }
            }
        }
        return -1;
    }
}
