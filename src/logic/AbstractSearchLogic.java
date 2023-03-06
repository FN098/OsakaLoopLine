package logic;

import model.*;

public abstract class AbstractSearchLogic implements SearchLogic {
  protected final Graph graph;
  protected final Object from;
  protected final Object to;

  /**
   * グラフ探索オブジェクトを生成します。
   * 
   * @param graph 探索対象のグラフ
   * @param from 出発地
   * @param to 目的地
   */
  public AbstractSearchLogic(Graph graph, Object from, Object to) {
    this.graph = graph;
    this.from = from;
    this.to = to;
  }

  @Override
  public SearchResult execute() {
    initialize();

    do {
      stepNext();
    } while (!isFoundRoute());

    var route = createRoute();
    var history = createHistory();
    return new SearchResult(graph, from, to, route, history);
  }
  
  /**
   * グラフの探索状態を初期化します。
   */
  protected abstract void initialize();

  /**
   * 次のノードを探索します。
   */
  protected abstract void stepNext();

  /**
   * 経路が見つかったかどうか判定します。
   * 
   * @return 経路が見つかっていればtrue
   */
  protected abstract boolean isFoundRoute();

  /**
   * 経路を作成します。
   * 
   * @return Routeオブジェクト
   */
  protected abstract Route createRoute();

  /**
   * 探索履歴を作成します。
   * 
   * @return Historyオブジェクト
   */
  protected abstract History createHistory();
}
