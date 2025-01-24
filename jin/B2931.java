package jin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B2931 {
    static int r,c;
    static char[][] graph;
    static int[] m,z; // 모스크바
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int[] ans = new int[3];
//    static boolean[][] visited;
    static boolean flag = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken()); // 행
        c = Integer.parseInt(st.nextToken()); // 열
        graph = new char[r+1][c+1];
//        visited = new boolean[r+1][c+1];

        for(int i = 1; i <= r; i++) {
            String s = br.readLine();
            for(int j = 1; j <= c; j++) {
                if(s.charAt(j-1) == 'M') {
                    m = new int[] {i, j};
                } else if(s.charAt(j-1) == 'Z') {
                    z = new int[] {i, j};
                }
                graph[i][j] = s.charAt(j-1);
            }
        }

        for(int i = 0; i < 4; i++) {
            int nextX = m[1] + dx[i];
            int nextY = m[0] + dy[i];

            if (nextY > r || nextY < 1 || nextX > c || nextX < 1) {
                continue;
            }
            int curLoc = (i + 2) % 4;
            if(graph[nextY][nextX] != '.' && graph[nextY][nextX] != 'Z') {
                dfs(new int[]{nextY,nextX}, curLoc);
                break;
            }
        }

        System.out.println(ans[0] + " " + ans[1] + " " + graph[ans[0]][ans[1]]);

    }

    //preLoc 0 left 1 up 2 right 4 down
    public static boolean dfs(int[] loc, int preLoc) {
        int curY = loc[0];
        int curX = loc[1];
        char curPipeType = graph[curY][curX];
//        visited[curY][curX] = true;
        if(curPipeType == 'Z') {
//            for(int i = 1; i <= r; i++) {
//                for(int j = 1; j <= c; j++) {
//                    if(graph[i][j] == 'M' || graph[i][j] == 'Z' || graph[i][j] == '.') continue;
//                    if(!visited[i][j]) return false;
//                }
//            }
            return true;
        }

        int nextDir = checkDir(preLoc, curPipeType);
        int nextX = curX + dx[nextDir];
        int nextY = curY + dy[nextDir];
        if (nextY > r || nextY < 1 || nextX > c || nextX < 1) {
            return false;
        }
        char nextPipeType = graph[nextY][nextX];
        int curLoc = (nextDir + 2) % 4;
        if (checkDir(curLoc, nextPipeType) == -1) {
            return false;
        }

        if(!flag && nextPipeType == '.') {
            ArrayList<Character> pipes = new ArrayList();
            if(curLoc == 0) {
                pipes.add('-');
                pipes.add('+');
                pipes.add('3');
                pipes.add('4');
            } else if(curLoc == 1) {
                pipes.add('|');
                pipes.add('+');
                pipes.add('2');
                pipes.add('3');
            } else if(curLoc == 2) {
                pipes.add('-');
                pipes.add('+');
                pipes.add('1');
                pipes.add('2');
            } else  {
                pipes.add('|');
                pipes.add('+');
                pipes.add('1');
                pipes.add('4');
            }
            flag = true;
            for(char c : pipes) {
                graph[nextY][nextX] = c;
                if(dfs(new int[] {nextY,nextX}, curLoc)) {
                    ans[0] = nextY;
                    ans[1] = nextX;
                    return true;
                }
            }
            return false;

        } else {
            return dfs(new int[] {nextY,nextX}, curLoc);
        }

    }

    //preLoc 0 left 1 up 2 right 3 down
    public static int checkDir(int preLoc, char curPipeType) {
        if(preLoc == 0) {
            if(curPipeType == '-' || curPipeType == '+') {
                return 2;
            } else if(curPipeType == '3') {
                return 1;
            } else if(curPipeType == '4') {
                return 3;
            }
        } else if(preLoc == 1) {
            if(curPipeType == '|' || curPipeType == '+') {
                return 3;
            } else if(curPipeType == '2') {
                return 2;
            } else if(curPipeType == '3') {
                return 0;
            }
        } else if(preLoc == 2) {
            if(curPipeType == '-' || curPipeType == '+') {
                return 0;
            } else if(curPipeType == '1') {
                return 3;
            } else if(curPipeType == '2') {
                return 1;
            }
        } else if(preLoc == 3) {
            if(curPipeType == '|' || curPipeType == '+') {
                return 1;
            } else if(curPipeType == '1') {
                return 2;
            } else if(curPipeType == '4') {
                return 0;
            }
        }
        if (curPipeType == '.' && !flag) {
            return 0;
        } else if (curPipeType == 'Z') {
            return 0;
        }
        return -1;
    }

}
