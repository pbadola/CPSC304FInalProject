package model;

public class CustomersModel {
    // dlicense is primary key

    private final int cellphone;
    private final String name;
    private final String address;
    private final int dlicense;

    public CustomersModel(int cellphone, String name, String address, int dlicense) {
        this.cellphone = cellphone;
        this.name = name;
        this.address = address;
        this.dlicense = dlicense;
    }

    public int getCellphone() {
        return cellphone;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
    public int getDlicense() {
        return dlicense;
    }
}
