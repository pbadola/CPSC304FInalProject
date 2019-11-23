package tests;

import controller.CustomersController;
import model.Customer;

public class CustomersTest {
  public static void test() {
    Customer c1 = new Customer(356345634, "Patrishka Badola", "Dumpster", "1234567");
    CustomersController.addCustomer(c1);

    Customer c2 = new Customer(356234234, "Ruperto Torres", "Hell", "1234568");
    CustomersController.addCustomer(c2);

    Customer test = CustomersController.getCustomer(c2.getDlicense());
    System.out.println(test);
    Customer test2 = CustomersController.getCustomer(c1.getDlicense());
    System.out.println(test2);

    CustomersController.deleteCustomer(c2.getDlicense());
    Customer test3 = CustomersController.getCustomer(c2.getDlicense());
    System.out.println(test3);

    CustomersController.deleteCustomer(c1.getDlicense());
  }
}
