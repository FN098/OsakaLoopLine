/*
 * title: 
 *    大阪環状線　経路探索
 * 
 * description: 
 *    大阪環状線の始点と終点を入力し、最短経路を探索します。
 * 
 * author: 
 *    Futoshi Nishino
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import logic.*;
import model.*;

public class Main {

  private static final String STDIN_CHARSET_NAME = "shift-jis";
  private static final Station UNKNOWN = new Station("unknown");

  public static void main(String[] args) {
    // グラフを作成
    var graph = OsakaLoopLine.createGraph();

    // 全ノードに数字を割り当てる
    var map = createNodeMap(graph);

    // グラフ情報を表示
    showGraphInfo(graph, map);

    // 始点と終点を入力
    Station from, to;
    try {
      from = readStation(map, "始点を入力: ");
      to = readStation(map, "終点を入力: ");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    // 幅優先探索でルートを検索
    var result = new BreadthFirstSearchLogic().search(graph, from, to);
    var route = result.getRoute();

    // 結果を表示
    System.out.println("始点: " + result.getFrom());
    System.out.println("終点: " + result.getTo());
    System.out.println("ルート: " + route);
    System.out.println("駅数: " + route.getTotalLinkCount());
    System.out.println("料金: " + route.getTotalCost() + " 円");
  }

  private static Map<Integer, Node> createNodeMap(Graph graph) {
    var nodes = graph.getNodes();
    var map = IntStream.range(0, nodes.size())
      .boxed()
      .collect(Collectors.toMap(
        i -> i,
        i -> nodes.get(i)
      ));

    return map;
  }

  private static void showGraphInfo(Graph graph, Map<Integer, Node> map) {
    var name = graph.getName();
    var message = map.keySet().stream()
      .map(key -> key + ": " + map.get(key))
      .collect(Collectors.joining(", "));

    System.out.println(name + ": " + message);
  }

  private static Station readStation(Map<Integer, Node> map, String message) throws IOException {
    System.out.print(message);
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, STDIN_CHARSET_NAME));
    var input = reader.readLine();

    try {
      // 数字の場合
      var key = Integer.parseInt(input);
      if (map.containsKey(key)) {
        return (Station) map.get(key).getValue();
      } else {
        return UNKNOWN;
      }

    } catch (NumberFormatException e) {
      // 駅名の場合
      var station = map.values().stream().map(node -> (Station) node.getValue())
        .filter(st -> st.getName().equals(input))
        .findFirst().orElse(UNKNOWN);
        
      return station;
    }
  }
}