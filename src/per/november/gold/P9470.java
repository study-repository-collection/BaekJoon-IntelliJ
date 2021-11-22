package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P9470 {
    static int[] degree;
    static ArrayList<Integer>[] adjacentList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(input.nextToken());
            int M = Integer.parseInt(input.nextToken());
            int P = Integer.parseInt(input.nextToken());
            adjacentList = new ArrayList[M + 1];
            degree = new int[M + 1];
            while (P-- > 0) {
                StringTokenizer edgeInfo = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(edgeInfo.nextToken());
                int B = Integer.parseInt(edgeInfo.nextToken());
                degree[B]++;
                if (null == adjacentList[A]) adjacentList[A] = new ArrayList<>();
                adjacentList[A].add(B);
            }


            System.out.println(K + " " + solution(M));
        }
    }

    static int solution(int M) {
        int[] answer = new int[M + 1];
        boolean[] add = new boolean[M + 1];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= M; i++) {
            if (degree[i] == 0) {
                queue.add(i);
                answer[i] = 1;
            }
        }

        while (!queue.isEmpty()) {
            int index = queue.poll();
            if (null != adjacentList[index]) {
                for (int value : adjacentList[index]) {
                    degree[value]--;
                    if (answer[value] < answer[index]) {
                        answer[value] = answer[index];
                        add[value] = false;
                    } else if (answer[value] == answer[index]) {
                        add[value] = true;
                    }
                    if (degree[value] == 0) {
                        queue.add(value);
                        if (add[value]) answer[value] = answer[value] + 1;
                    }
                }
            } else {
                return answer[index];
            }
        }
        return -1;
    }
}
