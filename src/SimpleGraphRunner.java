import java.io.*;

import logic.*;
import model.*;

public class SimpleGraphRunner {
  private static final String CONSOLE_CHARSET_NAME = "shift-jis";

  public static void main(String[] args) {
    var simpleGraph = new SimpleGraph();

    // グラフ情報を表示
    System.out.println(simpleGraph.getInfo());

    // 始点と終点を入力
    var from = readInput(simpleGraph, "始点を入力: ");
    var to = readInput(simpleGraph, "終点を入力: ");

    // 幅優先探索でルートを検索
    var graph = simpleGraph.getGraph();
    var result = new BreadthFirstSearchLogic().search(graph, from, to);

    // 結果を表示
    System.out.println("始点: " + result.getFrom());
    System.out.println("終点: " + result.getTo());
    System.out.println("探索履歴: " + result.getHistory());
    System.out.println("ルート: " + result.getRoute());
    System.out.println("駅数: " + result.getRoute().getTotalLinkCount());
    System.out.println("料金: " + result.getRoute().getTotalCost() + " 円");
  }

  private static Object readInput(SimpleGraph simpleGraph, String message) {
    System.out.print(message);
    var line = readLineFromConsole();

    var value = simpleGraph.findValue(line);
    if (value.isPresent()) {
      return value.get();
    }

    System.out.println("もう一度入力してください。");
    return readInput(simpleGraph, message);
  }

  private static String readLineFromConsole() {
    try {
      var reader = new BufferedReader(new InputStreamReader(System.in, CONSOLE_CHARSET_NAME));
      return reader.readLine();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
