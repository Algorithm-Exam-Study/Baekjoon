package jin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B4179 {
    static int dx[] = {0,1,0,-1};
    static int dy[] = {1,0,-1,0};
    static char graph[][];
    static int n;
    static int m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new char[n+1][m+1];
        int startX = 0;
        int startY = 0;
        ArrayList<int[]> fireLoc = new ArrayList<int[]>();

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            for (int j = 1; j <= m; j++) {
                if(str.charAt(j-1) == 'J') {
                    startX = j;
                    startY = i;
                } else if (str.charAt(j-1) == 'F') {
                    fireLoc.add(new int[]{j, i, 0, 1});
                }

                graph[i][j] =  str.charAt(j-1);
            }
        }

        int time = bfs(startX, startY, fireLoc);
        if(time == 0) System.out.println("IMPOSSIBLE");
        else System.out.println(time);

    }

    public static int bfs(int startX, int startY, ArrayList<int[]> fireLoc) {
        Queue<int[]> q = new LinkedList<>();
        for(int i = 0; i<fireLoc.size(); i++) q.offer(fireLoc.get(i));
        q.offer(new int[] {startX,startY,1,1}); // 1 은 지훈

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            int isFire = cur[2];// 0 = 불, 1= 지훈
            int time = cur[3];

            for(int i=0; i<4; i++) {
                int nx = curX + dx[i];
                int ny = curY + dy[i];

                if(isFire == 0) {
                    if(nx == 0 || nx > m || ny == 0 ||ny > n) continue;
                    if(graph[ny][nx] == '#' || graph[ny][nx] == 'J' || graph[ny][nx] == 'F') continue;
                    graph[ny][nx] = 'F';
                } else {
                    if(nx == 0 || nx > m || ny == 0 ||ny > n) {
                        return time;
                    }
                    if(graph[ny][nx] == '#' || graph[ny][nx] == 'J' || graph[ny][nx] == 'F') continue;
                    graph[ny][nx] = 'J';
                }

                q.add(new int[] {nx,ny,isFire,time+1});

            }
        }
        return 0;
    }
}