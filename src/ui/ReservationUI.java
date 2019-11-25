package ui;

import controller.CustomersController;
import controller.ReservationsController;
import controller.VehiclesController;
import model.Customer;
import model.Reservation;
import model.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import static java.lang.String.valueOf;

public class ReservationUI extends JFrame implements ActionListener  {
    private static final int TEXT_FIELD_WIDTH = 30;
    private  MainWindow mw;
    private Reservation res;
    private Customer customer;

    private JButton submitButton;
    private JButton submitCustButton;
    private JButton submitInfoButton;
    private JButton reserveButton;
    private JButton cancelButton;

    private JTextField carTypeField;
    private JTextField locationField;
    private JTextField fromDateField;
    private JTextField fromTimeField;
    private JTextField toDateField;
    private JTextField toTimeField;
    private JTextField cityField;
    private JTextField dlicenseField;
    private JTextField nameField;
    private JTextField addressField;
    private JTextField phoneNumField;


    private String carType;
    private String location;
    private String fromDate;
    private String fromTime;
    private String toDate;
    private String toTime;
    private String city;
    private String dlicense;
    private String name;
    private String address;
    private String phoneNum;
    private int confNo;


    private JFrame frame;
    private JPanel contentPane;
    private GridBagLayout gb;
    private  GridBagConstraints c;

    private ArrayList<Vehicle> availableVehicles = new ArrayList<>();

    public ReservationUI(JFrame frame){
        super("Available Vehicle Menu");
        this.frame = frame;
    }

    public void showMenu(){
        frame.getContentPane().removeAll();
        frame.repaint();

        //TODO: Enter the format
        JLabel carTypeLabel = new JLabel("Car type: ");
        JLabel locationLabel = new JLabel("Location: ");
        JLabel cityLabel = new JLabel("City: ");
        JLabel fromDateLabel = new JLabel("Pickup Date (required): ");
        JLabel fromTimeLabel = new JLabel("Pickup Time (required): ");
        JLabel toDateLabel = new JLabel("Drop-off Date (required): ");
        JLabel toTimeLabel = new JLabel("Drop-off Time (required): ");

        carTypeField = new JTextField(TEXT_FIELD_WIDTH);
        locationField = new JTextField(TEXT_FIELD_WIDTH);
        cityField = new JTextField(TEXT_FIELD_WIDTH);
        fromDateField = new JTextField(TEXT_FIELD_WIDTH);
        fromTimeField = new JTextField(TEXT_FIELD_WIDTH);
        toDateField = new JTextField(TEXT_FIELD_WIDTH);
        toTimeField = new JTextField(TEXT_FIELD_WIDTH);

        submitButton = new JButton("SUBMIT");

        contentPane = new JPanel();
        frame.setContentPane(contentPane);

        // layout components using the GridBag layout manager
        gb = new GridBagLayout();
        c = new GridBagConstraints();

        contentPane.setLayout(gb);
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //car type
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 10);
        gb.setConstraints(carTypeLabel, c);
        contentPane.add(carTypeLabel);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(10, 0, 5, 10);
        gb.setConstraints(carTypeField, c);
        contentPane.add(carTypeField);

