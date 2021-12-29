package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;

public class P2602 {
    static char[] devilBridge;
    static char[] angelBridge;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        String input = br.readLine();
        String devilBridgeInfo = br.readLine();
        String angelBridgeInfo = br.readLine();

        devilBridge = devilBridgeInfo.toCharArray();
        angelBridge = angelBridgeInfo.toCharArray();

        System.out.println(solution(input.toCharArray()));
    }

    static int solution(char[] answer) {
        int length = answer.length;
        int[][][] dp = new int[devilBridge.length][length][2];
        //첫 번쨰 : 현재 검사 위치
        //두 번쨰 : answer의 위치
        //세 번째 : 천사 다린 지 악마 다린지
        //dp[i][j][k] -> 천사 or 악마 다리의 i 번쨰 자리가 answer 의 j 번쨰 자리라고 가정했을 떄의 경우의 수
        int ret = 0;
        for (int i = 0; i < devilBridge.length; i++) {
            if (devilBridge[i] == answer[0]) {
                dp[i][0][1] = 1;
            }
            if (angelBridge[i] == answer[0]) {
                dp[i][0][0] = 1;
            }
            for (int j = 1; j < length; j++) {
                if (devilBridge[i] == answer[j]) {
                    for (int k = 0; k < i; k++) {
                        dp[i][j][1] += dp[k][j - 1][0];
                    }
                }
                if (angelBridge[i] == answer[j]) {
                    for (int k = 0; k < i; k++) {
                        dp[i][j][0] += dp[k][j - 1][1];
                    }
                }
            }
        }
        for (int i = 0; i < devilBridge.length; i++) {
            ret += dp[i][length-1][0];
            ret += dp[i][length-1][1];
        }
        return ret;
    }
}
