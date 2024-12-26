package gong;

import java.io.*;
import java.util.*;

/**
 불! 다국어

 시간 제한	메모리 제한	제출	정답	맞힌 사람	정답 비율
 1 초	256 MB	72488	16396	10766	20.773%

 문제
 ---
 지훈이는 미로에서 일을 한다. 지훈이를 미로에서 탈출하도록 도와주자!

 미로에서의 지훈이의 위치와 불이 붙은 위치를 감안해서 지훈이가 불에 타기전에 탈출할 수 있는지의 여부, 그리고 얼마나 빨리 탈출할 수 있는지를 결정해야한다.

 지훈이와 불은 매 분마다 한칸씩 수평또는 수직으로(비스듬하게 이동하지 않는다) 이동한다.

 불은 각 지점에서 네 방향으로 확산된다.

 지훈이는 미로의 가장자리에 접한 공간에서 탈출할 수 있다.

 지훈이와 불은 벽이 있는 공간은 통과하지 못한다.

 입력
 ---
 입력의 첫째 줄에는 공백으로 구분된 두 정수 R과 C가 주어진다. 단, 1 ≤ R, C ≤ 1000 이다. R은 미로 행의 개수, C는 열의 개수이다.

 다음 입력으로 R줄동안 각각의 미로 행이 주어진다.

 각각의 문자들은 다음을 뜻한다.

 #: 벽
 .: 지나갈 수 있는 공간
 J: 지훈이의 미로에서의 초기위치 (지나갈 수 있는 공간)
 F: 불이 난 공간
 J는 입력에서 하나만 주어진다.

 출력
 ---
 지훈이가 불이 도달하기 전에 미로를 탈출 할 수 없는 경우 IMPOSSIBLE 을 출력한다.

 지훈이가 미로를 탈출할 수 있는 경우에는 가장 빠른 탈출시간을 출력한다.

 예제 입력 1
 4 4
 ####
 #JF#
 #..#
 #..#
 예제 출력 1
 3
 */


public class Exam_4179 {
    public static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        List<int[]> firstFires = new ArrayList<>();

        map = new char[R][C];

        int[] firstUser = new int[2]; // y:0, x:1

        for(int i = 0; i < R; i++) {
            String line = br.readLine();
            for(int j = 0; j < C; j++) {
                char ch = line.charAt(j);

                map[i][j] = ch;

                if(ch == 'F'){
                    firstFires.add(new int[] {i, j});
                } else if(ch == 'J') {
                    firstUser[0] = i;
                    firstUser[1] = j;
                }
            }
        }

        Queue<int[]> queue = new LinkedList<>();

        for(int i = 0; i < firstFires.size(); i++)
            queue.add(new int[] {firstFires.get(i)[0], firstFires.get(i)[1], 1, 0});

        queue.add(new int[] {firstUser[0], firstUser[1], 0, 0});

        int resultTime = 0;
        boolean isSuccess = false;

        while(!queue.isEmpty()) {
            int[] info = queue.poll();
            int y = info[0];
            int x = info[1];
            int isFire = info[2];
            int time = info[3];

            if(isFire == 0 && isExit(x, y)){
                resultTime = time + 1;
                isSuccess = true;
                break;
            }


            if(isFire == 1) {
                // 위
                if(y - 1 > 0 && map[y - 1][x] != '#' && map[y - 1][x] != 'F'){
                    queue.add(new int[] {y - 1, x, 1, time + 1});
                    map[y - 1][x] = 'F';
                }

                // 오른쪽
                if(x + 1 < map[0].length && map[y][x + 1] != '#' && map[y][x + 1] != 'F') {
                    queue.add(new int[] {y, x + 1, 1, time + 1});
                    map[y][x + 1] = 'F';
                }

                // 아래
                if(y + 1 < map.length && map[y + 1][x] != '#' && map[y + 1][x] != 'F') {
                    queue.add(new int[] {y + 1, x, 1, time + 1});
                    map[y + 1][x] = 'F';
                }

                // 왼쪽
                if(x - 1 > 0 && map[y][x - 1] != '#' && map[y][x - 1] != 'F') {
                    queue.add(new int[] {y, x - 1, 1, time + 1});
                    map[y][x - 1] = 'F';
                }

            } else { // 지훈이면
                // 위
                if(y - 1 > 0 && map[y - 1][x] != '#' && map[y - 1][x] != 'J' && map[y - 1][x] != 'F' && map[y - 1][x] == '.'){
                    queue.add(new int[] {y - 1, x, 0, time + 1});
                    map[y - 1][x] = 'J';
                }

                // 오른쪽
                if(x + 1 < map[0].length && map[y][x + 1] != '#' &&  map[y][x + 1] != 'J'  && map[y][x + 1] != 'F' && map[y][x + 1] == '.') {
                    queue.add(new int[] {y, x + 1, 0, time + 1});
                    map[y][x + 1] = 'J';
                }

                // 아래
                if(y + 1 < map.length && map[y + 1][x] != '#' &&  map[y + 1][x] != 'J' && map[y + 1][x] != 'F' && map[y + 1][x] == '.') {
                    queue.add(new int[] {y + 1, x, 0, time + 1});
                    map[y + 1][x] = 'J';
                }

                // 왼쪽
                if(x - 1 > 0 && map[y][x - 1] != '#' && map[y][x - 1] != 'J' && map[y][x - 1] != 'F' && map[y][x - 1] == '.') {
                    queue.add(new int[] {y, x - 1, 0, time + 1});
                    map[y][x - 1] = 'J';
                }
            }
        }

        if(isSuccess) {
            System.out.println(resultTime);
        } else {
            System.out.println("IMPOSSIBLE");
        }

    }

    static boolean isExit(int x, int y) {
        if(x == 0 || y == 0) {
            return true;
        } else if(x == map[0].length - 1 || y == map.length - 1) {
            return true;
        } else {
            return false;
        }
    }
}
