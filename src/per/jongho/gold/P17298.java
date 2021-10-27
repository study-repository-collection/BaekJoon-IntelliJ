package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P17298 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> arrayList = new ArrayList<>();
        StringTokenizer sequenceInfo = new StringTokenizer(br.readLine());
        while (sequenceInfo.hasMoreTokens()) {
            arrayList.add(Integer.parseInt(sequenceInfo.nextToken()));
        }
        solution(N, arrayList);
        Math.pow(1,3);
    }


    static void solution(int N, ArrayList<Integer> arrayList) {
        Stack<Integer> answer = new Stack<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = arrayList.size() - 1; i >= 0; i--) {
            if (stack.isEmpty()) {
                answer.add(-1);
                stack.add(arrayList.get(i));
            } else if (stack.peek() > arrayList.get(i)) {
                answer.add(stack.peek());
                stack.add(arrayList.get(i));
            } else {
                while (!stack.isEmpty() && stack.peek() <= arrayList.get(i)) {
                    stack.pop();
                }
                if (stack.isEmpty()) {
                    answer.add(-1);
                    stack.add(arrayList.get(i));
                } else {
                    answer.add(stack.peek());
                    stack.add(arrayList.get(i));
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!answer.isEmpty()) {
            sb.append(answer.pop()).append(" ");
        }
        System.out.println(sb);
    }
}
