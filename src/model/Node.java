package model;

public final class Node {
  private final Object value;

  public Node(Object value) {
    this.value = value;
  }

  public Object getValue() {
    return value;
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
