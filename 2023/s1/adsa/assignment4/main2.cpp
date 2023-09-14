#include <iostream>
#include <climits>
#include <vector>
#include <cmath>

using namespace std;

// for test
void display(vector<vector<int>>& a, int ci, int cj){
    for (int i=0; i<ci; i++) {
        for (int j=0; j<cj; j++) {
            cout << a[i][j] << " ";
        }
        cout << endl;
    }
}

int charToCost(char c) {
    if (c >= 'A' && c <= 'Z') {
        return c - 'A';
    } else {
        return c - 'a' + 26;
    }
}

// 修建或摧毁道路的函数
int findMinCost(vector<vector<int>>& country, vector<vector<int>>& build, vector<vector<int>>& destroy) {
    // 城市数量
    int n = country.size();

    // 存放建设成本。
    vector<vector<int>> buildCosts(n, vector<int>(n, 0));
    // 存放摧毁成本。
    vector<vector<int>> destroyCosts(n, vector<int>(n, 0));
    // 存放连通各个城市道路的最低成本（最短路径）
    vector<vector<int>> roatCosts(n, vector<int>(n, 0));    
    
    // 1、首先，假设为所有城市建立彼此直连的道路。如果城市i与城市j间已经存在一条直连的道路，建设成本为0，否者为build[i][j]。
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
            // 此时，初始的最低成本与建设成本相同。
            roatCosts[i][j] = buildCosts[i][j] = country[i][j] == 0 ? build[i][j] : 0;
        }
    }

    // 2、然后，使用Floyd-Warshall算法为所有的城市计算最低成本（最短路径）
    for (int k = 0; k < n; ++k) {
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                // 如果通过中间节点k链接i、j两城市的成本（加上摧毁成本）低于直接连接成本，那么我们需要做一些事。
                if ((roatCosts[i][j] > roatCosts[i][k] + roatCosts[k][j] + (country[i][j] == 1 ? destroy[i][j] : 0))) {
                    if (country[i][j] == 1) {
                        // 原本就存在道路，需要记录摧毁成本
                        destroyCosts[i][j] = destroy[i][j];
                        // 将country[i][j]设为0，因为不再需要摧毁第二次。
                        country[i][j] = 0;
                    }
                    // 抹除建筑成本
                    buildCosts[i][j] = 0;
                    // 更新i、j之间的最低成本。
                    roatCosts[i][j] = roatCosts[i][k] + roatCosts[k][j];
                }
            }
        }
    }

    // 计算总成本
    int totalCost = 0;
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
            totalCost += buildCosts[i][j] + destroyCosts[i][j];
        }
    }
    totalCost = totalCost / 2;

    // 输出结果
    return totalCost;
}

int main() {
    string countryStr, buildStr, destroyStr;
    cin >> countryStr >> buildStr >> destroyStr;

    // According to the analysis, the number of cities (n) and the length (L) of countryStr
    // is a quadratic relationship: L=n*n+(n-1).
    // According to the quadratic formula, it can be simplified: n = (Sqrt(4L+5) - 1) / 2.
    int n = (sqrt(4*countryStr.length()+5) - 1) / 2;
    vector<vector<int>> country(n, vector<int>(n, 0));
    vector<vector<int>> build(n, vector<int>(n, 0));
    vector<vector<int>> destroy(n, vector<int>(n, 0));

    // Parse the input strings.
    for (int i = 0, commas=0; i < n; i++, commas++) {
        for (int j = 0; j < n; j++) {
            country[i][j] = countryStr[i * n + j + commas] - '0';
            build[i][j] = charToCost(buildStr[i * n + j + commas]);
            destroy[i][j] = charToCost(destroyStr[i * n + j + commas]);
        }
    }

    // Calculate the min cost.
    int minCost = findMinCost(country, build, destroy);
    cout << minCost << endl;

    return 0;
}
