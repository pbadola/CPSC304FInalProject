package model;

public class Rental {
    // come back to this when we know whats needed
    private final int rid;
    private final String vlicense;
    private final String dlicense;
    private final String fromTime;
    private final String fromDate;
    private final String toTime;
    private final String toDate;
    private final int odometer;
    private final String cardName;
    private final int cardNo;
    private final String expDate;
    private final int confNo;

    public Rental(int rid, String vlicense, String dlicense, String fromDate, String fromTime, String toDate,
                  String toTime, int odometer, String cardName, int cardNo, String expDate, int confNo){
        this.rid = rid;
        this.vlicense = vlicense;
        this.dlicense = dlicense;
        this.fromTime = fromTime;
        this.fromDate = fromDate;
        this.toTime = toTime;
        this.toDate = toDate;
        this.odometer = odometer;
        this.cardName = cardName;
        this.cardNo = cardNo;
        this.confNo = confNo;
        this.expDate = expDate;
    }

    public int getRid(){
        return rid;
    }

    public String getVlicense(){
        return vlicense;
    }

    public String getDlicense(){
        return dlicense;
    }

    public int getOdometer() {
        return odometer;
    }

    public String getCardName() {
        return cardName;
    }

    public int getCardNo() {
        return cardNo;
    }

    public String getFromTime() {
        return fromTime;
    }

    public String getFromDate() {
        return fromDate;
    }

    public String getToTime() {
        return toTime;
    }

    public String getToDate() {
        return toDate;
    }

    public String getExpDate() {
        return expDate;
    }

    public int getConfNo() {
        return confNo;
    }
}
