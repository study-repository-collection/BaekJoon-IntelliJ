package per.feburary.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P1253 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer input = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(input.nextToken());
        }
        Arrays.sort(arr);
        System.out.println(solution(arr));
    }

    static int solution(int[] arr) {
        int ret = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (j == i) continue;
                if (binarySearch(i, j, arr[i] - arr[j], arr)) {
                    ret++;
                    break;
                }
            }
        }
        return ret;
    }

    static boolean binarySearch(int i, int j, int key, int[] arr) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = (start + end) >>> 1;
            if (arr[mid] == key) {
                if (mid != i && mid != j) {
                    return true;
                } else {
                    int temp = mid - 1;
                    while (temp >= 0 && arr[temp] == key) {
                        if (temp != i && temp != j) {
                            return true;
                        }
                        temp--;
                    }
                    temp = mid + 1;
                    while (temp < arr.length && arr[temp] == key) {
                        if (temp != i && temp != j) {
                            return true;
                        }
                        temp++;
                    }
                    return false;
                }
            } else if (arr[mid] > key) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return false;
    }
}
