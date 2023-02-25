package model;

public final class Station {
  private final String name;

  public Station(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return name;
  }

  @Override
  public boolean equals(Object object) {
    var other = (Station) object;
    return name.equals(other.name);
  }
}
