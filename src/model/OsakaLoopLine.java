package model;
import java.util.*;

public final class OsakaLoopLine {

  public static Graph createGraph() {
    String graphName = "大阪環状線";

    int num = 0;
    var osaka = createNode(num++, "大阪");
    var temma = createNode(num++, "天満");
    var sakuranomiya = createNode(num++, "桜ノ宮");
    var kyobashi = createNode(num++, "京橋");
    var osakajo = createNode(num++, "大阪城");
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

  private static Node createNode(Integer number, String name) {
    var station = new Station(number.toString(), name);
    var node = new Node(station);
    return node;
  }
}


