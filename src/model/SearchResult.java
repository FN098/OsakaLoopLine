package model;

public final class SearchResult {
  private final Graph graph;
  private final Object from;
  private final Object to;
  private final Route route;
  private final History history;

  public SearchResult(
      Graph graph, 
      Object from, 
      Object to, 
      Route route, 
      History history) {
    this.graph = graph;
    this.from = from;
    this.to = to;
    this.route = route;
    this.history = history;
  }

  public Graph getGraph() {
    return graph;
  }

  public Object getFrom() {
    return from;
  }

  public Object getTo() {
    return to;
  }

  public Route getRoute() {
    return route;
  }

  public History getHistory() {
    return history;
  }
}
