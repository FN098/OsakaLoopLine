package model;

public final class Link {
  private final Node from;
  private final Node to;
  private final int cost;

  public Link(Node from, Node to, int cost) {
    this.from = from;
    this.to = to;
    this.cost = cost;
  }

  public Node getFrom() {
    return from;
  }

  public Node getTo() {
    return to;
  }

  public int getCost() {
    return cost;
  }
}