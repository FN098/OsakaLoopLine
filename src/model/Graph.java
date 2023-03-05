package model;

import java.util.*;
import java.util.function.Predicate;

public final class Graph {
  private final Collection<Node> nodes;
  private final Collection<Link> links;

  public Graph(Collection<Node> nodes, Collection<Link> links) {
    this.nodes = nodes;
    this.links = links;
  }

  public Collection<Node> getNodes() {
    return nodes;
  }

  public Collection<Link> getLinks() {
    return links;
  }

  public Optional<Node> findNode(Predicate<? super Node> predicate) {
    return nodes.stream().filter(predicate).findFirst();
  }

  public Optional<Node> findNodeByValue(Object value) {
    return nodes.stream().filter(node -> node.getValue().equals(value)).findFirst();
  }

  public Optional<Link> findLink(Node from, Node to) {
    return links.stream().filter(link -> 
        link.getFrom().equals(from) &&
        link.getTo().equals(to)).findFirst();
  }

  public List<Node> findNeighborNodes(Node node) {
    return links.stream().filter(link -> 
        link.getFrom().equals(node)).map(Link::getTo).toList();
  }
}
