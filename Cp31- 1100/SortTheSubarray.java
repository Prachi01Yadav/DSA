import java.util.*;

public class SortTheSubarray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();
            int[] a = new int[n];
            int[] a_ = new int[n];

            for (int i = 0; i < n; i++) a[i] = sc.nextInt();
            for (int i = 0; i < n; i++) a_[i] = sc.nextInt();

            int start = -1, end = -1;

            for (int i = 0; i < n; i++) {
                if (a[i] != a_[i]) {
                    start = i;
                    break;
                }
            }

            for (int i = n - 1; i >= 0; i--) {
                if (a[i] != a_[i]) {
                    end = i;
                    break;
                }
            }

            if (start == -1) { 
                System.out.println("1 1");
                continue;
            }

            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            for (int i = start; i <= end; i++) {
                min = Math.min(min, a_[i]);
                max = Math.max(max, a_[i]);
            }

            while (start > 0 && a[start - 1] <= min) {
                start--;
                min = Math.min(min, a[start]); 
            }

            while (end < n - 1 && a[end + 1] >= max) {
                end++;
                max = Math.max(max, a[end]);
            }

            System.out.println((start + 1) + " " + (end + 1));
        }
    }
}
