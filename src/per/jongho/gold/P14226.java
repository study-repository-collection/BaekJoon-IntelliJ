package per.jongho.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import static java.lang.System.in;

public class P14226 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        int S = Integer.parseInt(br.readLine());
        System.out.println(solution(S));
    }

    static final int INF = 987654321;

    static int solution(int S) {
        boolean[][] visited = new boolean[2 * S + 1][S + 1];
        Queue<Model> queue = new LinkedList<>();
        visited[1][0] = true;
        int count = 0;
        queue.add(new Model(1, 0));
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Model model = queue.poll();
                if (model.emojiCount == S) {
                    return count;
                }
                if (model.emojiCount >= 2 && !visited[model.emojiCount - 1][model.clipBoard]) {
                    visited[model.emojiCount - 1][model.clipBoard] = true;
                    queue.add(new Model(model.emojiCount - 1, model.clipBoard));
                }
                if (model.emojiCount <= S && !visited[model.emojiCount][model.emojiCount]) {
                    visited[model.emojiCount][model.emojiCount] = true;
                    queue.add(new Model(model.emojiCount, model.emojiCount));
                }
                if (model.emojiCount + model.clipBoard <= 2 * S && !visited[model.emojiCount + model.clipBoard][model.clipBoard]) {
                    visited[model.emojiCount + model.clipBoard][model.clipBoard] = true;
                    queue.add(new Model(model.emojiCount + model.clipBoard, model.clipBoard));
                }
            }
            count++;
        }
        return count;
    }

    static class Model {
        int emojiCount;
        int clipBoard;

        public Model(int emojiCount, int clipBoard) {
            this.emojiCount = emojiCount;
            this.clipBoard = clipBoard;
        }
    }
}
