package logic;

import model.*;

public interface SearchLogic {
  public SearchResult search(Graph graph, Object from, Object to);
}
