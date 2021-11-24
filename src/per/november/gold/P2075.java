package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.System.in;
import static java.lang.System.lineSeparator;

public class P2075 {
    static PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                queue.add(Integer.parseInt(input.nextToken()));
            }
        }
        int temp = 0;
        for (int i = 0; i < N; i++) {
            temp = queue.poll();
        }
        System.out.println(temp);
    }
    //모든 수는 자신의 한 칸 위에 있는 수보다 크다. = 맨 윗줄에 제일 작은 1번쨰 수가 존재한다.
}
