package jin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// LowerBpund UpperBound
public class B2143 {
    static int a[];
    static int b[];
    static int num;
    static long ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        num = Integer.parseInt(br.readLine());
        ArrayList<Integer> aArr = new ArrayList<Integer>();
        ArrayList<Integer> bArr = new ArrayList<Integer>();

        int n = Integer.parseInt(br.readLine());
        a = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) a[i] = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine());
        b = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) b[i] = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += a[j];
                aArr.add(sum);
            }
        }
        Collections.sort(aArr);

        for (int i = 0; i < m; i++) {
            int sum = 0;
            for (int j = i; j < m; j++) {
                sum += b[j];
                bArr.add(sum);
            }
        }
        Collections.sort(bArr);

        for (int i = 0; i< aArr.size(); i++) {
            int aVal = aArr.get(i);
            int aValSum = UpperBound(aArr, aVal) - LowerBound(aArr, aVal);
            int bValSum = UpperBound(bArr, num-aVal) - LowerBound(bArr, num-aVal);

            ans += (long)aValSum * bValSum;
            if(aValSum > 0 )i += aValSum-1;
        }
        System.out.println(ans);

    }

    public static int LowerBound(ArrayList<Integer> arr, int key) {
        int low = 0;
        int high = arr.size() - 1;

        while(low <= high) {
            int mid = (low + high) / 2;
            if(arr.get(mid) < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return low;
    }

    public static int UpperBound(ArrayList<Integer> arr, int key) {
        int low = 0;
        int high = arr.size() - 1;

        while(low <= high) {
            int mid = (low + high) / 2;
            if(arr.get(mid) <= key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return low;
    }

}
