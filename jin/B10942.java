import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B10942 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n+1];
        int[][] memo = new int[n+1][n+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=1; i<=n; i++) {
            memo[i][i] = 1;
            if(i+1 > n) continue;
            if(arr[i] == arr[i+1]) {
                memo[i][i+1] = 1;
                memo[i+1][i] = 1;
            }
        }

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                if(j+i > n) continue;
                memo[j][j+i] = arr[j] == arr[j+i] ? ((memo[j+1][j+i-1]) == 1 ? 1 : 0) : 0;
            }
        }

        int m = Integer.parseInt(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i=1; i<=m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            bw.append(memo[a][b] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
