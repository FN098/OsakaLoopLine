import java.io.*;

import logic.*;
import model.*;

public class OsakaLoopLineRunner {
  private static final String CONSOLE_CHARSET_NAME = "shift-jis";

  public static void main(String[] args) {
    var osakaLoopLine = new OsakaLoopLine();

    // グラフ情報を表示
    System.out.println(osakaLoopLine.getInfo());

    // 始点と終点を入力
    var from = readInput(osakaLoopLine, "始点を入力: ");
    var to = readInput(osakaLoopLine, "終点を入力: ");

    // 幅優先探索でルートを検索
    var graph = osakaLoopLine.getGraph();
    var result = new BreadthFirstSearchLogic().search(graph, from, to);

    // 結果を表示
    System.out.println("始点: " + result.getFrom());
    System.out.println("終点: " + result.getTo());
    System.out.println("探索履歴: " + result.getHistory());
    System.out.println("ルート: " + result.getRoute());
    System.out.println("駅数: " + result.getRoute().getTotalLinkCount());
    System.out.println("料金: " + result.getRoute().getTotalCost() + " 円");
  }

  private static Object readInput(OsakaLoopLine osakaLoopLine, String message) {
    System.out.print(message);
    var line = readLineFromConsole();

    var station = osakaLoopLine.findStationByName(line);
    if (station.isPresent()) {
      return station.get();
    }

    station = osakaLoopLine.findStationByNumber(line);
    if (station.isPresent()) {
      return station.get();
    }

    System.out.println("もう一度入力してください。");
    return readInput(osakaLoopLine, message);
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
