/*
 * Using Mo's algorithm to determining the number of distinct numbers in a subsequence.
 * Reference problem: http://www.spoj.com/problems/DQUERY/
 */

package codebook.algorithms;

import java.util.*;
import java.io.*;

public class Mo {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n, m, sz;
	static int[] cnt, a, ans;

	static int res;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		sz = (int) Math.sqrt(n);

		a = new int[n + 1];
		for (int i = 1; i <= n; i++)
			a[i] = readInt();
		cnt = new int[1000001];

		m = readInt();
		Query[] q = new Query[m];
		for (int i = 0; i < m; i++)
			q[i] = new Query(readInt(), readInt(), i);
		ans = new int[m];

		Arrays.sort(q);
		int l = 1, r = 0;
		for (Query query : q) {
			while (r > query.r) {
				remove(a[r]);
				r--;
			}
			while (r < query.r) {
				r++;
				update(a[r]);
			}
			while (l < query.l) {
				remove(a[l]);
				l++;
			}
			while (l > query.l) {
				l--;
				update(a[l]);
			}
			ans[query.index] = res;
		}
		for (int i : ans)
			out.println(i);

		out.close();
	}

	static void update (int i) {
		cnt[i]++;
		if (cnt[i] == 1)
			res++;
	}

	static void remove (int i) {
		cnt[i]--;
		if (cnt[i] == 0)
			res--;
	}

	static class Query implements Comparable<Query> {
		int l, r, index;

		Query (int l, int r, int index) {
			this.l = l;
			this.r = r;
			this.index = index;
		}

		@Override
		public int compareTo (Query o) {
			if ((l - 1) / sz != (o.l - 1) / sz)
				return (l - 1) / sz - (o.l - 1) / sz;
			return r - o.r;
		}
	}

	static String next () throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(br.readLine().trim());
		return st.nextToken();
	}

	static long readLong () throws IOException {
		return Long.parseLong(next());
	}

	static int readInt () throws IOException {
		return Integer.parseInt(next());
	}

	static double readDouble () throws IOException {
		return Double.parseDouble(next());
	}

	static char readCharacter () throws IOException {
		return next().charAt(0);
	}

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
