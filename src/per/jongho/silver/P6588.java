package per.jongho.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;

public class P6588 {
    static boolean[] primeNumbers = new boolean[1000001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        findPrimeNumbers();

        while (true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) break;
            answer(N);
        }
    }

    static void answer(int n) {
        boolean find = false;
        int a = 0;
        int b = 0;
        for (int i = n - 3; i >= n / 2; i -= 2) {
            if (primeNumbers[i] && primeNumbers[n - i]) {
                a = n - i;
                b = i;
                find = true;
                break;
            }
        }

        if (find) {
            System.out.println(n + " = " + a + " + " + b);
        } else {
            System.out.println("Goldbach`s conjecture is wrong.");
        }
    }

    static void findPrimeNumbers() {
        for (int i = 3; i <= 1000000; i += 2) {
            boolean isPrime = true;
            for (int j = 2; j * j <= i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            primeNumbers[i] = isPrime;
        }
    }
}
