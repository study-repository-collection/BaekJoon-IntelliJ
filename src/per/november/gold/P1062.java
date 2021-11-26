package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1062 {
    static ArrayList<String> words = new ArrayList<>();
    static boolean[] teachCharacter = new boolean[26];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        init();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //단어의 개수 N
        int K = Integer.parseInt(st.nextToken()); //가르칠 수 있는 단어의 개수
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            words.add(input.substring(4, input.length() - 4));
        }
        if (K < 5) {
            System.out.println(0);
            return;
        }
        K -= 5;
        System.out.println(backTracking(K, 1));
    }

    static int backTracking(int K, int startIndex) {
        int answer = 0;
        if (K == 0) {
            answer = Math.max(answer, readCount());
            return answer;
        }
        for (int i = startIndex; i < 26; i++) {
            if (!teachCharacter[i]) {
                teachCharacter[i] = true;
                answer = Math.max(answer, backTracking(K - 1, i + 1));
                teachCharacter[i] = false;
            }
        }
        return answer;
    }

    static int readCount() {
        int count = 0;
        for (String temp : words) {
            boolean canRead = true;
            for (int i = 0; i < temp.length(); i++) {
                if (!teachCharacter[temp.charAt(i) - 'a']) {
                    canRead = false;
                    break;
                }
            }
            if (canRead) count++;
        }
        return count;
    }

    static void init() {
        teachCharacter[0] = true;
        teachCharacter['n' - 'a'] = true;
        teachCharacter['t' - 'a'] = true;
        teachCharacter['i' - 'a'] = true;
        teachCharacter['c' - 'a'] = true;
    }
}
