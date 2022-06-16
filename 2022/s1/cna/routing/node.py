class Node:
    
    def __init__(self, name):
        self._name = name
        self._neighbors = {}        # {neighbor: cost}
        self._link_states = {}      # {(node, node): cost}
        self._routing_table = {}    # {destination: (next_hop, cost: cost)}

    def set_neighbor(self, neighbor, cost):
        # -1 means that the link is down
        if cost == -1:
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
        for nodes, cost in sorted(self._link_states.items()):
            str += f"{nodes[0]},{nodes[1]},{cost}\n"
        str += "\n"
        return str

    def routing_table_to_string(self):
        str = f"{self._name} Routing Table:\n"
        for dest, next in sorted(self._link_states.items()):
            str += f"{dest},{next[0]},{next[1]}\n"
        str += "\n"
        return str

    def detail_to_string(self):
        return self.neighbors_to_string() \
            + self.link_states_to_string() \
            + self.routing_table_to_string() \

    def __str__(self):
        return self._name


# for debug
if __name__ == "__main__":
    node = Node("D")
    node.set_neighbor("C", 1)
    node.set_neighbor("B", 2)
    node.set_neighbor("A", 3)
    print(node.neighbors_to_string(), end="")
    print(node.link_states_to_string(), end="")
    print(node.routing_table_to_string(), end="")
    print(node.detail_to_string(), end="")
