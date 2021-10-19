package per.jongho.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

import static java.lang.System.in;

public class P1174 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine()); //N번째로 작은 줄어드는 수를 구하시오
        System.out.println(solution(N));
    }

    static long solution(int N) {
        Queue<Long> queue = new LinkedList<>();
        for (int i = 0; i <= 9; i++) queue.add((long) i);
        long poll = 0;
        int count = -1;
        while (count != N && !queue.isEmpty()) {
            poll = queue.poll();
            count++;
            long add = poll * 10;
            for (int i = 0; i < poll % 10; i++) queue.add(add + i);
        }
        return count == N ? poll : -1;
    }
}
