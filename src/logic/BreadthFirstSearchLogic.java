package logic;

import java.util.*;

import model.*;

// 幅優先探索
public final class BreadthFirstSearchLogic extends AbstractSearchLogic {
  private Graph graph;
  private Node start;
  private Node goal;
  private final LinkedList<Node> queue = new LinkedList<>(); // BFSに使用するキュー
  private final List<Node> visited = new ArrayList<>(); // 探索済みノードのリスト
  private final Map<Node, Node> parents = new HashMap<>();  // 親ノードのマップ
  private boolean isFinished;

  @Override
  public void initialize(Graph graph, Node start, Node goal) {
    this.graph = graph;
    this.start = start;
    this.goal = goal;
    this.queue.clear();
    this.visited.clear();
    this.parents.clear();
    isFinished = false;
    
    queue.add(start); // スタートノードをキューに追加する
    visited.add(start);
  }

  @Override
  public void stepNext() {
    if (isFinished) {
      return;
    }

    var head = queue.remove(); // キューから先頭のノードを取り出す

    // ゴールが見つかれば探索終了
    if (head.equals(goal)) {
      isFinished = true;
      return;
    }

    // 隣接するノードが訪問済みでない場合は、キューに追加して訪問済みにする
    graph.findNeighborNodes(head).stream()
      .filter(neighbor -> !visited.contains(neighbor))
      .forEach(neighbor -> {
        queue.add(neighbor);
        visited.add(neighbor);
        parents.put(neighbor, head);
      });
  }

  @Override
  public boolean isFoundRoute() {
    return isFinished;
  }

  @Override
  public Route createRoute() {
    if (!isFinished) {
      return Route.empty();
    }

    var links = new ArrayList<Link>();

    // ゴールからスタートまでの親を辿る
    Node head = goal;
    while (!Objects.equals(head, start)) {
      var parent = parents.get(head);
      assert parent != null;

      var link = graph.findLink(parent, head);
      assert link != null;
      
      links.add(link);
      head = parent;
    }
    
    // 経路を反転
    Collections.reverse(links);

    return new Route(links);
  }

  public History createHistory() {
    if (!isFinished) {
      return History.empty();
    }
    return new History(visited);
  }
}
