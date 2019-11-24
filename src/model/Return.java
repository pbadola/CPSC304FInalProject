package model;

public class Return {
    private final int rid;
    private final String rdate;
    private final String rtime;
    private final int odometer;
    private final int fulltank;
    private double rvalue;

    public Return(int rid, String date, String time, int odometer, int fulltank, double value) {
        this.rid = rid;
        this.rdate = date;
        this.rtime = time;
        this.odometer = odometer;
        this.fulltank = fulltank;
        this.rvalue = value;
    }

    public int getRid() {
        return rid;
    }

    public String getRdate() {
        return rdate;
    }

    public String getRtime() {
        return rtime;
    }

    public int getOdometer() {
        return odometer;
    }

    public int isFulltank() {
        return fulltank;
    }

    public double getRvalue() {
        return rvalue;
    }

    public void setValue(double value){
        rvalue = value;
    }

    @Override
    public String toString()
    {
        return String.format("Return: \n"
                        + "* rid = %d\n"
                        + "* rdate = %s\n"
                        + "* rtime = %s\n"
                        + "* odometer = %d\n"
                        + "* fulltank = %d\n"
                        + "* rvalue = %.2f\n",
                rid, rdate, rtime, odometer, fulltank, rvalue);
    }
}
