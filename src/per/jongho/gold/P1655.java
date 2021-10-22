package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

import static java.lang.System.in;

public class P1655 {
    static PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.reverseOrder());
    static PriorityQueue<Integer> maxHeap = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int count = 0;
        while (N-- > 0) {
            count++;
            int number = Integer.parseInt(br.readLine());
            if (!minHeap.isEmpty() && minHeap.peek() < number) {
                maxHeap.add(number);
            } else {
                minHeap.add(number);
            }
            makeBalance();
            sb.append(getMedian(count)).append("\n");

        }
        System.out.println(sb);
    }

    static void makeBalance() {
        if (minHeap.size() >= maxHeap.size() + 2) {
            maxHeap.add(minHeap.poll());
        } else if (minHeap.size() + 2 <= maxHeap.size()) {
            minHeap.add(maxHeap.poll());
        }
    }

    static int getMedian(int count) {
        if ((count & 1) == 1) {
            if (minHeap.size() > maxHeap.size()) {
                return minHeap.peek();
            } else {
                return maxHeap.peek();
            }
        } else {
            return minHeap.peek();
        }
    }
}
