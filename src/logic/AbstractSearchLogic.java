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

    var parents = getParentNodeMap();
    var route = createRoute(graph, start, goal, parents);
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
   * 子ノードをキーとし、親ノードを値とするマップを取得します。
   * 
   * @return 親ノードのマップ
   */
  protected abstract Map<Node, Node> getParentNodeMap();

  /**
   * スタートからゴールまでのルートを作成します。
   * 
   * @param graph 探索対象のグラフ
   * @param start 探索開始ノード
   * @param goal 探索終了ノード
   * @return Routeオブジェクト
   */
  private Route createRoute(
      Graph graph, 
      Node start, 
      Node goal,
      Map<Node, Node> parents) {

    // ゴールからスタートまでの親を辿る
    Node head = goal;
    var links = new ArrayList<Link>();
    while (!Objects.equals(head, start)) {
      var parent = parents.get(head);
      if (Objects.isNull(parent)) {
        throw new IllegalStateException("親ノードが見つかりません");
      }
      var link = graph.findLink(parent, head);
      if (Objects.isNull(link)) {
        throw new IllegalStateException("リンクが見つかりません");
      }
      links.add(link);
      head = parent;
    }
    
    // 経路を反転（スタート→ゴール）
    Collections.reverse(links);
    return new Route(links);
  }
}
