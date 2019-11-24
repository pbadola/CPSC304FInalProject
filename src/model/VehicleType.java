package model;

public class VehicleType {
  private final String vtname;
  private final String features;
  private final double wrate;
  private final double drate;
  private final double hrate;
  private final double wirate;
  private final double dirate;
  private final double hirate;
  private final double krate;

  public VehicleType(
      String vtname,
      String features,
      double wrate,
      double drate,
      double hrate,
      double wirate,
      double dirate,
      double hirate,
      double krate) {
    this.vtname = vtname;
    this.features = features;
    this.wrate = wrate;
    this.drate = drate;
    this.hrate = hrate;
    this.wirate = wirate;
    this.dirate = dirate;
    this.hirate = hirate;
    this.krate = krate;
  }

  public String getVtname() {
    return vtname;
  }

  public String getFeatures() {
    return features;
  }

  public double getWrate() {
    return wrate;
  }

  public double getDrate() {
    return drate;
  }

  public double getHrate() {
    return hrate;
  }

  public double getWirate() {
    return wirate;
  }

  public double getDirate() {
    return dirate;
  }

  public double getHirate() {
    return hirate;
  }

  public double getKrate() {
    return krate;
  }

  @Override
  public String toString() {
    return String.format(
        "Vehicle Type: \n"
            + "* vtname = %s\n"
            + "* features = %s\n"
            + "* wrate = %.2f\n"
            + "* drate = %.2f\n"
            + "* hrate = %.2f\n"
            + "* wirate = %.2f\n"
            + "* dirate = %.2f\n"
            + "* hirate = %.2f\n"
            + "* krate = %.2f",
        vtname, features, wrate, drate, hrate, wirate, dirate, hirate, krate);
  }
}
