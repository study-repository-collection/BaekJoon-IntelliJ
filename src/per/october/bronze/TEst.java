package per.october.bronze;

import java.io.IOException;

public class TEst {
    public static void main(String[] args) throws IOException {

        int number = 4;
        int result;

        result = multiply(number);


        System.out.println(result);


        System.out.printf("Hello world number is %d");
        System.out.printf("%d", 8);
        int a = 8;
        int b = 12;
        int ret = sum(5, 5);
        System.out.println(ret);
    }

    static int sum(int a, int b) {
        return a + b;
    }

    static int multiply(int a) {
        return 2 * a;
    }
}
