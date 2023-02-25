package model;
import java.util.*;

public final class OsakaLoopLine {

  public static Graph createGraph() {
    String graphName = "大阪環状線";

    var osaka = createNode("大阪");
    var temma = createNode("天満");
    var sakuranomiya = createNode("桜ノ宮");
    var kyobashi = createNode("京橋");
    var osakajo = createNode("大阪城");
    var morinomiya = createNode("森ノ宮");
    var tamatsukuri = createNode("玉造");
    var tsuruhashi = createNode("鶴橋");
    var momodani = createNode("桃谷");
    var teradacho = createNode("寺田町");
    var tennoji = createNode("天王寺");
    var shinnimamiya = createNode("新今宮");
    var imamiya = createNode("今宮");
    var ashiharabashi = createNode("芦原橋");
    var taisho = createNode("大正");
    var bentencho = createNode("弁天町");
    var nishikujo = createNode("西九条");
    var noda = createNode("野田");
    var fukushima = createNode("福島");

    var links = new ArrayList<Link>(Arrays.asList(
      new Link(osaka, fukushima, 65),
      new Link(osaka, temma, 50),
      new Link(temma, osaka, 50),
      new Link(temma, sakuranomiya, 50),
      new Link(sakuranomiya, temma, 50),
      new Link(sakuranomiya, kyobashi, 50),
      new Link(kyobashi, sakuranomiya, 50),
      new Link(kyobashi, osakajo, 50),
      new Link(osakajo, kyobashi, 50),
      new Link(osakajo, morinomiya, 50),
      new Link(morinomiya, osakajo, 50),
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
      new Link(tennoji, shinnimamiya, 60),
      new Link(shinnimamiya, tennoji, 60),
      new Link(shinnimamiya, imamiya, 60),
      new Link(imamiya, shinnimamiya, 60),
      new Link(imamiya, ashiharabashi, 60),
      new Link(ashiharabashi, imamiya, 60),
      new Link(ashiharabashi, taisho, 60),
      new Link(taisho, ashiharabashi, 60),
      new Link(taisho, bentencho, 65),
      new Link(bentencho, taisho, 65),
      new Link(bentencho, nishikujo, 65),
      new Link(nishikujo, bentencho, 65),
      new Link(nishikujo, noda, 65),
      new Link(noda, nishikujo, 65),
      new Link(noda, fukushima, 65),
      new Link(fukushima, noda, 65),
      new Link(fukushima, osaka, 65)
    ));

    var nodes = links.stream()
      .map(Link::getFrom)
      .distinct()
      .toList();

    var graph = new Graph(nodes, links);
    graph.setName(graphName);
    return graph;
  }

  private static Node createNode(String stationName) {
    var station = new Station(stationName);
    var node = new Node(station);
    return node;
  }
}


