package model;

public class RentalConfirmation {
  private final int rid;
  private final Vehicle vehicle;

  public RentalConfirmation(int rid, Vehicle vehicle) {
    this.rid = rid;
    this.vehicle = vehicle;
  }

  public int getRid() {
    return rid;
  }

  public Vehicle getVehicle() {
    return vehicle;
  }
}
