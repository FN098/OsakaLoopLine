package logic;

import java.util.*;

import model.*;

public final class BreadthFirstSearchLogic extends AbstractSearchLogic {
  private final Graph graph;
  private final Node start;
  private final Node goal;
  private final LinkedList<Node> queue = new LinkedList<>(); // BFSに使用するキュー
  private final List<Node> visited = new ArrayList<>(); // 探索済みノードのリスト
  private final Map<Node, Node> parents = new HashMap<>();  // 親ノードのマップ
  private boolean isFinished = false;

  public BreadthFirstSearchLogic(SearchCondition condition) {
    super(condition);

    graph = condition.getGraph();
    var from = condition.getFrom();
    var to = condition.getTo();
    start = graph.findNodeByValue(from).orElse(null);
    goal = graph.findNodeByValue(to).orElse(null);
    if (start == null || goal == null) {
      throw new IllegalArgumentException("Start or goal node is not found.");
    }
  }

  @Override
  protected void initialize() {
    queue.clear();
    visited.clear();
    parents.clear();
    isFinished = false;
    
    queue.add(start); // スタートノードをキューに追加する
    visited.add(start);
  }

  @Override
  protected void stepNext() {
    if (isFinished) {
      throw new IllegalStateException("Search is already finished.");
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
  protected boolean isFoundRoute() {
    return isFinished;
  }

  @Override
  protected Route createRoute() {
    if (!isFinished) {
      throw new IllegalStateException("Search is not finished.");
    }

    var links = new ArrayList<Link>();

    // ゴールからスタートまでの親を辿る
    Node head = goal;
    while (!Objects.equals(head, start)) {
      var parent = parents.get(head);
      var link = graph.findLink(parent, head).get();
      links.add(link);
      head = parent;
    }
    
    // 経路を反転
    Collections.reverse(links);

    return new Route(links);
  }

  @Override
  protected History createHistory() {
    if (!isFinished) {
      throw new IllegalStateException("Search is not finished.");
    }
    return new History(visited);
  }
}
