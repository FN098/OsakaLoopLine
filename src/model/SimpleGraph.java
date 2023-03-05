package model;
import java.util.*;

public final class SimpleGraph implements GraphGenerator {

  @Override
  public Graph createGraph() {
    var title = "シンプルなグラフ";

    var num = 0;
    var node1 = createNode(num++, "A");
    var node2 = createNode(num++, "B");
    var node3 = createNode(num++, "C");
    var node4 = createNode(num++, "D");
    var node5 = createNode(num++, "E");

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

    var graph = new Graph(nodes, links, title);
    return graph;
  }

  private Node createNode(Integer number, String name) {
    var station = new Station(number.toString(), name);
    var node = new Node(station);
    return node;
  }
}


