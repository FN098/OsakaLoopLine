package model;

public final class Node {
  private final Object value;
  private Node parent;
  private boolean isVisited = false;

  public Node(Object value) {
    this.value = value;
  }

  public Object getValue() {
    return value;
  }

  public Node getParent() {
    return parent;
  }

  public void setParent(Node parent) {
    this.parent = parent;
  }

  public boolean getIsVisited() {
    return isVisited;
  }

  public void setIsVisited(boolean isVisited) {
    this.isVisited = isVisited;
  }

  @Override
  public String toString() {
    return value.toString();
  }

  @Override
  public boolean equals(Object object) {
    var other = (Node) object;
    return value.equals(other.value);
  }
}
