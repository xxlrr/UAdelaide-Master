from random import randint

def gen_input(node_num):
    link_num = randint(1, node_num*node_num/2)

    f = open(f"input_{node_num}.txt", "w")

    for n in range(node_num):
        print(f"N{n}", file=f)
    
    print(f"LINKSTATE", file=f)
    links = {}
    while len(links) < link_num:
        (i, j) = sorted([randint(0, node_num-1), randint(0, node_num-1)])
        if i != j and (i, j) not in links:
            links[(i, j)] = randint(1, 50)
            print(f"N{i} N{j} {links[(i, j)]}", file=f)
    print("END", file=f)

    f.close()

if __name__ == '__main__':
    for n in [10, 20, 30, 40, 50]:
        gen_input(n)
