package model;

public class ReturnsModel {
    private final int rid;
    private final String date;
    private final String time;
    private final String odometer;
    private boolean fulltank;
    private int value;

    public ReturnsModel(int rid, String date, String time, String odometer, boolean fulltank, int value) {
        this.rid = rid;
        this.date = date;
        this.time = time;
        this.odometer = odometer;
        this.fulltank = fulltank;
        this.value = value;
    }

    public int getRid() {
        return rid;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getOdometer() {
        return odometer;
    }

    public boolean isFulltank() {
        return fulltank;
    }

    public int getValue() {
        return value;
    }
}
