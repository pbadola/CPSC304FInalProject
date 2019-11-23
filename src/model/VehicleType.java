package model;

public class VehicleType {
  private final String vtname;
  private final String features;
  private final float wrate;
  private final float drate;
  private final float hrate;
  private final float wirate;
  private final float dirate;
  private final float hirate;
  private final float krate;

  public VehicleType(
      String vtname,
      String features,
      float wrate,
      float drate,
      float hrate,
      float wirate,
      float dirate,
      float hirate,
      float krate) {
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

  public float getWrate() {
    return wrate;
  }

  public float getDrate() {
    return drate;
  }

  public float getHrate() {
    return hrate;
  }

  public float getWirate() {
    return wirate;
  }

  public float getDirate() {
    return dirate;
  }

  public float getHirate() {
    return hirate;
  }

  public float getKrate() {
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
