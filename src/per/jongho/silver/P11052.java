package per.jongho.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.System.in;

public class P11052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine()); //민규가 구매하려는 카드의 개수
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        ArrayList<Card> cards = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            cards.add(new Card(i + 1, input[i]));
        }
        System.out.println(solution(N, cards));
    }


    static int solution(int N, ArrayList<Card> cards) {
        int[] dp = new int[N + 1];
        dp[0] = 0;
        dp[1] = cards.get(0).mValue;

        for (int i = 2; i <= N; i++) {
            for (int j = i; j > 0; j--) {
                dp[i] = Math.max(dp[i - j] + cards.get(j-1).mValue, dp[i]);
            }
        }
        return dp[N];
    }

    static class Card {
        private final int mCardCount;
        private final int mValue;

        public Card(int cardCount, int value) {
            mCardCount = cardCount;
            mValue = value;
        }
    }
}
