package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static java.lang.System.in;

public class P2981 {
    static ArrayList<Integer> arrayList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            arrayList.add(Integer.parseInt(br.readLine()));
        }
        arrayList.sort(null);
        solution(N);
    }

    static void solution(int N) {
        int gcd;
        ArrayList<Integer> sequence = new ArrayList<>();
        for (int i = 0; i < arrayList.size() - 1; i++) {
            sequence.add(arrayList.get(i + 1) - arrayList.get(i));
        }
        gcd = sequence.get(0);
        for (int i = 1; i < sequence.size(); i++) {
            gcd = euclid(sequence.get(i), gcd);
        }

        ArrayList<Integer> answer = new ArrayList<>();
        answer.add(gcd);
        for (int i = 2; i * i <= gcd; i++) {
            if (gcd % i == 0) {
                int number = gcd / i;
                answer.add(i);
                if (number != i) {
                    answer.add(gcd / i);
                }
            }
        }
        answer.sort(null);
        StringBuilder sb = new StringBuilder();
        for (int value : answer) {
            sb.append(value).append(" ");
        }
        System.out.println(sb);
    }


    static int euclid(int a, int b) {
        if (b > a) {
            int temp = a;
            a = b;
            b = temp;
        }
        int r = a % b;
        if (r == 0) return b;
        else return euclid(b, r);
    }
}
