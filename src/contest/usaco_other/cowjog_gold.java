package contest.usaco_other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/*
ID: jeffrey40
LANG: JAVA
TASK: cowjog
 */
import java.util.ArrayList;
import java.util.StringTokenizer;

public class cowjog_gold {
  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new FileReader("cowjog.in"));
    // br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new BufferedWriter(new FileWriter("cowjog.out")));

    int n = readInt();
    int t = readInt();

    Cow[] c = new Cow[n];
    for (int x = 0; x < n; x++)
      c[x] = new Cow(readDouble(), readDouble());
    ArrayList<Cow> states = new ArrayList<Cow>();

    for (int x = 0; x < n; x++) {
      int y;
      for (y = 0; y < states.size(); y++) {
        if (states.get(y).speed <= c[x].speed) {
          states.set(y, c[x]);
          break;
        } else if (states.get(y).speed > c[x].speed && (c[x].pos - states.get(y).pos) / (states.get(y).speed - c[x].speed) > t) {
          states.set(y, c[x]);
          break;
        }
      }
      if (y == states.size()) {
        states.add(c[x]);
      }
    }
    pr.println(states.size());

    pr.close();
    System.exit(0);
  }

  static String next() throws IOException {
    while (st == null || !st.hasMoreTokens())
      st = new StringTokenizer(br.readLine().trim());
    return st.nextToken();
  }

  static long readLong() throws IOException {
    return Long.parseLong(next());
  }

  static int readInt() throws IOException {
    return Integer.parseInt(next());
  }

  static double readDouble() throws IOException {
    return Double.parseDouble(next());
  }

  static String readLine() throws IOException {
    return br.readLine().trim();
  }

  static class Cow {
    double pos, speed;

    Cow(double pos, double speed) {
      this.pos = pos;
      this.speed = speed;
    }
  }
}
