package jin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B15683 {
    static int n,m;
    static int min = Integer.MAX_VALUE;
    static int graph[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n+1][m+1];
        ArrayList<int[]> cctvList = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                if(tmp > 0 && tmp < 6) cctvList.add(new int[]{i, j, tmp});
                graph[i][j] = tmp;
            }
        }

        solution(cctvList, 0, 7);

        System.out.println(min);
    }

    public static void solution(ArrayList<int[]> cctvList, int cctvNum, int viewport) {
        if(cctvNum == cctvList.size()) {
            int cnt = 0;
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= m; j++) {
                    if(graph[i][j] == 0) cnt++;
                }
            }
            min = Math.min(min, cnt);
            return;
        }
        int curY = cctvList.get(cctvNum)[0];
        int curX = cctvList.get(cctvNum)[1];
        int cctvType = cctvList.get(cctvNum)[2];

        int[] dir;
        if(cctvType == 1 || cctvType == 3 || cctvType == 4) {
            for(int i = 0; i < 4 ; i++) {
                if(i == 0) {
                    if(cctvType == 1) dir = new int[]{0};
                    else if(cctvType == 3) dir = new int[]{0,1};
                    else dir = new int[]{3,0,1};
                } else if(i == 1) {
                    if(cctvType == 1) dir = new int[]{1};
                    else if(cctvType == 3) dir = new int[]{1,2};
                    else dir = new int[]{0,1,2};
                } else if(i == 2) {
                    if(cctvType == 1) dir = new int[]{2};
                    else if(cctvType == 3) dir = new int[]{2,3};
                    else dir = new int[]{1,2,3};
                } else {
                    if(cctvType == 1) dir = new int[]{3};
                    else if(cctvType == 3) dir = new int[]{3,0};
                    else dir = new int[]{2,3,0};
                }
                move(curX,curY,viewport,false,dir);
                solution(cctvList, cctvNum + 1, viewport+1);
                move(curX,curY,viewport,true,dir);
            }
        } else if(cctvType == 2) {
            for(int i = 0; i < 2 ; i++) {
                if(i == 0) {
                    dir = new int[]{0,2};
                } else {
                    dir = new int[]{1,3};
                }
                move(curX, curY, viewport, false, dir);
                solution(cctvList, cctvNum + 1, viewport+1);
                move(curX, curY, viewport, true, dir);
            }
        } else {
            move(curX, curY, viewport, false, new int[]{0,1,2,3});
            solution(cctvList, cctvNum + 1, viewport+1);
            move(curX, curY, viewport, true, new int[]{0,1,2,3});
        }
    }

    public static void move(int x, int y, int viewport, boolean isErase, int[] direction) {
        // direction 0 위 1 오른쪽 2 아래 3 왼쪽
        for (int j : direction) {
            int nextX = x;
            int nextY = y;

            while (nextY >= 1 && nextY <= n && nextX >= 1 && nextX <= m) {
                if (j == 0) nextY++;
                else if (j == 1) nextX++;
                else if (j == 2) nextY--;
                else nextX--;

                if (nextX < 1 || nextY < 1 || nextX > m || nextY > n) break;
                if(graph[nextY][nextX] == 6) break;

                if (isErase) {
                    if (graph[nextY][nextX] == viewport) graph[nextY][nextX] = 0;
                } else {
                    if (graph[nextY][nextX] > 0) continue;
                    graph[nextY][nextX] = viewport;
                }
            }
        }
    }

}
