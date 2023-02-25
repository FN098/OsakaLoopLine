package model;

public final class SearchResult {
  private final Graph graph;
  private final Route route;
  private final Object from;
  private final Object to;

  public SearchResult(Graph graph, Route route, Object from, Object to) {
    this.graph = graph;
    this.route = route;
    this.from = from;
    this.to = to;
  }

  public Graph getGraph() {
    return graph;
  }

  public Route getRoute() {
    return route;
  }

  public Object getFrom() {
    return from;
  }

  public Object getTo() {
    return to;
  }
}
