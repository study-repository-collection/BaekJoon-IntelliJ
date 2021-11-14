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
            } else if (a < 0) {
                negativeArrayList.add(a);
            }
        }
        System.out.println(solution());
    }

    public static long solution() {
        long sum = 0;
        while (!positiveArrayList.isEmpty()) {
            int temp1 = positiveArrayList.poll();
            int temp2 = 0;
            if (!positiveArrayList.isEmpty()) {
                temp2 = positiveArrayList.poll();
            }
            sum += Math.max((long) temp1 * temp2, (long) temp1 + temp2);
        }
        while (!negativeArrayList.isEmpty()) {
            int temp1 = negativeArrayList.poll();
            int temp2 = 0;
            if (!negativeArrayList.isEmpty()) {
                temp2 = negativeArrayList.poll();
            }
            if (temp2 != 0) {
                sum += (long) temp1 * temp2;
            } else {
                sum += temp1;
            }
        }
        return sum;
    }
}
