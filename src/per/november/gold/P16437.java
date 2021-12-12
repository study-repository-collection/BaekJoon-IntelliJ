package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P16437 {
    static final int SHEEP = 1;
    static final int WOLF = 2;
    static int[] types;
    static int[] counts;
    static ArrayList<Integer>[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        types = new int[N + 1];
        counts = new int[N + 1];
        nodes = new ArrayList[N + 1];
        for (int i = 2; i <= N; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            int type;
            if (input.nextToken().equals("S")) {
                type = SHEEP;
            } else {
                type = WOLF;
            }
            int count = Integer.parseInt(input.nextToken());
            int to = Integer.parseInt(input.nextToken());
            types[i] = type;
            counts[i] = count;
            if (null == nodes[to]) nodes[to] = new ArrayList<>();
            nodes[to].add(i);
        }
        System.out.println(search(1));
    }


    static long search(int node) {
        int type = types[node];
        int count = counts[node];
        long sumOfSheep = type == SHEEP ? count : 0;
        if (null != nodes[node]) {
            for (int to : nodes[node]) {
                sumOfSheep += search(to);
            }
        }
        if (type == WOLF) {
            sumOfSheep -= count;
        }

        return sumOfSheep < 0 ? 0 : sumOfSheep;
    }
}
