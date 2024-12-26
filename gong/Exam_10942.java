package gong;

import java.util.*;
import java.io.*;

/**
팰린드롬?

시간 제한	메모리 제한	제출	정답	맞힌 사람	정답 비율
0.5 초 (하단 참고)	256 MB	63443	19319	13411	30.324%

문제
----
명우는 홍준이와 함께 팰린드롬 놀이를 해보려고 한다.

먼저, 홍준이는 자연수 N개를 칠판에 적는다. 그 다음, 명우에게 질문을 총 M번 한다.

각 질문은 두 정수 S와 E(1 ≤ S ≤ E ≤ N)로 나타낼 수 있으며, 
S번째 수부터 E번째 까지 수가 팰린드롬을 이루는지를 물어보며, 
명우는 각 질문에 대해 팰린드롬이다 또는 아니다를 말해야 한다.

예를 들어, 홍준이가 칠판에 적은 수가 1, 2, 1, 3, 1, 2, 1라고 하자.

S = 1, E = 3인 경우 1, 2, 1은 팰린드롬이다.

S = 2, E = 5인 경우 2, 1, 3, 1은 팰린드롬이 아니다.

S = 3, E = 3인 경우 1은 팰린드롬이다.

S = 5, E = 7인 경우 1, 2, 1은 팰린드롬이다.

자연수 N개와 질문 M개가 모두 주어졌을 때, 명우의 대답을 구하는 프로그램을 작성하시오.


입력
----
첫째 줄에 수열의 크기 N (1 ≤ N ≤ 2,000)이 주어진다.

둘째 줄에는 홍준이가 칠판에 적은 수 N개가 순서대로 주어진다. 칠판에 적은 수는 100,000보다 작거나 같은 자연수이다.

셋째 줄에는 홍준이가 한 질문의 개수 M (1 ≤ M ≤ 1,000,000)이 주어진다.

넷째 줄부터 M개의 줄에는 홍준이가 명우에게 한 질문 S와 E가 한 줄에 하나씩 주어진다.


출력
----
총 M개의 줄에 걸쳐 홍준이의 질문에 대한 명우의 답을 입력으로 주어진 순서에 따라서 출력한다. 

팰린드롬인 경우에는 1, 아닌 경우에는 0을 출력한다.


예제 입력 1 
7
1 2 1 3 1 2 1
4
1 3
2 5
3 3
5 7
예제 출력 1 
1
0
1
1
 */

/**

0	1	2	3	4	5	6
1	2	3	4	5	6	7
--------------------------
1	2	1	3	1	2	1
--------------------------
1	1	1	1	1	1	1

	0	0	0	0	0	0
	
		1	0	1	0	1
		
			0	0	0	0
				
				0	1	0
				
					0	0
						
						1
						
{ 팰린드롬 확인법 : a 번째에서 b 번째 } (a < b)

1. dp[b - a][b - 1];
						
비교시, 항상 첫 번째 수는  { 현재 인덱스 - (현재 비교하는 길이 - 1) }

if(만약 현재 비교 인덱스의 수와 첫 번째 수가 같다면), 

(현재 비교하는 길이 - 2) == 0 이라면, 1 -> 길이가 2 일때만 해당 

or (dp[y - 2][x - 1] == 1) 이라면, 1


길이 1은 전부 같으므로 1 처리

길이 2

						
S = 1, E = 3인 경우 1, 2, 1은 팰린드롬이다.

S = 2, E = 5인 경우 2, 1, 3, 1은 팰린드롬이 아니다.

S = 3, E = 3인 경우 1은 팰린드롬이다.

S = 5, E = 7인 경우 1, 2, 1은 팰린드롬이다.
 */

public class Exam_10942 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 기존 배열의 총 길이 
		int totalLength = Integer.parseInt(br.readLine());
		
		int[] arr = new int[totalLength + 1];
		
		// 배열에 들어갈 내용 토큰화 
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		for(int i = 1; i < totalLength + 1; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[totalLength + 1][totalLength + 1];
		
		for(int i = 1; i < totalLength + 1; i++)
			dp[1][i] = 1;
		
		// 길이 2 부터 시작 
		for(int len = 2; len < totalLength + 1; len++) {
			int sX = len;
			int sY = len;
			
			for(int i = sX; i < totalLength + 1; i++) {
				// 지금부터 현재 인덱스는 sY, i 가 된다.
				int currValue = arr[i];
				// 첫 번째 수
				int firstValue = arr[i - (len - 1)];
				
				if(currValue == firstValue) {
					// 길이비교 2 일때만 해당
					if(len - 2 == 0) {
						dp[sY][i] = 1;
						continue;
					}
					
					if(dp[sY - 2][i - 1] == 1)
						dp[sY][i] = 1;
				}
			}
		}
		
		// 질문 수 
		int question = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < question; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			sb.append(dp[b - a + 1][b]).append("\n");
		}
		
		System.out.println(sb.toString());
	}

}
