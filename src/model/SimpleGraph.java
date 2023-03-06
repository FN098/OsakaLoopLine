package model;
import java.util.*;
import java.util.stream.Collectors;

public final class SimpleGraph {
  private final String title;
  private final Graph graph;

  public SimpleGraph() {
    this.title = "シンプルグラフ";
    this.graph = createGraph();
  }

  public String getTitle() {
    return title;
  }
  
  public Graph getGraph() {
    return graph;
  }

  private Graph createGraph() {
    var node1 = new Node("A");
    var node2 = new Node("B");
    var node3 = new Node("C");
    var node4 = new Node("D");
    var node5 = new Node("E");

    var links = new ArrayList<Link>(Arrays.asList(
      new Link(node1, node2, 1),
      new Link(node2, node1, 1),
      new Link(node1, node3, 1),
      new Link(node3, node1, 1),
      new Link(node2, node3, 1),
      new Link(node3, node3, 1),
      new Link(node2, node4, 1),
      new Link(node4, node2, 1),
      new Link(node3, node4, 1),
      new Link(node4, node3, 1),
      new Link(node4, node5, 1),
      new Link(node5, node4, 1)
    ));

    var nodes = links.stream()
      .map(Link::getFrom)
      .distinct()
      .toList();

    var graph = new Graph(nodes, links);
    return graph;
  }

  public String getInfo() {
    var info = graph.getNodes().stream()
      .map(node -> (String) node.getValue())
      .collect(Collectors.joining(", "));
      
    return title + ": " + info;
  }

  public Optional<String> findText(String value) {
    return graph.getNodes().stream()
      .map(node -> (String) node.getValue())
      .filter(str -> str.equals(value))
      .findFirst();
  }
}


