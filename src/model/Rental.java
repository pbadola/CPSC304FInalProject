package model;

import java.sql.Timestamp;

public class Rental {
  // come back to this when we know whats needed
  private final int rid;
  private final String vlicense;
  private final String dlicense;
  private final Timestamp fromDateTime;
  private final Timestamp toDateTime;
  private final int odometer;
  private final String cardName;
  private final String cardNo;
  private final String expDate;
  private final int confNo;

  public Rental(
      int rid,
      String vlicense,
      String dlicense,
      Timestamp fromDateTime,
      Timestamp toDateTime,
      int odometer,
      String cardName,
      String cardNo,
      String expDate,
      int confNo) {
    this.rid = rid;
    this.vlicense = vlicense;
    this.dlicense = dlicense;
    this.fromDateTime = fromDateTime;
    this.toDateTime = toDateTime;
    this.odometer = odometer;
    this.cardName = cardName;
    this.cardNo = cardNo;
    this.confNo = confNo;
    this.expDate = expDate;
  }

  public int getRid() {
    return rid;
  }

  public String getVlicense() {
    return vlicense;
  }

  public String getDlicense() {
    return dlicense;
  }

  public int getOdometer() {
    return odometer;
  }

  public String getCardName() {
    return cardName;
  }

  public String getCardNo() {
    return cardNo;
  }

  public Timestamp getFromDateTime() {
    return fromDateTime;
  }

  public Timestamp getToDateTime() {
    return toDateTime;
  }

  public String getExpDate() {
    return expDate;
  }

  public int getConfNo() {
    return confNo;
  }

  @Override
  public String toString() {
    return String.format(
        "Rental: \n"
            + "* rid = %d\n"
            + "* vlicense = %s\n"
            + "* dlicense = %s\n"
            + "* fromDateTime = %s\n"
            + "* toDateTime = %s\n"
            + "* odometer = %d\n"
            + "* cardName = %s\n"
            + "* cardNo = %s\n"
            + "* expDate = %s\n"
            + "* confNo = %d",
        rid,
        vlicense,
        dlicense,
        fromDateTime,
        toDateTime,
        odometer,
        cardName,
        cardNo,
        expDate,
        confNo);
  }
}
