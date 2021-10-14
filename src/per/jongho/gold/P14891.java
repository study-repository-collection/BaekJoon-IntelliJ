package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.LockInfo;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P14891 {

    static final int N = 0;
    static final int S = 1;

    static final int CLOCK = 1;
    static final int REVERSE_CLOCK = -1;

    static Deque<Integer>[] gears = new Deque[5];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= 4; i++) {
            String input = br.readLine();
            gears[i] = new LinkedList<>();
            for (int j = 2; j < 8; j++) {
                gears[i].offerLast(input.charAt(j) - '0');
            }
            for (int j = 0; j < 2; j++) {
                gears[i].offerLast(input.charAt(j) - '0');
            }
        }

        int K = Integer.parseInt(br.readLine()); //회전시킬 횟수
        while (K-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int gearNumber = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            rotateGear(gearNumber, direction);
        }
        System.out.println(answer());

    }


    static int answer() {
        int sum = 0;
        int score = 1;
        for (int i = 1; i <= 4; i++) {
            gears[i].pollLast();
            int gook = gears[i].pollLast();
            if (gook == S) {
                sum += score;
            }
            score *= 2;
        }
        return sum;
    }

    static int getLeft(int number) {
        int count = 0;
        for (Integer value : gears[number]) {
            if (count == 4) {
                return value;
            }
            count++;
        }
        return 0;
    }

    static int getRight(int number) {
        return gears[number].peekFirst();
    }

    static void rotateGear(int number, int direction) {
        int[] directions = new int[5];

        directions[number] = direction;
        for (int i = number - 1; i > 0; i--) {
            int currentLeft = getLeft(i + 1);
            if (getRight(i) != currentLeft) {
                directions[i] = directions[i + 1] == CLOCK ? REVERSE_CLOCK : CLOCK;
            } else {
                break;
            }
        }

        for (int i = number + 1; i <= 4; i++) {
            int currentRight = getRight(i - 1);
            if (getLeft(i) != currentRight) {
                directions[i] = directions[i - 1] == CLOCK ? REVERSE_CLOCK : CLOCK;
            } else {
                break;
            }
        }

        for (int i = 1; i <= 4; i++) {
            switch (directions[i]) {
                case CLOCK:
                    int last = gears[i].pollLast();
                    gears[i].offerFirst(last);
                    break;
                case REVERSE_CLOCK:
                    int first = gears[i].pollFirst();
                    gears[i].offerLast(first);
                    break;
            }
        }
    }
}
