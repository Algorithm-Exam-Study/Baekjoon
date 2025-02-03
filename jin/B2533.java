package jin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B2533 {
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static int dp[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n+1];
        visited = new boolean[n+1];
        dp = new int[n+1][2];

        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        StringTokenizer st;
        for(int i=0; i<n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        dfs(1);

        System.out.println(Math.min(dp[1][0], dp[1][1]));

    }
    public static void dfs(int start) {
        visited[start] = true;
        dp[start][0] = 1;
        dp[start][1] = 0;
        for(int node : graph[start]) {
            if(visited[node]) continue;
            dfs(node);
            dp[start][0] += Math.min(dp[node][0],dp[node][1]);
            dp[start][1] += dp[node][0];
        }

    }

}
