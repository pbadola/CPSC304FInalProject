package model;

public class ReturnReportCount {
  private final String city;
  private final String location;
  private final String vtname;
  private final int count;
  private final Double revenue;

  public ReturnReportCount(String city, String location, String vtname, int count, Double revenue) {
    this.city = city;
    this.location = location;
    this.vtname = vtname;
    this.count = count;
    this.revenue = revenue;
  }

  public String getLocation() {
    return location;
  }

  public String getCity() {
    return city;
  }

  public String getVtname() {
    return vtname;
  }

  public int getCount() {
    return count;
  }

  public Double getRevenue() {
    return revenue;
  }

  @Override
  public String toString() {
    return String.format(
        "ReturnReportCount [city=%s, location=%s, vtname=%s, count=%d, revenue=%.2f]",
        city, location, vtname, count, revenue);
  }
}
