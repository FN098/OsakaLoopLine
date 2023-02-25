package logic;

import java.util.*;

import model.*;

public abstract class AbstractSearchLogic implements SearchLogic {

  public SearchResult search(Graph graph, Object from, Object to) {
    var start = graph.findNodeByValue(from);
    var goal = graph.findNodeByValue(to);
    if (start != null && goal != null) {
      if (search(graph, start, goal)) {
        var route = createRoute(graph, start, goal);
        return new SearchResult(graph, route, from, to);
      }
    }
    return new SearchResult(graph, Route.empty(), from, to);
  }

  // 子クラスで実装する検索メソッド
  protected abstract boolean search(Graph graph, Node start, Node goal);

  // ゴールからスタートまでの親を辿りルートを作成
  private Route createRoute(Graph graph, Node start, Node goal) {
    var links = new ArrayList<Link>();
    var head = goal;

    while (head != start) {
      var parent = head.getParent();
      if (parent == null) {
        return Route.empty();
      }
      
      var link = graph.findLink(parent, head);
      if (link == null) {
        return Route.empty();
      }

      links.add(link);
      head = parent;
    }

    Collections.reverse(links);
    
    var route = new Route(links);
    return route;
  }
}
