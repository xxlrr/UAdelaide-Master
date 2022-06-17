import imp
import sys
from time import time


Dijkstra = imp.load_source("Dijkstra", "Dijkstra")
DijkstraNlogN = imp.load_source("DijkstraNlogN", "DijkstraNlogN")


if __name__ == "__main__":

    for n in [10, 20, 30, 40, 50]:
        sys.stdin = open(f'input_{n}.txt', 'r')
        start = time()
        Dijkstra.main()
        etime = time() - start
        sys.stdin.close()
        print(f"Dijkstra {n}: {etime}", end="\t")

        sys.stdin = open(f'input_{n}.txt', 'r')
        start = time()
        DijkstraNlogN.main()
        etime = time() - start
        sys.stdin.close()
        print(f"DijkstraNlogN {n}: {etime}", end="\t")

        print()

