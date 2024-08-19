import time


def read_hypergraph_data(file_path):
    hyperedges = []
    vertices_degrees = {}
    with open(file_path, 'r') as file:
        for line in file:
            line = line.strip()
            if line:
                vertices = list(map(int, line.split()))
                hyperedges.append(vertices)
                for vertex in vertices:
                    if vertex in vertices_degrees:
                        vertices_degrees[vertex] += 1
                    else:
                        vertices_degrees[vertex] = 1
    return hyperedges, vertices_degrees


def greedy_minimum_vertex_cover(hyperedges, vertices_degrees):
    # 将超边按照未覆盖顶点最多的顺序排序
    sorted_hyperedges = sorted(hyperedges, key=lambda x: -max(vertices_degrees[v] for v in x))
    # 初始化一个空的顶点覆盖集合
    vertex_cover = set()
    # 遍历每条超边，选择能够覆盖最多未覆盖顶点的顶点加入覆盖集合
    for edge in sorted_hyperedges:
        if not any(v in vertex_cover for v in edge):
            # 如果当前超边没有被覆盖，则选择当前超边中度数最高的顶点加入覆盖集合
            max_degree_vertex = max(edge, key=lambda v: vertices_degrees[v])
            vertex_cover.add(max_degree_vertex)
    return vertex_cover


# 函数用于选择要处理的数据集
def choose_dataset():
    dataset_name = input("请输入要处理的数据集名称（例如 'circuit_3' 或 'cryg10000'），输入 '#' 退出： ")
    if dataset_name == "#":
        return None
    elif dataset_name == "circuit_3":
        file_path = 'D://不会编程//algorithm//finalwork//circuit_3.txt'  # 替换为你的 circuit_3 数据集文件路径
    elif dataset_name == "cryg10000":
        file_path = 'D://不会编程//algorithm//finalwork//cryg10000.txt'  # 替换为你的 cryg10000 数据集文件路径
    else:
        print("Unsupported dataset name. Supported datasets are 'circuit_3' and 'cryg10000'.")
        return choose_dataset()  # 递归调用自身，直到输入合法的数据集名称
    return file_path

# 主程序
if __name__ == "__main__":
    while True:
        # 选择要处理的数据集
        file_path = choose_dataset()
        if file_path:
            # 读取数据集并计算顶点度数
            hyperedges, vertices_degrees = read_hypergraph_data(file_path)
            # 记录算法开始时间
            start_time = time.time()
            # 使用贪心算法求解最小顶点覆盖问题
            min_vertex_cover = greedy_minimum_vertex_cover(hyperedges, vertices_degrees)
            # 记录算法结束时间
            end_time = time.time()
            # 计算算法运行时间
            runtime = end_time - start_time
            # 输出结果和运行时间
            print(f"数据集 可以覆盖所有超边的最小顶点集合： {min_vertex_cover}")
            print(f"最小顶点集合中的顶点数量： {len(min_vertex_cover)}")
            print(f"算法运行时间： {runtime:.6f} 秒")
        else:
            print("程序已退出。")
            break