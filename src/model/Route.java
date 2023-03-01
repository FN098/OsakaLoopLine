package model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Route {
  private static final Route EMPTY = new Route(Collections.emptyList());
  
  private final List<Link> links;

  public Route(List<Link> links) {
    this.links = links;
  }

  public static Route empty() {
    return EMPTY;
  }

  public List<Link> getLinks() {
    return Collections.unmodifiableList(links);
  }

  public int getTotalCost() {
    var sum = links.stream()
      .map(Link::getCost)
      .reduce((first, second) -> first + second)
      .orElse(0);
      
    return sum;
  }

  public int getTotalLinkCount() {
    return links.size();
  }

  public Stream<Node> getNodeStream() {
    return Stream.concat(
      links.stream().map(Link::getFrom).limit(1), 
      links.stream().map(Link::getTo));
  }

  @Override
  public String toString() {
    return getNodeStream()
      .map(Node::toString)
      .collect(Collectors.joining("->"));
  }
}
