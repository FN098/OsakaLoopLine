package logic;

import model.*;

public interface SearchLogic {
  /**
   * 出発地と目的地を指定してグラフを探索します。
   * 
   * @param graph 探索対象のグラフ
   * @param from 出発地
   * @param to 目的地
   * @return 探索結果
   */
  public SearchResult execute(Graph graph, Object from, Object to);
}
