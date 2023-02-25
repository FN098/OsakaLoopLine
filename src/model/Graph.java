package model;

import java.util.*;
import java.util.function.Predicate;

public final class Graph {
  private final Collection<Node> nodes;
  private final Collection<Link> links;
  private String name;

  public Graph(Collection<Node> nodes, Collection<Link> links) {
    this.nodes = nodes;
    this.links = links;
  }

  public List<Node> getNodes() {
    return nodes.stream().toList();
  }

  public List<Link> getLinks() {
    return links.stream().toList();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Node findNode(Predicate<? super Node> predicate) {
    return nodes.stream().filter(predicate).findFirst().orElse(null);
  }

  public Node findNodeByValue(Object value) {
    return nodes.stream().filter(node -> node.getValue().equals(value)).findFirst().orElse(null);
  }

  public Link findLink(Node from, Node to) {
    return links.stream().filter(link -> 
        link.getFrom().equals(from) &&
        link.getTo().equals(to)).findFirst().orElse(null);
  }

  public List<Node> findNeighborNodes(Node node) {
    return links.stream().filter(link -> 
        link.getFrom().equals(node)).map(Link::getTo).toList();
  }
}
