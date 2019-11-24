package model;

import java.sql.Timestamp;

public class Reservation {
  private int confNo;
  private final String vtname;
  private final String dlicense;
  private final Timestamp fromDateTime;
  private final Timestamp toDateTime;

  public Reservation(
      int confNo, String vtname, String dlicense, Timestamp fromDateTime, Timestamp toDateTime) {
    this.confNo = confNo;
    this.vtname = vtname;
    this.dlicense = dlicense;
    this.fromDateTime = fromDateTime;
    this.toDateTime = toDateTime;
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

  public void setConfNo(int confNo) {
    this.confNo = confNo;
  }

  @Override
  public String toString() {
    return String.format(
        "Reservation [confNo=%d, vtname=%s, dlicense=%s, fromDateTime=%s, toDateTime=%s]",
        confNo, vtname, dlicense, fromDateTime, toDateTime);
  }
}
