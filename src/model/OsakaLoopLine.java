package model;
import java.util.*;
import java.util.stream.Collectors;

public final class OsakaLoopLine {
  private final String title;
  private final Graph graph;

  public OsakaLoopLine() {
    this.title = "大阪環状線";
    this.graph = createGraph();
  }

  public String getTitle() {
    return title;
  }
  
  public Graph getGraph() {
    return graph;
  }

  private Graph createGraph() {
    var num = 0;
    var osaka = createNode(num++, "大阪");
    var temma = createNode(num++, "天満");
    var sakuranomiya = createNode(num++, "桜ノ宮");
    var kyobashi = createNode(num++, "京橋");
    var osakajokoen = createNode(num++, "大阪城公園");
    var morinomiya = createNode(num++, "森ノ宮");
    var tamatsukuri = createNode(num++, "玉造");
    var tsuruhashi = createNode(num++, "鶴橋");
    var momodani = createNode(num++, "桃谷");
    var teradacho = createNode(num++, "寺田町");
    var tennoji = createNode(num++, "天王寺");
    var shinnimamiya = createNode(num++, "新今宮");
    var imamiya = createNode(num++, "今宮");
    var ashiharabashi = createNode(num++, "芦原橋");
    var taisho = createNode(num++, "大正");
    var bentencho = createNode(num++, "弁天町");
    var nishikujo = createNode(num++, "西九条");
    var noda = createNode(num++, "野田");
    var fukushima = createNode(num++, "福島");

    var links = new ArrayList<Link>(Arrays.asList(
      // A区間
      new Link(osaka, temma, 50),
      new Link(temma, osaka, 50),
      new Link(temma, sakuranomiya, 50),
      new Link(sakuranomiya, temma, 50),
      new Link(sakuranomiya, kyobashi, 50),
      new Link(kyobashi, sakuranomiya, 50),
      new Link(kyobashi, osakajokoen, 50),
      new Link(osakajokoen, kyobashi, 50),
      new Link(osakajokoen, morinomiya, 50),
      new Link(morinomiya, osakajokoen, 50),
      // B区間
      new Link(morinomiya, tamatsukuri, 55),
      new Link(tamatsukuri, morinomiya, 55),
      new Link(tamatsukuri, tsuruhashi, 55),
      new Link(tsuruhashi, tamatsukuri, 55),
      new Link(tsuruhashi, momodani, 55),
      new Link(momodani, tsuruhashi, 55),
      new Link(momodani, teradacho, 55),
      new Link(teradacho, momodani, 55),
      new Link(teradacho, tennoji, 55),
      new Link(tennoji, teradacho, 55),
      // C区間
      new Link(tennoji, shinnimamiya, 60),
      new Link(shinnimamiya, tennoji, 60),
      new Link(shinnimamiya, imamiya, 60),
      new Link(imamiya, shinnimamiya, 60),
      new Link(imamiya, ashiharabashi, 60),
      new Link(ashiharabashi, imamiya, 60),
      new Link(ashiharabashi, taisho, 60),
      new Link(taisho, ashiharabashi, 60),
      // D区間
      new Link(taisho, bentencho, 65),
      new Link(bentencho, taisho, 65),
      new Link(bentencho, nishikujo, 65),
      new Link(nishikujo, bentencho, 65),
      new Link(nishikujo, noda, 65),
      new Link(noda, nishikujo, 65),
      new Link(noda, fukushima, 65),
      new Link(fukushima, noda, 65),
      new Link(fukushima, osaka, 65),
      new Link(osaka, fukushima, 65)
    ));

    var nodes = links.stream()
      .map(Link::getFrom)
      .distinct()
      .toList();

    var graph = new Graph(nodes, links);
    return graph;
  }

  private Node createNode(Integer number, String name) {
    var station = new Station(number.toString(), name);
    var node = new Node(station);
    return node;
  }

  public String getInfo() {
    var info = graph.getNodes().stream()
      .map(node -> (Station) node.getValue())
      .map(station -> station.getNumber() + ": " + station.getName())
      .collect(Collectors.joining(", "));
      
    return title + ": " + info;
  }

  public Optional<Station> findStationByNumber(String number) {
    return graph.getNodes().stream()
      .map(node -> (Station) node.getValue())
      .filter(station -> station.getNumber().equals(number))
      .findFirst();
  }

  public Optional<Station> findStationByName(String name) {
    return graph.getNodes().stream()
      .map(node -> (Station) node.getValue())
      .filter(station -> station.getName().equals(name))
      .findFirst();
  }
}


