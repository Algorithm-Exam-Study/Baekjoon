package jin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B17281 {
    static int n;
    static int[] order = new int[10];
    static boolean[] visited = new boolean[10];
    static int[][] graph;
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        graph = new int[n+1][10];

        for(int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= 9; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        order[4] = 1;
        dfs(1);

        System.out.println(ans);
    }

    public static void dfs(int depth) {
        if(depth==4) {
            dfs(depth+1);
            return;
        }
        if(depth == 10) {
            gameStart();
            return;
        }

        for (int i = 2; i <= 9; i++) {
            if (visited[i]) continue;
            order[depth] = i;
            visited[i] = true;
            dfs(depth + 1);
            visited[i] = false;
        }

    }
    
    public static void gameStart() {
        int innings = 1;
        int ord = 1;
        int cnt = 0;

        while(innings <= n) {
            int outCount = 0;
            int[] ru = new int[4]; // 루

            while(outCount < 3) {
                int type = graph[innings][order[ord]];
                if(type == 0) {
                    outCount++;
                } else if(type == 4) {
                    cnt++;
                    for(int i = 1; i <= 3; i++) {
                        if(ru[i] == 0) continue;
                        ru[i] = 0;
                        cnt++;
                    }
                } else {
                    int[][] result = letsGo(ru, type);
                    ru = result[0];
                    cnt += result[1][0];
                }

                ord++;
                if(ord == 10) ord = 1;
            }
            innings++;
        }
        ans = Math.max(ans, cnt);
    }

    public static int[][] letsGo(int[] ru, int type) {
        int cnt = 0;
        for(int i=3; i>0; i--) {
            if(ru[i]==1) {
                int temp = i+type;
                if(temp<4) ru[temp] = 1;
                else cnt++;
            }
            ru[i] = 0;
        }
        ru[type] = 1;


        return new int[][]{ru,{cnt}};
    }

}
