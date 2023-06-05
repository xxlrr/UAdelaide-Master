#include <iostream>
#include <climits>
#include <vector>
#include <cmath>

using namespace std;

const int MAX_CITIES = 3;

int charToCost(char c) {
    if (c >= 'A' && c <= 'Z') {
        return c - 'A';
    } else {
        return c - 'a' + 26;
    }
}

// 修建或摧毁道路的函数
int findMinCost(vector<vector<int>>& country, vector<vector<int>>& rebuild, vector<vector<int>>& destroy) {
    int totalCost = 0;
    int n = country.size();

    // 初始化图的邻接矩阵
    vector<vector<int>> graph(n, vector<int>(n, 0));    

    // 初始化图的邻接矩阵为重建花费，如果每对城市之间已存在路径那么花费设为0
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
            graph[i][j] = country[i][j] == 0 ? rebuild[i][j] : 0;
        }
    }

    // 对于每对城市之间的连接，如果存在更低的摧毁费用，更新邻接矩阵
    // for (int i = 0; i < n; ++i) {
    //     for (int j = 0; j < n; ++j) {
    //         if (destroy[i][j] < rebuild[i][j]) {
    //             graph[i][j] = destroy[i][j];
    //         }
    //     }
    // }

    // 使用Floyd-Warshall算法计算每对城市之间的最短路径
    for (int k = 0; k < n; ++k) {
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (graph[i][k] != INT_MAX && graph[k][j] != INT_MAX && (graph[i][k] + graph[k][j]) < graph[i][j]) {
                    graph[i][j] = graph[i][k] + graph[k][j];
                }
            }
        }
    }

    // 修建或摧毁道路，使每对不同城市之间只存在一条连接路径
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
            for (int k = 0; k < n; ++k) {
                if (i != j && i != k && j != k && graph[i][j] != INT_MAX && graph[i][k] != INT_MAX && graph[k][j] != INT_MAX) {
                    if (graph[i][j] == (graph[i][k] + graph[k][j])) {
                        // 存在其他路径连接，摧毁道路
                        graph[i][j] = INT_MAX;
                    }
                }
            }
        }
    }

    // 计算总成本
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
            if (graph[i][j] != INT_MAX) {
                totalCost += graph[i][j];
            }
        }
    }

    // 输出结果
    return totalCost;
}

void display2(vector<vector<int>>& a, int ci, int cj){
    for (int i=0; i<ci; i++) {
        for (int j=0; j<cj; j++) {
            cout << char(a[i][j]);
        }
        cout << endl;
    }
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
