package tests;

import controller.VehicleTypesController;
import controller.VehiclesController;
import model.Vehicle;
import model.VehicleType;

public class VehicleTypesTest {
    public static void test(){
        VehicleType v1 = new VehicleType(
                "Hybrid",
                "matte coating",
                2.0,
                2.1,
                0.04,
                2.3,
                1.1,
                0.01,
                0.10
        );
        System.out.println("Adding the Hybrid to the server");
        VehicleTypesController.addVehicleType(v1);

        System.out.println("Checking if the vehicle type 'Hybrid' was added");
        VehicleType check = VehicleTypesController.getVehicleType("Hybrid");
        System.out.println(check);

        System.out.println("Deleting the Hybrid entry");
        VehicleTypesController.deleteVehicleType("Hybrid");
        check = VehicleTypesController.getVehicleType("Hybrid");
        if(check == null){
            System.out.println("Hybrid was deleted!");
        }
    }
}
