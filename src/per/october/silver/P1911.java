package per.october.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1911 {
    static PriorityQueue<Integer> woongDung = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        while (N-- > 0) {
            StringTokenizer woongDungInfo = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(woongDungInfo.nextToken());
            int finish = Integer.parseInt(woongDungInfo.nextToken());
            woongDung.add(start);
            woongDung.add(finish);
        }
        System.out.println(solution(L));
    }

    static int solution(int L) {
        int count = 0;
        int currentPosition = 0;
        while (!woongDung.isEmpty()) {
            int startPosition = woongDung.poll();
            if (startPosition > currentPosition) {
                currentPosition = startPosition;
            }
            int endPosition = woongDung.poll();
            while (currentPosition < endPosition) {
                currentPosition += L;
                count++;
            }
        }
        return count;
    }
}
