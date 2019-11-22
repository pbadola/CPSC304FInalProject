package model;

import java.util.Random;

public class Reservation {
    // confNo is primary key
    private int confNo;
    private final String vtname;
    private final String dlicense;
    private final String pickUpTime;
    private final String pickUpDate;
    private final String dropOffTime;
    private final String dropOffDate;

    public Reservation(int confNo, String vtname, String dlicense, String pickUpTime, String pickUpDate, String dropOffTime,
                       String dropOffDate) {
        this.confNo = confNo;
        this.vtname = vtname;
        this.dlicense = dlicense;
        this.pickUpTime = pickUpTime;
        this.pickUpDate = pickUpDate;
        this.dropOffTime = dropOffTime;
        this.dropOffDate = dropOffDate;
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

    public String getPickUpTime() {
        return pickUpTime;
    }

    public String getPickUpDate() {
        return pickUpDate;
    }

    public String getDropOffTime() {
        return dropOffTime;
    }

    public String getDropOffDate() {
        return dropOffDate;
    }

    public void setConfNo(int confNo) {
        this.confNo = confNo;
    }

    @Override
    public String toString()
    {
        return String.format("Reservation [confNo=%d, vtname=%s, dlicense=%s, fromDate=%s, fromTime=%s," +
                        "toDate=%s, toTime=%s]",
                confNo, vtname, dlicense, pickUpDate, pickUpTime, dropOffDate, dropOffTime);
    }
}