        // location
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 0);
        gb.setConstraints(locationLabel, c);
        contentPane.add(locationLabel);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(10, 0, 5, 10);
        gb.setConstraints(locationField, c);
        contentPane.add(locationField);

        // place the city label
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 0);
        c.anchor = GridBagConstraints.CENTER;
        gb.setConstraints(cityLabel, c);
        contentPane.add(cityLabel);

        // place the text field for the location
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(10, 0, 5, 10);
        c.anchor = GridBagConstraints.CENTER;
        gb.setConstraints(cityField, c);
        contentPane.add(cityField);

        // from date
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 10);
        c.anchor = GridBagConstraints.CENTER;
        gb.setConstraints(fromDateLabel, c);
        contentPane.add(fromDateLabel);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(10, 0, 5, 10);
        c.anchor = GridBagConstraints.CENTER;
        gb.setConstraints(fromDateField, c);
        contentPane.add(fromDateField);

        // from time
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 10);
        c.anchor = GridBagConstraints.CENTER;
        gb.setConstraints(fromTimeLabel, c);
        contentPane.add(fromTimeLabel);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(10, 0, 5, 10);
        c.anchor = GridBagConstraints.CENTER;
        gb.setConstraints(fromTimeField, c);
        contentPane.add(fromTimeField);

        // to date
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 10);
        c.anchor = GridBagConstraints.CENTER;
        gb.setConstraints(toDateLabel, c);
        contentPane.add(toDateLabel);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(10, 0, 5, 10);
        c.anchor = GridBagConstraints.CENTER;
        gb.setConstraints(toDateField, c);
        contentPane.add(toDateField);

        // to time
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 10);
        c.anchor = GridBagConstraints.CENTER;
        gb.setConstraints(toTimeLabel, c);
        contentPane.add(toTimeLabel);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(10, 0, 5, 10);
        c.anchor = GridBagConstraints.CENTER;
        gb.setConstraints(toTimeField, c);
        contentPane.add(toTimeField);

        // submit button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        gb.setConstraints(submitButton, c);
        contentPane.add(submitButton);

        submitButton.addActionListener(this);

        // anonymous inner class for closing the window
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // size the window to obtain a best fit for the components
        frame.pack();

        // center the frame
        Dimension d = frame.getToolkit().getScreenSize();
        Rectangle r = frame.getBounds();
        frame.setLocation( (d.width - r.width)/2, (d.height - r.height)/2 );

        // make the window visible
        frame.setVisible(true);

        // place the cursor in the text field for the username
        carTypeField.requestFocus();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO: Handle errors appropriately
        //if getVehicles gives null then
            //error handling
        //else
        if(e.getSource() == submitButton) {
            carType = carTypeField.getText();
            location = locationField.getText();
            fromDate = fromDateField.getText();
            fromTime = fromTimeField.getText();
            toDate = toDateField.getText();
            toTime = toTimeField.getText();
            city = cityField.getText();
            if(fromDate.isBlank() || fromTime.isBlank()|| toDate.isBlank()|| toTime.isBlank()){
                missingRequiredInfo();
            } else {
                showAvailableVehicles();
            }
        } else if (e.getSource() == reserveButton){
            reservationMenu();
        } else if(e.getSource() == cancelButton){
            mw = new MainWindow();
            mw.showFrame();
        } else if(e.getSource() == submitCustButton){
            name = nameField.getText();
            dlicense = dlicenseField.getText();
            if(dlicense.isBlank() || name.isBlank()){
                missingRequiredInfo();
            }else{
                makeReservation();
            }
        } else if(e.getSource() == submitInfoButton){
            phoneNum = phoneNumField.getText();
            address = addressField.getText();
            if (address.isBlank() || phoneNum.isBlank()){
                missingRequiredInfo();
            } else {
                customer = handleCustomerInfo();
            }
        }
    }
    private void missingRequiredInfo(){
        JLabel requiredLabel = new JLabel("Please ensure that you have filled in " +
                "the required fields");

        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 0);
        c.anchor = GridBagConstraints.CENTER;
        gb.setConstraints(requiredLabel, c);
        contentPane.add(requiredLabel);
        frame.pack();
    }
    public void showAvailableVehicles(){
        frame.getContentPane().removeAll();
        frame.repaint();

        JLabel availabilityLabel;
        reserveButton = new JButton("RESERVE");
        cancelButton = new JButton("CANCEL");

        System.out.println(carType);
        if (carType.isBlank()){
            carType = null;
        }
        if (location.isBlank()){
            location = null;
        }
        if (city.isBlank()){
            city = null;
        }
        availableVehicles = VehiclesController.getAvailableVehicles(carType, location, city);
        boolean setReserve = false;

        if (availableVehicles == null || availableVehicles.isEmpty()){
            availabilityLabel = new JLabel("**** Sorry, there are no vehicles available at this time****");
        } else {
            availabilityLabel = new JLabel("Good news, we have the perfect vehicle for you to rent!");
            setReserve = true;
        }

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        gb.setConstraints(availabilityLabel, c);
        contentPane.add(availabilityLabel);

        if (setReserve){

            // reserve button
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.insets = new Insets(5, 10, 10, 10);
            c.anchor = GridBagConstraints.CENTER;
            gb.setConstraints(reserveButton, c);
            contentPane.add(reserveButton);
        }

        //cancel button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        gb.setConstraints(cancelButton, c);
        contentPane.add(cancelButton);

        reserveButton.addActionListener(this);
        cancelButton.addActionListener(this);
        frame.pack();


    }

    private void makeReservation(){
        customer = CustomersController.getCustomer(dlicense);
        if (customer == null){
           getCustomerInfo();
        }
        carType = availableVehicles.get(0).getVtname();

        //TODO: Remember Reservation has city and location
        res = new Reservation(1, carType, dlicense, fromDate, fromTime, toDate, toTime);
        //location and city
        confNo = ReservationsController.makeReservation(res);
        printDetails();

    }

    private void printDetails(){
        frame.getContentPane().removeAll();
        frame.repaint();

        String conf = valueOf(confNo);

        JLabel confirmationLabel = new JLabel("SUCCESS! Your confirmation number is "+ conf);
        JLabel nameLabel = new JLabel("Name: " + name);
        JLabel fromDateLabel = new JLabel("Pickup Date: " + fromDate);
        JLabel fromTimeLabel = new JLabel("Pickup Time: " + fromTime);
        JLabel toDateLabel = new JLabel("Dropoff Date: " + toDate);
        JLabel toTimeLabel = new JLabel("Dropoff Time: " + toTime);
        JLabel locationLabel = new JLabel("Locatoin: " + location);
        JLabel cityLabel = new JLabel("City: " + city);
        JLabel cartypeLabel = new JLabel("Car type: "+ carType);


        cancelButton = new JButton("OK");

        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 0);
        gb.setConstraints(confirmationLabel, c);
        contentPane.add(confirmationLabel);

