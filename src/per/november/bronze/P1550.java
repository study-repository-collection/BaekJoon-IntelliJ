package per.november.bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import static java.lang.System.in;

public class P1550 {
    static HashMap<Character, Integer> numbers = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String input = br.readLine();
        initHashMap();
        int result = 0;
        int mul = 1;
        for (int i = input.length() - 1; i >= 0; i--) {
            int num = 0;
            if (input.charAt(i) >= '0' && input.charAt(i) <= '9') {
                num = input.charAt(i) - '0';
            } else {
                num = numbers.getOrDefault(input.charAt(i), 0);
            }
            result += num * mul;
            mul *= 16;
        }
        System.out.println(result);
    }

    static void initHashMap() {
        numbers.put('A', 10);
        numbers.put('B', 11);
        numbers.put('C', 12);
        numbers.put('D', 13);
        numbers.put('E', 14);
        numbers.put('F', 15);

    }
}
