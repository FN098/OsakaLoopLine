package model;

public final class Station {
  private final String number;
  private final String name;

  public Station(String number, String name) {
    this.number = number;
    this.name = name;
  }

  public String getNumber() {
    return number;
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
