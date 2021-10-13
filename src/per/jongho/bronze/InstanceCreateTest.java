package per.jongho.bronze;

import java.io.IOException;
import java.util.Date;

public class InstanceCreateTest {
    public static void main(String[] args) throws IOException {
        Date startTime = new Date();

// 수행작업
        for (int i = 0; i < 1000000; i++) new Test();

// End time
        Date endTime = new Date();

// Time Check
        long time = endTime.getTime() - startTime.getTime();
        System.out.println("TIME : " + time + "(ms)");

    }

    static class Test {

    }
}
