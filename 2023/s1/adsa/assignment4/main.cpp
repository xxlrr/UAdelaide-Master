#include <iostream>
#include <climits>
#include <vector>
#include <cmath>

using namespace std;

// for test, display a vector
void display(vector<vector<int>>& a, int ci, int cj){
    for (int i=0; i<ci; i++) {
        for (int j=0; j<cj; j++) {
            cout << a[i][j] << " ";
        }
        cout << endl;
    }
}

// Function to convert character to cost
int charToCost(char c) {
    if (c >= 'A' && c <= 'Z') {
        return c - 'A';
    } else {
        return c - 'a' + 26;
    }
}

// Function to find the minimum cost of building or destroying roads
int findMinCost(vector<vector<int>>& country, vector<vector<int>>& build, vector<vector<int>>& destroy) {
    // Number of cities
    int n = country.size();

    cout << "country" << endl;
    display(country, n, n);

    // Store building costs
    vector<vector<int>> buildCosts(n, vector<int>(n, 0));
    // Store destruction costs
    vector<vector<int>> destroyCosts(n, vector<int>(n, 0));
    // Store the lowest cost (shortest path) for connecting cities with roads
    vector<vector<int>> roadCosts(n, vector<int>(n, 0));    
    
    // 1. First, assume roads are directly connected between all cities.
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
            // If there is already a direct road between city i and city j,
            // the building cost is 0; otherwise, it is build[i][j].
            buildCosts[i][j] = country[i][j] == 0 ? build[i][j] : 0;
            // At this point, the initial lowest cost is equal to the building cost.
            roadCosts[i][j] = buildCosts[i][j];
        }
    }

    cout << "roadCosts" << endl;
    display(roadCosts, n, n);

    // 2. Next, use the Floyd-Warshall algorithm to calculate the lowest 
    //    cost (shortest path) for all cities.
    for (int k = 0; k < n; ++k) {
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i != j && i != k && j != k && roadCosts[i][k] != INT_MAX && roadCosts[k][j] != INT_MAX) {
                    // If the cost of linking cities i and j through intermediate node k (plus the
                    // destruction cost) is lower than or equal to the current connection cost,
                    // then we need to do something.
                    int budget = roadCosts[i][k] + roadCosts[k][j] + (country[i][j] == 1 ? destroy[i][j] : 0);
                    if (budget <= roadCosts[i][j]) {
                        if (country[i][j] == 1) {
                            // If there was already a road, record the destruction cost.
                            destroyCosts[i][j] = destroy[i][j];
                            // Set country[i][j] to 0 because it no longer needs to be destroyed again.
                            country[i][j] = 0;
                        }
                        // Erase the building cost.
                        buildCosts[i][j] = 0;
                        // Set the road cost between i and j to infinity (used to indicate road removal).
                        roadCosts[i][j] = INT_MAX;
                    }
                }
            }
        }
    }

    cout << "===========================" << endl;
    cout << "roadCosts" << endl;
    display(roadCosts, n, n);
    cout << "buildCosts" << endl;
    display(buildCosts, n, n);
    cout << "destroyCosts" << endl;
    display(destroyCosts, n, n);


    // 3. If multiple connecting paths still exist between different pairs of cities,
    //    keep only the unique paths.
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
            for (int k = 0; k < n; ++k) {
                if (i != j && i != k && j != k && roadCosts[i][j] != INT_MAX && roadCosts[i][k] != INT_MAX && roadCosts[k][j] != INT_MAX) {
                    if (roadCosts[i][j] == (roadCosts[i][k] + roadCosts[k][j])) {
                        // If there are other paths connecting the cities, destroy the road.
                        roadCosts[i][j] = INT_MAX;
                        if (country[i][j] == 1) {
                            // If there was already a road, record the destruction cost.
                            destroyCosts[i][j] = destroy[i][j];
                            // Set country[i][j] to 0 because it no longer needs to be destroyed again.
                            country[i][j] = 0;
                        }
                    }
                }
            }
        }
    }

    cout << "===========================" << endl;
    cout << "roadCosts" << endl;
    display(roadCosts, n, n);
    cout << "buildCosts" << endl;
    display(buildCosts, n, n);
    cout << "destroyCosts" << endl;
    display(destroyCosts, n, n);

    // Calculate the total cost
    int totalCost = 0;
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
            totalCost += buildCosts[i][j] + destroyCosts[i][j];
        }
    }
    totalCost = totalCost / 2;

    // Return the result
    return totalCost;
}

int main() {
    string countryStr, buildStr, destroyStr;
    // accept user input.
    cin >> countryStr >> buildStr >> destroyStr;

    // According to the analysis, the number of cities (n) and the length (L) of countryStr
    // is a quadratic relationship: L=n*n+(n-1).
    // According to the quadratic formula, it can be simplified: n = (Sqrt(4L+5) - 1) / 2.
    int n = (sqrt(4*countryStr.length()+5) - 1) / 2;

    // initilize variables required.
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
