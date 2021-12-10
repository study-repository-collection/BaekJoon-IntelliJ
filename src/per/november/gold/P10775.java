package per.november.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

import static java.lang.System.in;

public class P10775 {
    static ArrayList<Integer> planes = new ArrayList<>();
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int G = Integer.parseInt(br.readLine()); // 게이트의 수
        int P = Integer.parseInt(br.readLine()); // 비행기의 수
        planes.add(-1);
        parent = new int[G + 1];
        for (int i = 0; i <= G; i++) parent[i] = i;
        int count = 0;
        while (P-- > 0) {
            int a = Integer.parseInt(br.readLine());
            int root = find(a);
            if (root == 0) {
                break;
            } else union(a, root - 1);
            count++;
        }
        System.out.println(count);
    }

    static int find(int a) {
        if (a == parent[a]) return a;
        else return parent[a] = find(parent[a]);
    }

    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) {
            return false;
        }
        if (a > b) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }
        return true;
    }
}
