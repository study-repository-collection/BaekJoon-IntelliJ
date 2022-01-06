package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1461 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer input = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        StringTokenizer bookPositionInfo = new StringTokenizer(br.readLine());
        PriorityQueue<Integer> leftBook = new PriorityQueue<>();
        PriorityQueue<Integer> rightBook = new PriorityQueue<>(Comparator.reverseOrder());

        while (bookPositionInfo.hasMoreTokens()) {
            int a = Integer.parseInt(bookPositionInfo.nextToken());
            if (a < 0) {
                leftBook.add(a);
            } else {
                rightBook.add(a);
            }
        }
        System.out.println(solution(leftBook, rightBook, M));
    }

    static int solution(PriorityQueue<Integer> leftBook, PriorityQueue<Integer> rightBook, int M) {
        int ret = 0;
        int count = 0;
        int initialDistance = 0;
        PriorityQueue<Integer> first;
        PriorityQueue<Integer> second;
        if (leftBook.isEmpty()) {
            first = rightBook;
            second = leftBook;
        } else if (rightBook.isEmpty()) {
            first = leftBook;
            second = rightBook;
        } else if (leftBook.peek() * -1 > rightBook.peek()) {
            first = leftBook;
            second = rightBook;
        } else {
            first = rightBook;
            second = leftBook;
        }
        while (count < M && !first.isEmpty()) {
            count++;
            if (count == 1) {
                initialDistance = Math.abs(first.poll());
            } else {
                first.poll();
            }
        }
        ret += initialDistance;
        while (!first.isEmpty()) {
            initialDistance = 0;
            count = 0;
            while (count < M && !first.isEmpty()) {
                count++;
                if (count == 1) {
                    initialDistance = Math.abs(first.poll());
                } else {
                    first.poll();
                }
            }
            ret += initialDistance*2;
        }

        while (!second.isEmpty()) {
            initialDistance = 0;
            count = 0;
            while (count < M && !second.isEmpty()) {
                count++;
                if (count == 1) {
                    initialDistance = Math.abs(second.poll());
                } else {
                    second.poll();
                }
            }
            ret += initialDistance*2;
        }

        return ret;
    }
}
