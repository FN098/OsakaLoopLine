package logic;

import java.util.*;

import model.*;

// 幅優先探索
public final class BreadthFirstSearchLogic extends AbstractSearchLogic {

  @Override
  protected boolean search(Graph graph, Node start, Node goal) {
    var queue = new LinkedList<Node>(); // BFSに使用するキュー
    queue.add(start); // スタートノードをキューに追加する
    start.setIsVisited(true);

    var step = 0;
    while (!queue.isEmpty()) {
      var head = queue.remove(); // キューから先頭のノードを取り出す
      head.setStep(++step);
      if (head.equals(goal)) {
        return true;  // ゴールが見つかったら探索終了
      }

      var neighbors = graph.findNeighborNodes(head);

      // 隣接するノードが訪問済みでない場合は、キューに追加して訪問済みにする
      neighbors.stream()
        .filter(neighbor -> !neighbor.getIsVisited())
        .forEach(neighbor -> {
          queue.add(neighbor);
          neighbor.setIsVisited(true);
          neighbor.setParent(head);  // 親を設定（ルートを辿るために必要）
        });
    }
    return false;  // 解なし
  }
}
