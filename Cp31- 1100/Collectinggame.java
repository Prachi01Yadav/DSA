import java.util.*;

public class Collectinggame {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt(); 

		while (t-- > 0) {
			long n = sc.nextLong(); 
			List<Pair> v = new ArrayList<>(); 

			for (int i = 0; i < n; i++) { 
				long a = sc.nextLong();
				v.add(new Pair(a, i));
			}

			long[] pre = new long[(int) n]; 
			Collections.sort(v, Comparator.comparingLong(pair -> pair.first)); 

			pre[0] = v.get(0).first;
			for (int i = 1; i < n; i++) { 
				pre[i] = pre[i - 1] + v.get(i).first;
			}

			long[] ans = new long[(int) n]; 

			
			for (int i = 0; i < n; i++) { 
				int j = i;
				int found = i;
				while (j < n) {
					Pair temp = new Pair(pre[j] + 1, Long.MIN_VALUE);
					int idx = Collections.binarySearch(v, temp, Comparator.comparingLong(pair -> pair.first));
					if (idx < 0) idx = -idx - 1;
					idx--;
					if (idx == j) {
						break;
					}
					found += idx - j;
					j = idx;
				}
				ans[(int) v.get(i).second] = found;
			}

			for (int i = 0; i < n; i++) { 
				System.out.print(ans[i] + " ");
			}
			System.out.println();
		}


   }

	static class Pair {
		long first, second;

		Pair(long first, long second) {
			this.first = first;
			this.second = second;
		}
	}
}
