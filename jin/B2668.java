import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class B2668 {

    static int[] num;
    static boolean[] visited;
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        num = new int[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            num[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i <= n; i++) {
            visited[i] = true;
            dfs(i, i);
            visited[i] = false;
        }

        System.out.println(list.size());
        for (int i = 0; i < list.size(); i++) {
          System.out.println(list.get(i));
        }

    }

    public static void dfs(int start, int tmp) {
        if (!visited[num[start]]) {
            visited[num[start]] = true;
            dfs(num[start], tmp);
            visited[num[start]] = false;
        }
        if (tmp == num[start]) {
          list.add(tmp);
        }
    }
}
