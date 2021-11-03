package per.october.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P2258 {

    static ArrayList<Meat> meats = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //덩어리의 개수
        int M = Integer.parseInt(st.nextToken());//필요한 고기의 양

        for (int i = 0; i < N; i++) {
            StringTokenizer meatInfo = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(meatInfo.nextToken());
            int price = Integer.parseInt(meatInfo.nextToken());
            meats.add(new Meat(weight, price));
        }
        meats.sort(Comparator.comparingInt(o -> o.price));

        int currentSum = 0;
        for (int i = 0; i < meats.size(); i++) {
            int sumOfMeat = 0;
            Meat meat = meats.get(i);
            meat.weight += currentSum;
            sumOfMeat = meat.weight;
            while (++i < meats.size() && meats.get(i).price == meat.price) {
                sumOfMeat += meats.get(i).weight;
                meats.get(i).weight += currentSum;
            }
            i--;
            currentSum = sumOfMeat;
        }

        int currentPrice = -1;
        int currentWeight = -1;

    }

    static class Meat {
        int weight;
        final int price;

        public Meat(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }
    }
}
