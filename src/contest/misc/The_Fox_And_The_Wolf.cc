#include <bits/stdc++.h>
using namespace std;
int n, m, c, b;
int sx1, sy1, sx2, sy2, ex1, ey1, ex2, ey2;
char g[20][20];
int movex[5] = {0, 0, 0, -1, 1};
int movey[5] = {0, -1, 1, 0, 0};
bool v[20][20][20][20][10][10][10];
struct state {
  int x1, y1, x2, y2, time, b1, b2, c;
  state(int x1, int y1, int x2, int y2, int b1, int b2, int c, int time) {
    this->x1 = x1;
    this->y1 = y1;
    this->x2 = x2;
    this->y2 = y2;
    this->time = time;
    this->b1 = b1;
    this->b2 = b2;
    this->c = c;
  }
};
int main() {
  scanf("%d%d%d%d", &n, &m, &c, &b);
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      scanf(" %c", &g[i][j]);
      if (g[i][j] == 'f') {
        g[i][j] = '.';
        sx1 = i;
        sy1 = j;
      } else if (g[i][j] == 'F') {
        ex1 = i;
        ey1 = j;
      } else if (g[i][j] == 'w') {
        g[i][j] = '.';
        sx2 = i;
        sy2 = j;
      } else if (g[i][j] == 'W') {
        ex2 = i;
        ey2 = j;
      }
    }
  }
  queue<state> q;
  q.push(state(sx1, sy1, sx2, sy2, 0, 0, 0, 0));
  v[sx1][sy1][sx2][sy2][0][0][0] = true;
  while (!q.empty()) {
    state curr = q.front();
    q.pop();
    if (curr.x1 == ex1 && curr.y1 == ey1 && curr.x2 == ex2 && curr.y2 == ey2) {
      printf("%d\n", curr.time);
      return 0;
    }
    for (int i = 0; i < 5; i++) {
      if (i && (curr.c || curr.b1))
        continue;
      for (int j = 0; j < 5; j++) {
        if (j && (curr.c || curr.b2))
          continue;
        int nx1 = curr.x1 + movex[i];
        int ny1 = curr.y1 + movey[i];
        int nx2 = curr.x2 + movex[j];
        int ny2 = curr.y2 + movey[j];
        if (nx1 < 0 || nx1 >= n || ny1 < 0 || ny1 >= m || nx2 < 0 || nx2 >= n || ny2 < 0 ||
            ny2 >= m || g[nx1][ny1] == 'T' || g[nx2][ny2] == 'T' || g[nx1][ny1] == 'W' ||
            g[nx2][ny2] == 'F')
          continue;

        int nb1 = curr.b1 ? curr.b1 - 1 : 0;
        int nb2 = curr.b2 ? curr.b2 - 1 : 0;
        int nc = curr.c ? curr.c - 1 : 0;

        if (!curr.b1 && g[curr.x1][curr.y1] == 'B' && g[nx1][ny1] == '.')
          nb1 = b;
        if (!curr.b2 && g[curr.x2][curr.y2] == 'B' && g[nx2][ny2] == '.')
          nb2 = b;
        if (!curr.c && ((nx1 == nx2 && ny1 == ny2) ||
                           (curr.x1 == nx2 && curr.y1 == ny2 && curr.x2 == nx1 && curr.y2 == ny1)))
          nc = c;
        if (v[nx1][ny1][nx2][ny2][nb1][nb2][nc])
          continue;
        v[nx1][ny1][nx2][ny2][nb1][nb2][nc] = true;
        q.push(state(nx1, ny1, nx2, ny2, nb1, nb2, nc, curr.time + 1));
      }
    }
  }
  printf("-1");
}
