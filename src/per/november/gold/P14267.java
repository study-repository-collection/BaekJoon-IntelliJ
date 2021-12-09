package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P14267 {
    static int[] parent;
    static int[] praise;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        parent = new int[N + 1];
        praise = new int[N + 1];
        StringTokenizer info = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            parent[i] = Integer.parseInt(info.nextToken());
        }
        while (M-- > 0) {
            StringTokenizer praiseInfo = new StringTokenizer(br.readLine());
            int number = Integer.parseInt(praiseInfo.nextToken());
            int praiseAmount = Integer.parseInt(praiseInfo.nextToken());
            praise[number] += praiseAmount;
        }

        for (int i = 1; i <= N; i++) {
            int sum = sum(i);
            parent[i] = -1;
            praise[i] = sum;
            sb.append(sum).append(" ");
        }
        System.out.println(sb);
    }

    static int sum(int number) {
        if (number == -1) return 0;
        else return praise[number] + sum(parent[number]);
    }
}
