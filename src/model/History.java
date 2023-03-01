package model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class History {
  private static final History EMPTY = new History(Collections.emptyList());

  private final List<Node> nodes;

  public History(List<Node> nodes) {
    this.nodes = nodes;
  }

  public static History empty() {
    return EMPTY;
  }

  public List<Node> getNodes() {
    return Collections.unmodifiableList(nodes);
  }

  @Override
  public String toString() {
    return nodes.stream()
      .map(Node::toString)
      .collect(Collectors.joining("->"));
  }
}
