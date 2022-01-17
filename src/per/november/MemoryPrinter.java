package per.november;

public class MemoryPrinter {

    private static Runtime rt = Runtime.getRuntime();

    public static long printHeap(int idx) {
        rt.gc();
        long t = rt.totalMemory();
        long f = rt.freeMemory();
        long u = t - f;
        System.out.printf("%d HEAP:%,8d bytes%n", idx, u);

        return u;
    }
}
