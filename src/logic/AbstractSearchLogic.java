package logic;

import model.*;

public abstract class AbstractSearchLogic implements SearchLogic {

  @Override
  public SearchResult search(Graph graph, Object from, Object to) {
    var start = graph.findNodeByValue(from);
    var goal = graph.findNodeByValue(to);
    assert start.isPresent() && goal.isPresent();
    
    initialize(graph, start.get(), goal.get());

    do {
      stepNext();
    } while (!isFoundRoute());

    var route = createRoute();
    var history = createHistory();
    return new SearchResult(graph, from, to, route, history);
  }
  
  /**
   * グラフの探索状態を初期化します。
   * 
   * @param graph 探索対象のグラフ
   * @param start 探索開始ノード
   * @param goal 探索終了ノード
   */
  public abstract void initialize(Graph graph, Node start, Node goal);

  /**
   * 次のノードを探索します。
   */
  public abstract void stepNext();

  /**
   * 経路が見つかったかどうか判定します。
   * 
   * @return 経路が見つかっていればtrue
   */
  public abstract boolean isFoundRoute();

  /**
   * 経路を作成します。
   * 
   * @return Routeオブジェクト
   */
  public abstract Route createRoute();

  /**
   * 探索履歴を作成します。
   * 
   * @return Historyオブジェクト
   */
  public abstract History createHistory();
}
