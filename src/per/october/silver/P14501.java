package per.october.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class P14501 {

    static int maxProfit = -1;
    static ArrayList<Consulting> mBruteForceList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        ArrayList<Consulting> consultings = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            consultings.add(new Consulting(i + 1, st.nextToken(), st.nextToken()));
        }
        pretreatment(consultings, N);
        bruteForceSolution(consultings, N, 0, true);
        bruteForceSolution(consultings, N, 0, false);
        System.out.println(maxProfit);
    }

    static void pretreatment(ArrayList<Consulting> consultings, int N) {
        consultings.removeIf(consulting -> consulting.mId + consulting.mDuration > N + 1);
    }

    static void bruteForceSolution(ArrayList<Consulting> consultings, int N, int index, boolean choose) {
        if (index == consultings.size()) {
            int profit = 0;
            for (Consulting consulting : mBruteForceList) {
                profit += consulting.mProfit;
            }
            maxProfit = Math.max(maxProfit, profit);
            return;
        }
        if (choose) {
            if (!isSatisfy(consultings.get(index), N)) {
                return;
            }
            mBruteForceList.add(consultings.get(index));
            bruteForceSolution(consultings, N, index + 1, true);
            bruteForceSolution(consultings, N, index + 1, false);
            mBruteForceList.remove(mBruteForceList.size() - 1);
        } else {
            bruteForceSolution(consultings, N, index + 1, true);
            bruteForceSolution(consultings, N, index + 1, false);
        }
    }

    static boolean isSatisfy(Consulting addTo, int N) {
        if (mBruteForceList.isEmpty()) return addTo.mId + addTo.mDuration <= N + 1;
        Consulting consulting = mBruteForceList.get(mBruteForceList.size() - 1);
        return (consulting.mId + consulting.mDuration <= addTo.mId && addTo.mId + addTo.mDuration <= N + 1);
    }

    static class Consulting {
        private final int mId;
        private final int mDuration;
        private final int mProfit;

        public Consulting(int id, String duration, String profit) {
            mId = id;
            mDuration = Integer.parseInt(duration);
            mProfit = Integer.parseInt(profit);
        }
    }
}
