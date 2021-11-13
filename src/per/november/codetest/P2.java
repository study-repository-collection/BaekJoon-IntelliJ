package per.november.codetest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static java.lang.System.in;

public class P2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String s = br.readLine();
        System.out.println(solution(s));
    }

    public static int solution(String S) {
        int sum = 0;
        int maxLengthOfString = -1;
        ArrayList<Integer> lengthOfBlocks = new ArrayList<>();
        char currentChar = S.charAt(0);
        int currentCharStart = 0;
        for (int i = 0; i < S.length(); i++) {
            if (currentChar != S.charAt(i)) {
                currentChar = S.charAt(i);
                lengthOfBlocks.add(i - currentCharStart);
                maxLengthOfString = Math.max(maxLengthOfString, i - currentCharStart);
                currentCharStart = i;
            }
        }
        lengthOfBlocks.add(S.length() - currentCharStart);
        maxLengthOfString = Math.max(maxLengthOfString, S.length() - currentCharStart);
        for (int value : lengthOfBlocks) {
            sum += (maxLengthOfString - value);
        }
        return sum;
    }
}
