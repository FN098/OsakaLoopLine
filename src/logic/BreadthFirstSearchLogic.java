package logic;

import java.util.*;

import model.*;

// 幅優先探索
public final class BreadthFirstSearchLogic extends AbstractSearchLogic {
  private Graph graph;
  private final LinkedList<Node> queue = new LinkedList<>(); // BFSに使用するキュー
  private final Set<Node> visited = new HashSet<>(); // 探索済みのノードセット

  @Override
  protected void initialize(Graph graph, Node start, Node goal) {
    this.graph = graph;
    this.queue.clear();
    this.visited.clear();
    queue.add(start); // スタートノードをキューに追加する
    visited.add(start);
  }

  @Override
  protected Node stepNext() {
    if (queue.isEmpty()) {
      throw new IllegalStateException("初期化されていないか、探索するノードがありません。");
    }

    var head = queue.remove(); // キューから先頭のノードを取り出す

    // 隣接するノードが訪問済みでない場合は、キューに追加して訪問済みにする
    graph.findNeighborNodes(head).stream()
      .filter(neighbor -> !visited.contains(neighbor))
      .forEach(neighbor -> {
        queue.add(neighbor);
        visited.add(neighbor);
      });

    return head;
  }
}
