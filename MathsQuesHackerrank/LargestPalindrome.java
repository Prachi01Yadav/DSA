import java.util.Scanner;

public class LargestPalindrome {

    static boolean isPalindrome(int n) {
        String s = Integer.toString(n);
        String rev = new StringBuilder(s).reverse().toString();
        return s.equals(rev);
    }

    static int largestPalindromeBelow(int n) {
        int result = 0;

        for (int i = 999; i >= 100; i--) {

            if (i * i <= result) break;

            for (int j = 999; j >= 100; j--) {
                int product = i * j;

                if (product >= n) continue;

                if (product <= result) break;

                if (isPalindrome(product)) {
                    result = product; 
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt(); 

        while (t-- > 0) {
            int n = sc.nextInt();
            System.out.println(largestPalindromeBelow(n));
        }
    }
}