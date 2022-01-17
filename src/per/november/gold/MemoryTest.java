package per.november.gold;

import per.november.MemoryPrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;

public class MemoryTest {
    public static void main(String[] args) throws IOException {
        MemoryPrinter.printHeap(0);
        StringBuilder a = new StringBuilder("a");
        for (int i = 0; i < 1000 * 500; i++) {
            a.append("a");
        }

        MemoryPrinter.printHeap(1);
        MemoryPrinter.printHeap(2);
        MemoryPrinter.printHeap(3);
    }
}
