package model;

public class RentalReportCount {
  private final String city;
  private final String location;
  private final String vtname;
  private final int count;

  public RentalReportCount(String city, String location, String vtname, int count) {
    this.city = city;
    this.location = location;
    this.vtname = vtname;
    this.count = count;
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

  @Override
  public String toString() {
    return String.format(
        "RentalReportCount [city=%s, location=%s, vtname=%s, count=%d]",
        city, location, vtname, count);
  }
}
