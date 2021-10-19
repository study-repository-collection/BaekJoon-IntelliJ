package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1759 {

    static int[] alphabet = new int[26];
    static final int JAUM = 0;
    static final int MOUM = 1;

    static final ArrayList<Character> alphabets = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        StringTokenizer input = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(input.nextToken()); //비밀번호 자리수
        int C = Integer.parseInt(input.nextToken()); //가능성 있는 문자 모음
        StringTokenizer alphabetsInfo = new StringTokenizer(br.readLine());

        while (alphabetsInfo.hasMoreTokens()) {
            alphabets.add(alphabetsInfo.nextToken().charAt(0));
        }
        alphabets.sort(null);
        init();
        solution(1, new char[L + 1], L, new boolean[C + 1], 0);
        System.out.print(sb);
    }

    static StringBuilder sb = new StringBuilder();

    static void solution(int cnt, char[] arr, int L, boolean[] visited, int start) {
        if (cnt == L + 1) {
            if (isMatch(arr, L)) {
                for (int i = 1; i <= L; i++) {
                    sb.append(arr[i]);
                }
                sb.append("\n");
            }
            return;
        }


        for (int i = start; i < alphabets.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                arr[cnt] = alphabets.get(i);
                solution(cnt + 1, arr, L, visited, i + 1);
                visited[i] = false;
            }
        }
    }

    static boolean isMatch(char[] arr, int L) {
        boolean moumExist = false;
        int sumOfJaum = 0;

        for (int i = 1; i <= L; i++) {
            char character = arr[i];
            if (alphabet[character - 'a'] == JAUM) {
                sumOfJaum++;
            } else {
                moumExist = true;
            }
        }
        return moumExist && sumOfJaum >= 2;
    }

    static void init() {
        alphabet['a' - 'a'] = MOUM;
        alphabet['e' - 'a'] = MOUM;
        alphabet['i' - 'a'] = MOUM;
        alphabet['o' - 'a'] = MOUM;
        alphabet['u' - 'a'] = MOUM;
    }
}
