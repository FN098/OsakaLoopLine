package model;

public final class SearchResult {
  private final SearchCondition condition;
  private final Route route;
  private final History history;

  public SearchResult(
      SearchCondition condition, 
      Route route, 
      History history) {
    this.condition = condition;
    this.route = route;
    this.history = history;
  }
  
  public SearchCondition getCondition() {
    return condition;
  }

  public Route getRoute() {
    return route;
  }

  public History getHistory() {
    return history;
  }
}
