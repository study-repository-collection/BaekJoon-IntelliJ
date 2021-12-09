package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P14395 {
    static char[] operator = {'*', '+', '-', '/'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer input = new StringTokenizer(br.readLine());
        long s = Long.parseLong(input.nextToken());
        long t = Long.parseLong(input.nextToken());
        if (s == t) {
            System.out.println(0);
        } else {
            System.out.println(solution(s, t));
        }
    }

    static String solution(long s, long t) {
        boolean[] visited = new boolean[(int) Math.max(s, t) + 1];
        Queue<String> queue = new LinkedList<>();
        queue.add("");
        visited[(int) s] = true;
        int count = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String temp = queue.poll();
                for (char a : operator) {
                    String nextTemp = temp + a;
                    long tempNumber = sum(s, nextTemp, t);
                    if (tempNumber != -1) {
                        if (tempNumber == t) {
                            return nextTemp;
                        }
                        if (!visited[(int) tempNumber]) {
                            visited[(int) tempNumber] = true;
                            queue.add(nextTemp);
                        }
                    }
                }

            }
            count++;
        }
        return "-1";
    }


    static long sum(long start, String operators, long t) {
        for (int i = 0; i < operators.length(); i++) {
            switch (operators.charAt(i)) {
                case '*':
                    start *= start;
                    if (start > t) {
                        return -1;
                    }
                    break;
                case '+':
                    start *= 2;
                    if (start > t) {
                        return -1;
                    }
                    break;
                case '-':
                    return -1;
                case '/':
                    start = 1;
                    break;
            }
        }
        return start;
    }
}
