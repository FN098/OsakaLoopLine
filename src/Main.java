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
import java.util.stream.Collectors;

import logic.*;
import model.*;

public class Main {

  private static final String STDIN_CHARSET_NAME = "shift-jis";

  public static void main(String[] args) {
    // グラフを作成
    var graph = OsakaLoopLine.createGraph();

    // グラフ情報を表示
    var graphInfo = graph.getNodes().stream()
      .map(node -> (Station) node.getValue())
      .map(station -> station.getNumber() + ": " + station.getName())
      .collect(Collectors.joining(", "));
    System.out.println(graph.getTitle() + ": " + graphInfo);

    // 始点と終点を入力
    Object from, to;
    try {
      from = readStation(graph, "始点を入力: ");
      to = readStation(graph, "終点を入力: ");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    // 幅優先探索でルートを検索
    var result = new BreadthFirstSearchLogic().search(graph, from, to);

    // 結果を表示
    System.out.println("始点: " + result.getFrom());
    System.out.println("終点: " + result.getTo());
    System.out.println("履歴: " + result.getHistory());
    System.out.println("ルート: " + result.getRoute());
    System.out.println("駅数: " + result.getRoute().getTotalLinkCount());
    System.out.println("料金: " + result.getRoute().getTotalCost() + " 円");
  }

  private static Station readStation(Graph graph, String message) throws IOException {
    System.out.print(message);

    var reader = new BufferedReader(new InputStreamReader(System.in, STDIN_CHARSET_NAME));
    var line = reader.readLine();

    var station = graph.getNodes().stream()
      .map(n -> (Station) n.getValue())
      .filter(s -> line.equals(s.getName()) || line.equals(s.getNumber()))
      .findFirst();
    
    if (station.isPresent()) {
      return station.get();
    }
    return readStation(graph, message);
  }
}