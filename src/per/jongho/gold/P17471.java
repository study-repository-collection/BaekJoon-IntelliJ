package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P17471 {
    static int[] personNumbers;
    static ArrayList<Integer>[] adjacentList;

    static int min = Integer.MAX_VALUE;
    static ArrayList<Integer> A = new ArrayList<>(); // A 선거구
    static ArrayList<Integer> B = new ArrayList<>(); // B 선거구
    static int[] remainder;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine()); //구역의 개수
        adjacentList = new ArrayList[N];
        personNumbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            int lineNumber = Integer.parseInt(input.nextToken());
            adjacentList[i] = new ArrayList<>();
            while (lineNumber-- > 0) {
                adjacentList[i].add(Integer.parseInt(input.nextToken()));
            }
        }
        remainder = new int[N + 1];
        for (int i = 1; i <= N; i++) remainder[i] = 1;
        //입력 종료
        makeCombination(true, 1, N);
        makeCombination(false, 1, N);
        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }
    }

    static void makeCombination(boolean choose, int index, int N) {
        if (index == N) {
            for (int i = 1; i <= N; i++) {
                if (remainder[i] == 1) {
                    B.add(i);
                }
            }
            checkCombination(N);
            B.clear();
            return;
        }
        if (choose) {
            A.add(index);
            remainder[index] = 0;
            makeCombination(true, index + 1, N);
            makeCombination(false, index + 1, N);
            remainder[index] = 1;
            A.remove(Integer.valueOf(index));
        } else {
            makeCombination(true, index + 1, N);
            makeCombination(false, index + 1, N);
        }
    }

    static void checkCombination(int N) {
        if (A.isEmpty() || B.isEmpty()) return;
        int a = A.get(0);
        int b = B.get(0);

        boolean[] aVisit = new boolean[N + 1];
        boolean[] bVisit = new boolean[N + 1];
        dfs(a, aVisit, A);
        if (!isConnected(A, aVisit)) {
            return;
        }
        dfs(b, bVisit, B);
        if (!isConnected(B, bVisit)) {
            return;
        }
        int countA = sumOfList(A);
        int countB = sumOfList(B);
        if (min >= Math.abs(countA - countB)) {
            min = Math.abs(countA - countB);
        }
    }

    static int sumOfList(ArrayList<Integer> arr) {
        int sum = 0;
        for (int value : arr) sum += personNumbers[value - 1];
        return sum;
    }

    static void dfs(int to, boolean[] visit, ArrayList<Integer> combination) {
        visit[to] = true;
        if (adjacentList[to - 1] != null) {
            for (int connect : adjacentList[to - 1]) {
                if (!visit[connect] && combination.contains(connect)) {
                    dfs(connect, visit, combination);
                }
            }
        }
    }

    static boolean isConnected(ArrayList<Integer> arr, boolean[] visit) {
        for (int value : arr) if (!visit[value]) return false;
        return true;
    }
}
