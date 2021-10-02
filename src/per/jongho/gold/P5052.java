package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.System.in;

public class P5052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());//테스트 케이스 개수
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine()); //전화번호 개수
            ArrayList<String> phoneNumbers = new ArrayList<>();
            while (N-- > 0) phoneNumbers.add(br.readLine());
            System.out.println(solution(phoneNumbers));
        }
    }

    static String solution(ArrayList<String> phoneNumbers) {
        HashMap<String, Boolean> hashMap = new HashMap<>();
        for (String phoneNumber : phoneNumbers) hashMap.put(phoneNumber, true);

        for (String value : hashMap.keySet()) {
            for (int i = 0; i < value.length(); i++) {
                if (hashMap.containsKey(value.substring(0, i))) {
                    return "NO";
                }
            }
        }
        return "YES";
    }
}
