package per.jongho.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.BiFunction;

import static java.lang.System.in;

public class P10546 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        ArrayList<String> participant = new ArrayList<>();
        ArrayList<String> completions = new ArrayList<>();
        int N = Integer.parseInt(br.readLine()); //마라톤 참가자 수
        int M = N - 1;
        while (N-- > 0) {
            participant.add(br.readLine());
        }
        while (M-- > 0) {
            completions.add(br.readLine());
        }
        System.out.println(solution(participant, completions));
    }

    static String solution(ArrayList<String> participants, ArrayList<String> completions) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (String participant : participants)
            hashMap.compute(participant, (s, integer) -> integer == null ? 1 : integer + 1);

        for (String completion : completions) {
            int value = hashMap.get(completion);
            if ((--value) == 0) {
                hashMap.remove(completion);
            } else {
                hashMap.put(completion, value);
            }
        }
        String answer = hashMap.keySet().toString();
        return answer.substring(1, answer.length() - 1);
    }
}
