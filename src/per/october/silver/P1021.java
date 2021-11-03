package per.october.silver;

import java.io.IOException;
import java.util.*;

import static java.lang.System.in;

public class P1021 {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner(in);
        int N = scanner.nextInt(); //큐의 크기
        int M = scanner.nextInt(); //뽑을 수의 개수
        scanner.nextLine();
        int[] numberToPick = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(solution(N, M, numberToPick));

    }

    public static int solution(int N, int M, int[] numberToPick) {
        Deque<Integer> list = new LinkedList<>();
        int sum = 0;
        //ArrayList 초기화
        init(N, list);

        for (int i = 0; i < M; i++) {
            int find = numberToPick[i];
            sum += goToNumber(find, list, goToLeft(find, list));
        }
        return sum;
    }

    public static int goToNumber(int find, Deque<Integer> list, boolean goToLeft) {
        int count = 0;
        if (goToLeft) {
            while (!list.isEmpty()) {
                int poll = list.pollFirst();
                if (poll == find) {
                    break;
                } else {
                    count++;
                    list.addLast(poll);
                }
            }
        } else {
            while (!list.isEmpty()) {
                count++;
                int poll = list.pollLast();
                if (poll == find) {
                    break;
                } else {
                    list.addFirst(poll);
                }
            }
        }
        return count;
    }

    public static boolean goToLeft(int find, Deque<Integer> list) {
        int index = 0;
        for (int number : list) {
            index++;
            if (find == number) break;
        }
        return index - 1 < list.size() - index + 1;
    }

    private static void init(int N, Deque<Integer> list) {
        for (int i = 1; i <= N; i++) list.add(i);
    }
}
