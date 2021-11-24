package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

public class P1963 {
    static boolean[] primeNumber = new boolean[10000];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        Arrays.fill(primeNumber, true);
        findPrimeNumber();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int finish = Integer.parseInt(st.nextToken());
            int ret;
            sb.append((ret = solution(start, finish)) == -1 ? "Impossible" : ret).append("\n");
        }
        System.out.print(sb);
    }

    static int solution(int start, int finish) {
        boolean[] visited = new boolean[10000];
        visited[start] = true;
        Queue<Integer> queue = new LinkedList<>();
        int count = 0;
        queue.add(start);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int temp = queue.poll();
                if (temp == finish) {
                    return count;
                }
                findNextPrime(temp, visited, queue);
            }
            count++;
        }
        return -1;
    }

    static void findNextPrime(int number, boolean[] visited, Queue<Integer> queue) {
        int temp = number % 1000;
        for (int i = 1; i <= 9; i++) {
            int tempNumber = temp;
            tempNumber += i * 1000;
            if (primeNumber[tempNumber] && !visited[tempNumber]) {
                visited[tempNumber] = true;
                queue.add(tempNumber);
            }
        }
        temp = temp / 100;
        temp = number - (temp * 100);
        for (int i = 0; i <= 9; i++) {
            int tempNumber = temp;
            tempNumber += i * 100;
            if (primeNumber[tempNumber] && !visited[tempNumber]) {
                visited[tempNumber] = true;
                queue.add(tempNumber);
            }
        }
        temp = number - ((number % 100) / 10) * 10;
        for (int i = 0; i <= 9; i++) {
            int tempNumber = temp;
            tempNumber += i * 10;
            if (primeNumber[tempNumber] && !visited[tempNumber]) {
                visited[tempNumber] = true;
                queue.add(tempNumber);
            }
        }
        temp = number - (number % 10);
        for (int i = 1; i <= 9; i += 2) {
            int tempNumber = temp;
            tempNumber += i;
            if (primeNumber[tempNumber] && !visited[tempNumber]) {
                visited[tempNumber] = true;
                queue.add(tempNumber);
            }
        }
    }

    static void findPrimeNumber() {
        for (int i = 2; i * i <= 10000; i++) {
            for (int j = i * i; j < 10000; j += i) {
                primeNumber[j] = false;
            }
        }
    }
}
