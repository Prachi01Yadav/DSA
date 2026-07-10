import java.util.*;

public class kalindromeArray {

    static boolean ok(List<Long> a, long x) {
        int l = 0, r = a.size() - 1;
        while (l < r) {
            if (a.get(l) == x) l++;
            else if (a.get(r) == x) r--;
            else if (!a.get(l).equals(a.get(r))) return false;
            else { l++; r--; }
        }
        return true;
    }

    static void solve(Scanner sc) {
        int n = sc.nextInt();
        List<Long> a = new ArrayList<>();
        for (int i = 0; i < n; i++) a.add(sc.nextLong());

        int l = 0, r = n - 1;
        while (l < r && a.get(l).equals(a.get(r))) {
            l++; r--;
        }

        if (l >= r || ok(a, a.get(l)) || ok(a, a.get(r)))
            System.out.println("YES");
        else
            System.out.println("NO");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) solve(sc);
    }
}
