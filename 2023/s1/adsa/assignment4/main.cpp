#include <iostream>
#include <vector>
#include <algorithm>
#include <numeric>

using namespace std;

struct Edge {
    int u, v, cost;
    Edge(int _u, int _v, int _cost) : u(_u), v(_v), cost(_cost) {}
};

bool compareEdges(const Edge& e1, const Edge& e2) {
    return e1.cost < e2.cost;
}

int findRoot(int city, vector<int>& parent) {
    if (parent[city] != city)
        parent[city] = findRoot(parent[city], parent);
    return parent[city];
}

int reconstructRoadNetwork(const vector<string>& country, const vector<string>& build, const vector<string>& destroy) {
    int N = country.size();
    vector<Edge> edges;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            if (country[i][j] == '1') {
                edges.emplace_back(i, j, destroy[i][j]);
            } else {
                edges.emplace_back(i, j, build[i][j]);
            }
        }
    }
    sort(edges.begin(), edges.end(), compareEdges);

    vector<int> parent(N);
    iota(parent.begin(), parent.end(), 0);

    int totalCost = 0;
    int edgeCount = 0;

    for (const Edge& edge : edges) {
        int rootU = findRoot(edge.u, parent);
        int rootV = findRoot(edge.v, parent);

        if (rootU != rootV) {
            parent[rootU] = rootV;
            totalCost += edge.cost;
            edgeCount++;
        }

        if (edgeCount == N - 1)
            break;
    }

    return totalCost;
}

vector<string> splitString(const string& input, char delimiter) {
    vector<string> parts;
    string part;
    for (char c : input) {
        if (c == delimiter) {
            parts.push_back(part);
            part.clear();
        } else {
            part += c;
        }
    }
    parts.push_back(part);
    return parts;
}

int main() {
    string countryInput, buildInput, destroyInput;
    cin >> countryInput >> buildInput >> destroyInput;
    vector<string> country = splitString(countryInput, ',');
    vector<string> build = splitString(buildInput, ',');
    vector<string> destroy = splitString(destroyInput, ',');

    int minimalCost = reconstructRoadNetwork(country, build, destroy);

    cout << minimalCost << endl;

    return 0;
}
