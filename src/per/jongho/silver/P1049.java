package per.jongho.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1049 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());
        ArrayList<Integer> setPrice = new ArrayList<>();
        ArrayList<Integer> individualPrice = new ArrayList<>();
        //브랜드 초기화
        while (M-- > 0) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            setPrice.add(Integer.parseInt(input.nextToken()));
            individualPrice.add(Integer.parseInt(input.nextToken()));
        }
        System.out.println(solution(N, setPrice, individualPrice));
    }

    static int solution(int N, ArrayList<Integer> setPrices, ArrayList<Integer> individualPrices) {
        int minSetPrice = setPrices.stream().min(Integer::compareTo).get();
        int minIndividualPrice = individualPrices.stream().min(Integer::compareTo).get();

        int a = N / 6;
        int b = N % 6;

        return Math.min(a * minSetPrice + Math.min(b * minIndividualPrice, minSetPrice), N * minIndividualPrice);
    }

}
