package model;

public class TimeIntervalModel {
    private String pickUpTime;
    private String pickUpDate;
    private String dropOffTime;
    private String dropOffDate;

    public TimeIntervalModel(String pickUpTime, String pickUpDate, String dropOffTime, String dropOffDate) {
        this.pickUpTime = pickUpTime;
        this.pickUpDate = pickUpDate;
        this.dropOffTime = dropOffTime;
        this.dropOffDate = dropOffDate;
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
}
