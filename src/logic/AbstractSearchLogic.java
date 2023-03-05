package logic;

import java.util.*;

import model.*;

public abstract class AbstractSearchLogic implements SearchLogic {

  @Override
  public SearchResult search(Graph graph, Object from, Object to) {
    var start = graph.findNodeByValue(from);
    var goal = graph.findNodeByValue(to);
    if (Objects.isNull(start) || Objects.isNull(goal)) {  // ノードが見つからなければ探索終了
      return new SearchResult(graph, from, to, Route.empty(), History.empty());
    }
    
    initialize(graph, start, goal);

    Node head = null;
    var visited = new ArrayList<Node>();
    while (!Objects.equals(head, goal)) { // ゴールが見つかるまで探索を続ける
      head = stepNext(); // 次のノードを探索
      visited.add(head); // 訪問リストに追加
    }

    var route = createRoute(graph, start, goal, visited);
    var history = new History(visited);
    return new SearchResult(graph, from, to, route, history);
  }
  
  /**
   * グラフの探索状態を初期化します。
   * 
   * @param graph 探索対象のグラフ
   * @param start 探索開始ノード
   * @param goal 探索終了ノード
   */
  protected abstract void initialize(Graph graph, Node start, Node goal);

  /**
   * 次のノードを探索します。
   * 
   * @return 訪問したノード
   */
  protected abstract Node stepNext();

  /**
   * スタートからゴールまでのルートを作成します。
   * 
   * @param graph 探索対象のグラフ
   * @param start 探索開始ノード
   * @param goal 探索終了ノード
   * @param visited 訪問リスト
   * @return Routeオブジェクト
   */
  private Route createRoute(
      Graph graph, 
      Node start, 
      Node goal,
      List<Node> visited) {

    // 訪問リストを反転（ゴール→スタート）
    var list = new ArrayList<>(visited);
    Collections.reverse(list);

    // ゴールからスタートまでの親を辿る
    Node head = goal;
    var links = new ArrayList<Link>();
    for (var parent : list) {
      var link = graph.findLink(parent, head);
      if (link != null) {
        links.add(link);
        head = parent;
      }
    }
    
    // 経路を反転（スタート→ゴール）
    Collections.reverse(links);
    return new Route(links);
  }
}
