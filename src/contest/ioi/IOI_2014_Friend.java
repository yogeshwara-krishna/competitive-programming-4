package contest.ioi;

import java.util.*;
import java.io.*;

public class IOI_2014_Friend {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int[] confidence, host, protocol;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int n = readInt();
		confidence = new int[n];
		host = new int[n];
		protocol = new int[n];
		for (int i = 0; i < n; i++)
			confidence[i] = readInt();
		for (int i = 0; i < n; i++) {
			host[i] = readInt();
			protocol[i] = readInt();
		}
		for (int i = n-1; i >= 0; i--) {
		}
		
		out.close();
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

