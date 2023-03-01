package model;

public final class Node {
  private final Object value;
  private Node parent = null;
  private boolean isVisited = false;
  private int step = 0;

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

  public int getStep() {
    return step;
  }

  public void setStep(int step) {
    this.step = step;
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
