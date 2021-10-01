package per.jongho.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Collator;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.in;

public class P1026 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine()); //배열의 길이
        ArrayList<Integer> A = intArrayToList(Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray());
        ArrayList<Integer> B = intArrayToList(Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray());

        System.out.println(solution(A, B, N));
    }

    public static int solution(ArrayList<Integer> A, ArrayList<Integer> B, int N) {
        int sum = 0;
        A.sort(Comparator.naturalOrder());
        B.sort(Comparator.reverseOrder());
        for (int i = 0; i < A.size(); i++) {
            sum += A.get(i) * B.get(i);
        }
        return sum;
    }

    private static ArrayList<Integer> intArrayToList(int[] arr) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int value : arr) list.add(value);
        return list;
    }
}
