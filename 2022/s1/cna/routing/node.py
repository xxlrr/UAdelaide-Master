class Node:
    """A router node."""

    def __init__(self, name):
        self._name = name
        self._neighbors = {}        # {neighbor: cost}
        self._link_states = {}      # {(node, node): cost}
        self._routing_table = {}    # {destination: (next_hop, cost: cost)}

    def set_neighbor(self, neighbor, cost):
        if cost == -1:
            # -1 means that the link is down
            del self._neighbors[neighbor]
        else:
            self._neighbors[neighbor] = cost
    
    def neighbors_to_string(self):
        str = f"{self._name} Neighbour Table:\n"
        for neighbor, cost in sorted(self._neighbors.items()):
            str += f"{neighbor},{cost}\n"
        str += "\n"
        return str

    def link_states_to_string(self):
        str = f"{self._name} LSDB:\n"
        for (node1, node2), cost in sorted(self._link_states.items()):
            str += f"{node1},{node2},{cost}\n"
        str += "\n"
        return str

    def routing_table_to_string(self):
        str = f"{self._name} Routing Table:\n"
        for dest, (next, cost) in sorted(self._link_states.items()):
            str += f"{dest},{next},{cost}\n"
        str += "\n"
        return str

    def detail_to_string(self):
        return self.neighbors_to_string() \
            + self.link_states_to_string() \
            + self.routing_table_to_string() \

    def __str__(self):
        return self._name


def main():
    """Main control function, used to handle input, output and program flow."""
    
    # reading route nodes from input
    nodes = {}
    while True:
        line = input()
        if line == "LINKSTATE":
            break
        nodes[line] = Node(line)

    # reading link states from input
    while True:
        line = input()
        if line == "UPDATE":
            break
        fields = line.split()   # fields == (name, neighbor, cost, [router_list])
        nodes[fields[0]].set_neighbor(fields[1], int(fields[2]))
        if len(fields) == 4:
            for name in fields[3].split(","):
                print(nodes[name].detail_to_string(), end="")
        # todo: Dijkstra()

    # update link states
    while True:
        line = input()
        if line == "END":
            break
        fields = line.split()   # fields == (name, neighbor, cost, [router_list])
        if fields[1] not in nodes:
            nodes[line] = Node(fields[1])
        nodes[fields[0]].set_neighbor(fields[1], int(fields[2]))
        if len(fields) == 4:
            for name in fields[3].split(","):
                print(nodes[name].detail_to_string(), end="")
        # todo: Dijkstra()
    

# for debug
if __name__ == "__main__":
    main()
