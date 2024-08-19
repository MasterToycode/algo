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
    sorted_hyperedges = sorted(hyperedges, key=lambda x: -max(vertices_degrees[v] for v in x))
    vertex_cover = set()
    for edge in sorted_hyperedges:
        if not any(v in vertex_cover for v in edge):
            max_degree_vertex = max(edge, key=lambda v: vertices_degrees[v])
            vertex_cover.add(max_degree_vertex)
    return vertex_cover

def calculate_covered_hyperedges(vertex_set, hyperedges):
    covered_edges = set()
    for edge in hyperedges:
        if any(v in vertex_set for v in edge):
            covered_edges.add(tuple(edge))
    return len(covered_edges)

class BranchBoundNode:
    def __init__(self, solution=None, cost=float('inf'), bound=float('inf'), depth=0):
        self.solution = solution  # 当前节点的解（顶点覆盖集合）
        self.cost = cost  # 当前节点解的代价（覆盖的超边数）
        self.bound = bound  # 当前节点的上界（用于剪枝的参考值）
        self.depth = depth  # 当前节点在搜索树中的深度
def branch_bound_minimum_vertex_cover(hyperedges, vertices_degrees):
    initial_solution = greedy_minimum_vertex_cover(hyperedges, vertices_degrees)
    initial_bound = calculate_covered_hyperedges(initial_solution, hyperedges)
    # 创建初始节点
    initial_node = BranchBoundNode(solution=initial_solution, cost=len(initial_solution), bound=initial_bound, depth=0)
    # 初始化最佳解和其对应的代价
    best_solution = initial_node.solution
    best_cost = initial_node.cost
    # 初始化节点列表，将初始节点放入列表中
    nodes = [initial_node]
    # 开始分支定界算法的主循环，直到节点列表为空
    while nodes:
        # 取出当前节点
        current_node = nodes.pop(0)
        # 如果当前节点的上界小于当前最优解的代价，则剪枝，继续下一个节点
        if current_node.bound < best_cost:
            continue
        # 如果当前节点的深度小于顶点度数的长度，则继续扩展
        if current_node.depth < len(vertices_degrees):
            # 获取当前深度对应的顶点
            vertex = list(vertices_degrees.keys())[current_node.depth]
            # 探索包含该顶点的情况
            included_solution = current_node.solution.copy()
            included_solution.add(vertex)  # 将该顶点加入当前解中
            included_bound = calculate_covered_hyperedges(included_solution, hyperedges)  # 计算新解的覆盖超边数
            included_node = BranchBoundNode(solution=included_solution, cost=len(included_solution),
                                            bound=included_bound, depth=current_node.depth + 1)
            # 更新最优解和最优代价
            if included_node.cost < best_cost:
                best_solution = included_node.solution
                best_cost = included_node.cost
            # 如果新解的上界大于等于当前最优代价，则将其加入节点列表继续扩展
            if included_node.bound >= best_cost:
                nodes.append(included_node)
            # 探索不包含该顶点的情况
            excluded_solution = current_node.solution.copy()
            excluded_bound = calculate_covered_hyperedges(excluded_solution, hyperedges)  # 计算不包含该顶点的覆盖超边数
            excluded_node = BranchBoundNode(solution=excluded_solution, cost=len(excluded_solution),
                                            bound=excluded_bound, depth=current_node.depth + 1)
            # 更新最优解和最优代价
            if excluded_node.cost < best_cost:
                best_solution = excluded_node.solution
                best_cost = excluded_node.cost
            # 如果新解的上界大于等于当前最优代价，则将其加入节点列表继续扩展
            if excluded_node.bound >= best_cost:
                nodes.append(excluded_node)
    return best_solution

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
        return choose_dataset()
    return file_path


# 主程序
if __name__ == "__main__":
    while True:
        file_path = choose_dataset()
        if file_path:
            hyperedges, vertices_degrees = read_hypergraph_data(file_path)
            start_time = time.time()
            min_vertex_cover = branch_bound_minimum_vertex_cover(hyperedges, vertices_degrees)
            end_time = time.time()
            runtime = end_time - start_time
            print(f"数据集 可以覆盖所有超边的最小顶点集合： {min_vertex_cover}")
            print(f"最小顶点集合中的顶点数量： {len(min_vertex_cover)}")
            print(f"算法运行时间： {runtime:.6f} 秒")
        else:
            print("程序已退出。")
            break
