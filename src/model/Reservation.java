package model;

import java.sql.Timestamp;

public class Reservation {
  private int confNo;
  private final String vtname;
  private final String dlicense;
  private final Timestamp fromDateTime;
  private final Timestamp toDateTime;
  private final String location;
  private final String city;

  public Reservation(
      int confNo,
      String vtname,
      String dlicense,
      Timestamp fromDateTime,
      Timestamp toDateTime,
      String location,
      String city) {
    this.confNo = confNo;
    this.vtname = vtname;
    this.dlicense = dlicense;
    this.fromDateTime = fromDateTime;
    this.toDateTime = toDateTime;
    this.location = location;
    this.city = city;
  }

  public int getConfNo() {
    return confNo;
  }

  public String getVtname() {
    return vtname;
  }

  public String getDlicense() {
    return dlicense;
  }

  public Timestamp getFromDateTime() {
    return fromDateTime;
  }

  public Timestamp getToDateTime() {
    return toDateTime;
  }

  public String getLocation() {
    return location;
  }

  public String getCity() {
    return city;
  }

  public void setConfNo(int confNo) {
    this.confNo = confNo;
  }

  @Override
  public String toString() {
    return String.format(
        "Reservation [confNo=%d, vtname=%s, dlicense=%s, fromDateTime=%s, toDateTime=%s, location=%s, city=%s]",
        confNo, vtname, dlicense, fromDateTime, toDateTime, location, city);
  }
}
