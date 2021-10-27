package per.jongho.silver;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;

public class P2565 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int tempN = N;
        ArrayList<Point> s = new ArrayList<>();
        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            s.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        s.sort(Comparator.comparingInt(o -> o.x));
        System.out.println(solution(s, tempN));
    }

    static int solution(ArrayList<Point> sequence, int N) {
        ArrayList<Integer> C = new ArrayList<>();
        C.add(sequence.get(0).y);
        for (int i = 1; i < N; i++) {
            if (sequence.get(i).y > C.get(C.size() - 1)) {
                C.add(sequence.get(i).y);
            } else {
                int index = Collections.binarySearch(C, sequence.get(i).y);
                if (index < 0) {
                    index = index * -1 - 1;
                }
                C.set(index, sequence.get(i).y);
            }
        }
        return N - C.size();
    }

}
