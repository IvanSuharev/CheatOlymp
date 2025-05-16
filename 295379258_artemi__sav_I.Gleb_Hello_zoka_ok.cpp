#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
#include <set>
#include <sstream>

using namespace std;

#define int long long
typedef long long ll;
typedef long double ld;

int pos = 0;
bool ok = true;
void skip_spaces(const string &s) {
    while (pos < s.size() && s[pos] == ' ') {
        pos++;
    }
}
int parse_inter(const string &s) {
    skip_spaces(s);
    int res = 0;
    int ln = 0;
    bool bl = false;
    if (s[pos] == '0') {
        bl = true;
        pos++;
        skip_spaces(s);
        ln++;
    }
    while (isdigit(s[pos])) {
        if (bl) {
            ok = false;
            return -1;
        }
        ln++;
        res *= 10;
        res += s[pos] - '0';
        pos++;
        skip_spaces(s);
    }
    if (ln == 0 || ln > 10) {
        ok = false;
        return -1;
    }
    return res;
}
int parse_expr(const string &s) {
    int res = parse_inter(s);
    while (s[pos] != '=' && pos < s.size()) {
        skip_spaces(s);
        if (s[pos] == '+') {
            pos++;
            res += parse_inter(s);
        } else if (s[pos] == '-') {
            pos++;
            res -= parse_inter(s);
        } else {
            ok = false;
            return -1;
        }
        if (!ok) return -1;
    }
    return res;
}


bool is_correct(const string &s) {
    ok = true;
    pos = 0;
    int l = parse_expr(s);
    if (!ok) return  false;
    skip_spaces(s);
    pos++;
    skip_spaces(s);
    int r = parse_expr(s);
    // cerr << l << " " << r << ' ' << s << '\n';
    return ok && l == r;
}


void solve() {
    string s;
    cin >> s;
    if (is_correct(s)) {
        cout << "Correct";
        return;
    }
    string cur(s.size() * 2 + 1, ' ');
    int dop = 0;
    for (int i = 1; i < cur.size(); i += 2) {
        cur[i] = s[dop++];
    }

    for (int i = 1; i < cur.size(); i += 2) {
        if (!isdigit(cur[i])) continue;
        char x = cur[i];
        cur[i] = ' ';
        for (int j = 0; j < cur.size(); j += 2) {
            cur[j] = x;
            if (is_correct(cur)) {
                for (int k = 0; k < cur.size(); k++) {
                    if (cur[k] != ' ') {
                        cout << cur[k];
                    }
                }
                return;
            }
            cur[j] = ' ';
        }
        cur[i] = x;
    }
    cout << "Impossible";
}

signed main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
#ifdef LC
    freopen("/Users/Iurii.Zaitsev/TUM/ICPC/y1/nerc_test/input.txt", "r", stdin);
    freopen("/Users/Iurii.Zaitsev/TUM/ICPC/y1/nerc_test/output.txt", "w", stdout);

#endif
    int t = 1;
    // cin >> t;
    for (int tt =0; tt < t; tt++) {
        solve();
    }

    return 0;
}
