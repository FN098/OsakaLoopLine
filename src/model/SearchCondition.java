package model;

public final class SearchCondition {
  private final Graph graph;
  private final Object from;
  private final Object to;

  public SearchCondition(Graph graph, Object from, Object to) {
    this.graph = graph;
    this.from = from;
    this.to = to;
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
  
}