//        c.gridwidth = GridBagConstraints.RELATIVE;
//        c.insets = new Insets(10, 10, 5, 0);
//        gb.setConstraints(nameLabel, c);
//        contentPane.add(nameLabel);

//        c.gridwidth = GridBagConstraints.RELATIVE;
//        c.insets = new Insets(10, 10, 5, 0);
//        gb.setConstraints(cartypeLabel, c);
//        contentPane.add(cartypeLabel);
//
//        c.gridwidth = GridBagConstraints.RELATIVE;
//        c.insets = new Insets(10, 10, 5, 0);
//        gb.setConstraints(fromDateLabel, c);
//        contentPane.add(fromDateLabel);
//
//        c.gridwidth = GridBagConstraints.RELATIVE;
//        c.insets = new Insets(10, 10, 5, 0);
//        gb.setConstraints(fromTimeLabel, c);
//        contentPane.add(fromTimeLabel);
//
//        c.gridwidth = GridBagConstraints.RELATIVE;
//        c.insets = new Insets(10, 10, 5, 0);
//        gb.setConstraints(toDateLabel, c);
//        contentPane.add(toDateLabel);
//
//        c.gridwidth = GridBagConstraints.RELATIVE;
//        c.insets = new Insets(10, 10, 5, 0);
//        gb.setConstraints(toTimeLabel, c);
//        contentPane.add(toTimeLabel);
//
//        if (location != null){
//            c.gridwidth = GridBagConstraints.RELATIVE;
//            c.insets = new Insets(10, 10, 5, 0);
//            gb.setConstraints(locationLabel, c);
//            contentPane.add(locationLabel);
//        }
//
//        if(city != null){
//            c.gridwidth = GridBagConstraints.RELATIVE;
//            c.insets = new Insets(10, 10, 5, 0);
//            gb.setConstraints(cityLabel, c);
//            contentPane.add(cityLabel);
//        }
//
        //cancel button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        gb.setConstraints(cancelButton, c);
        contentPane.add(cancelButton);

        cancelButton.addActionListener(this);

        frame.pack();
        frame.setVisible(true);
    }

    private Customer handleCustomerInfo(){
        int phone = Integer.parseInt(phoneNum);
        Customer c = new Customer(phone, name,address,dlicense);
        CustomersController.addCustomer(c);
        return c;
    }
    private void reservationMenu(){
        frame.getContentPane().removeAll();
        frame.repaint();

        JLabel dlicenseLabel = new JLabel("Driver's License (required):  ");
        JLabel nameLabel = new JLabel("Name (required): ");
        dlicenseField = new JTextField(TEXT_FIELD_WIDTH);
        nameField = new JTextField(TEXT_FIELD_WIDTH);

        submitCustButton = new JButton("SUBMIT");

        // from time
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 0);
        gb.setConstraints(nameLabel, c);
        contentPane.add(nameLabel);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(10, 0, 5, 10);
        gb.setConstraints(nameField, c);
        contentPane.add(nameField);

        // to date
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 0);
        gb.setConstraints(dlicenseLabel, c);
        contentPane.add(dlicenseLabel);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(10, 0, 5, 10);
        gb.setConstraints(dlicenseField, c);
        contentPane.add(dlicenseField);


        // submit button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        gb.setConstraints(submitCustButton, c);
        contentPane.add(submitCustButton);

        submitCustButton.addActionListener(this);

        frame.pack();
        nameField.requestFocus();
        frame.setVisible(true);
    }

    private void getCustomerInfo(){
        frame.getContentPane().removeAll();
        frame.repaint();

        JLabel addressLabel = new JLabel("Address (required):  ");
        JLabel phoneLabel = new JLabel("Phone Number (required): ");
        addressField = new JTextField(TEXT_FIELD_WIDTH);
        phoneNumField = new JTextField(TEXT_FIELD_WIDTH);

        submitInfoButton = new JButton("SUBMIT");

        // from time
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 0);
        gb.setConstraints(addressLabel, c);
        contentPane.add(addressLabel);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(10, 0, 5, 10);
        gb.setConstraints(addressField, c);
        contentPane.add(addressField);

        // to date
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 0);
        gb.setConstraints(phoneLabel, c);
        contentPane.add(phoneLabel);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(10, 0, 5, 10);
        gb.setConstraints(phoneNumField, c);
        contentPane.add(phoneNumField);


        // submit button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        gb.setConstraints(submitInfoButton, c);
        contentPane.add(submitInfoButton);

        submitCustButton.addActionListener(this);

        frame.pack();
        nameField.requestFocus();
        frame.setVisible(true);

    }
}

