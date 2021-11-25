package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

import static java.lang.System.in;

public class P1644 {
    static final int MAX = 4000001;
    static boolean[] primeNumber;
    static Queue<Integer> primeNumbers = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        primeNumber = new boolean[N + 1];
        Arrays.fill(primeNumber, true);
        findPrimeNumber(N);
        for (int i = 2; i <= N; i++) {
            if (primeNumber[i]) {
                primeNumbers.add(i);
            }
        }
        System.out.println(solution(N));
    }

    static int solution(int N) {
        long totalSum = 0;
        int ret = 0;
        Deque<Integer> deque = new LinkedList<>();
        while (!primeNumbers.isEmpty()) {
            if (totalSum <= N) {
                int temp = primeNumbers.poll();
                deque.add(temp);
                totalSum += temp;
                if (totalSum == N) {
                    ret++;
                }
            } else {
                while (totalSum > N && !deque.isEmpty()) {
                    int temp = deque.pollFirst();
                    totalSum -= temp;
                }
                if (totalSum == N) {
                    ret++;
                }
            }
        }
        while (!deque.isEmpty()) {
            totalSum -= deque.pollFirst();
            if (totalSum == N) {
                ret++;
            }
        }
        return ret;
    }

    static void findPrimeNumber(int N) {
        for (int i = 2; i * i <= N; i++) {
            for (int j = i * i; j <= N; j += i) {
                primeNumber[j] = false;
            }
        }
    }
}
