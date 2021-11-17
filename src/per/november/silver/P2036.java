package per.november.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

import static java.lang.System.*;

public class P2036 {
    static PriorityQueue<Integer> positiveArrayList = new PriorityQueue<>(Comparator.reverseOrder());
    static PriorityQueue<Integer> negativeArrayList = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            int a = Integer.parseInt(br.readLine());
            if (a > 0) {
                positiveArrayList.add(a);
            } else {
                negativeArrayList.add(a);
            }
        }
        System.out.println(solution());
    }

    static long solution() {
        long sum = 0;
        while (!positiveArrayList.isEmpty()) {
            int temp1 = positiveArrayList.poll();
            if (temp1 == 1) {
                sum += temp1;
            } else if (!positiveArrayList.isEmpty()) {
                int temp2 = positiveArrayList.poll();
                sum += Math.max((long) temp1 * temp2, (long) temp1 + temp2);
            } else {
                sum += temp1;
            }
        }
        while (!negativeArrayList.isEmpty()) {
            int temp1 = negativeArrayList.poll();
            if (!negativeArrayList.isEmpty()) {
                int temp2 = negativeArrayList.poll();
                sum += (long) temp1 * temp2;
            } else {
                sum += temp1;
            }
        }
        return sum;
    }
}
