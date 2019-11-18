package model;


public class Customer {
    // dlicense is primary key

    private final int cellphone;
    private final String name;
    private final String address;
    private final String dlicense;


    public Customer(int cellphone, String name, String address, String dlicense) {
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
    public String getDlicense() {
        return dlicense;
    }

    @Override
    public String toString()
    {
        return String.format("Customer [dlicense=%s, cellphone=%d, name=%s, address=%s]",
                dlicense, cellphone, name, address);
    }
}
