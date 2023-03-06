import logic.*;
import model.*;
import util.ConsoleReader;

public class SimpleGraphRunner {
  public static void main(String[] args) {
    
    // グラフを作成
    var simpleGraph = new SimpleGraph();
    var graph = simpleGraph.getGraph();

    // グラフ情報を表示
    System.out.println(simpleGraph.getInfo());

    // 始点と終点を入力
    var from = readInputValue(simpleGraph, "始点を入力: ");
    var to = readInputValue(simpleGraph, "終点を入力: ");

    // 幅優先探索でルートを検索
    var condition = new SearchCondition(graph, from, to);
    var result = new BreadthFirstSearchLogic(condition).execute();

    // 結果を表示
    System.out.println("始点: " + result.getCondition().getFrom());
    System.out.println("終点: " + result.getCondition().getTo());
    System.out.println("探索履歴: " + result.getHistory());
    System.out.println("ルート: " + result.getRoute());
    System.out.println("駅数: " + result.getRoute().getTotalLinkCount());
    System.out.println("料金: " + result.getRoute().getTotalCost() + " 円");
  }

  private static Object readInputValue(SimpleGraph simpleGraph, String message) {
    System.out.print(message);
    var line = new ConsoleReader().readLine();

    var value = simpleGraph.findText(line).orElse(null);
    if (value != null) {
      return value;
    }

    return readInputValue(simpleGraph, "もう一度入力してください。");
  }
}
