package per.october.bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P13458 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] room = new int[N];
        StringTokenizer roomInfo = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) room[i] = Integer.parseInt(roomInfo.nextToken());

        StringTokenizer supervisor = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(supervisor.nextToken()); //총 감독관 감시 인원수
        int C = Integer.parseInt(supervisor.nextToken()); //부감독관 감시 인원수
        long sum = 0;
        for (int i = 0; i < N; i++) sum += solution(room[i], B, C);
        System.out.println(sum);
    }

    static int solution(int number, int B, int C) {
        int count = 0;
        count = 1;
        number -= B;
        if (number <= 0) return count;
        count += Math.ceil((double) number / C);
        return count;
    }
}
