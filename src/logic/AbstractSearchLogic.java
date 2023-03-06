package logic;

import model.*;

public abstract class AbstractSearchLogic implements SearchLogic {
  private final SearchCondition condition;

  public AbstractSearchLogic(SearchCondition condition) {
    this.condition = condition;
  }

  @Override
  public SearchResult execute() {
    initialize();

    do {
      stepNext();
    } while (!isFoundRoute());

    var route = createRoute();
    var history = createHistory();
    return new SearchResult(condition, route, history);
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
