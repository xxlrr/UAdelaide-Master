import heapq

def minimal_cost(country, build, destroy):
    N = len(country)  # Number of cities
    graph = [[] for _ in range(N)]  # Adjacency list representation of the graph

    # Build the initial graph based on the existing roads
    for i in range(N):
        for j in range(N):
            if country[i][j] == '1':
                cost = ord(destroy[i][j]) - ord('A') + 1
                graph[i].append((j, cost))

    # Helper function for Dijkstra's algorithm
    def dijkstra(start):
        distances = [float('inf')] * N
        distances[start] = 0
        heap = [(0, start)]

        while heap:
            dist, node = heapq.heappop(heap)

            if dist > distances[node]:
                continue

            for neighbor, cost in graph[node]:
                new_dist = dist + cost

                if new_dist < distances[neighbor]:
                    distances[neighbor] = new_dist
                    heapq.heappush(heap, (new_dist, neighbor))

        return distances

    min_cost = float('inf')

    # Try removing each road and find the minimum cost for each case
    for i in range(N):
        for j in range(N):
            if i != j and country[i][j] == '1':
                cost = ord(build[i][j]) - ord('A') + 1
                country[i] = country[i][:j] + '0' + country[i][j+1:]
                distances = dijkstra(i)
                min_cost = min(min_cost, cost + min(distances))

                # Restore the removed road
                country[i] = country[i][:j] + '1' + country[i][j+1:]

    if min_cost == float('inf'):
        return 0  # No reconstruction needed
    else:
        return min_cost


if __name__ == "__main__":

    user_input = input("input: ")

    fields = user_input.split(" ")
    country = fields[0].split(",")
    build = fields[1].split(",")
    destroy = fields[2].split(",")

    result = minimal_cost(country, build, destroy)
    print(result)
