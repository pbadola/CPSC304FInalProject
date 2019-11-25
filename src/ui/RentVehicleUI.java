package ui;


import controller.RentalsController;
import controller.ReservationsController;
import model.Rental;
import model.Reservation;
import model.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class RentVehicleUI extends JFrame implements ActionListener {
    private static final int TEXT_FIELD_WIDTH = 30;
    private JButton submitButton;

    private JTextField dlicenseField;
    private JTextField confNoField;
    private JTextField cardNoField;
    private JTextField cardNameField;
    private JTextField expDateField;

    private String dlicense;
    private String confNo;
    private String cardName;
    private String expDate;
    private String cardNo;


    private JFrame frame;
    private JPanel contentPane;
    private GridBagLayout gb;
    private  GridBagConstraints c;

    RentVehicleUI(JFrame frame){
        super("Make a Rental");
        this.frame = frame;
    }

    void showMenu(){
        frame.getContentPane().removeAll();
        frame.repaint();

        JLabel dlicenseLabel = new JLabel("Driver's License: ");
        JLabel confNoLabel = new JLabel("Confirmation Number: ");

        dlicenseField = new JTextField(TEXT_FIELD_WIDTH);
        confNoField = new JTextField(TEXT_FIELD_WIDTH);

        submitButton = new JButton("SUBMIT");

        contentPane = new JPanel();
        frame.setContentPane(contentPane);

        // layout components using the GridBag layout manager
        gb = new GridBagLayout();
        c = new GridBagConstraints();

        contentPane.setLayout(gb);
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // dlicense
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 0);
        gb.setConstraints(dlicenseLabel, c);
        contentPane.add(dlicenseLabel);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(10, 0, 5, 10);
        gb.setConstraints(dlicenseField, c);
        contentPane.add(dlicenseField);

        // confNo
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 0);
        gb.setConstraints(confNoLabel, c);
        contentPane.add(confNoLabel);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(10, 0, 5, 10);
        gb.setConstraints(confNoField, c);
        contentPane.add(confNoField);

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

        dlicenseField.requestFocus();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submitButton){

            dlicense = dlicenseField.getText();
            confNo = confNoField.getText();
            makeRental();

        }
    }

    //TODO: Complete this method.
    public void makeRental(){
        frame.getContentPane().removeAll();
        frame.repaint();
        JLabel cardNoLabel;
        JLabel cardNameLabel;
        JLabel expDateLabel;
        submitButton = new JButton("SUBMIT");
        contentPane = new JPanel();
        frame.setContentPane(contentPane);

        try {
            int confNo_int = Integer.parseInt(confNo);
            Reservation existingRes = ReservationsController.getReservation(confNo_int);
            if (existingRes == null) {
                System.out.println("main reservation");
                ReservationUI ru = new ReservationUI(frame);
                ru.showMenu();
            }
            else {
                ArrayList<Vehicle> availableVehicles = null;
                String vtname = existingRes.getVtname();
                // TODO: fix this back to using reservation
                //     String location = existingRes.getLocation();
                //     String city = existingRes.getCity();
                //     availableVehicles = VehiclesController.getAvailableVehicles(vtname, location, city);
                String city = "Vancouver";
                String location = "525 W Broadway";
                if (availableVehicles == null) {
                    JLabel novehicles = new JLabel("Sorry, no vehicles of this type available at this branch.");
                    System.out.println("if avaliable vehicles = null");
                }
                else {
                    cardNoLabel = new JLabel("Card Number: ");
                    cardNameLabel = new JLabel("Card Name: ");
                    expDateLabel = new JLabel("Expiry Date: ");

                    cardNoField = new JTextField(TEXT_FIELD_WIDTH);
                    cardNameField = new JTextField(TEXT_FIELD_WIDTH);
                    expDateField = new JTextField(TEXT_FIELD_WIDTH);

                    submitButton = new JButton("SUBMIT");

                    contentPane = new JPanel();
                    frame.setContentPane(contentPane);

                    // layout components using the GridBag layout manager
                    gb = new GridBagLayout();
                    c = new GridBagConstraints();

                    contentPane.setLayout(gb);
                    contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                    // cardNo
                    c.gridwidth = GridBagConstraints.RELATIVE;
                    c.insets = new Insets(10, 10, 5, 0);
                    gb.setConstraints(cardNoLabel, c);
                    contentPane.add(cardNoLabel);

                    c.gridwidth = GridBagConstraints.REMAINDER;
                    c.insets = new Insets(10, 0, 5, 10);
                    gb.setConstraints(cardNoField, c);
                    contentPane.add(cardNoField);

                    // cardName
                    c.gridwidth = GridBagConstraints.RELATIVE;
                    c.insets = new Insets(10, 10, 5, 0);
                    gb.setConstraints(cardNameLabel, c);
                    contentPane.add(cardNameLabel);


                    c.gridwidth = GridBagConstraints.REMAINDER;
                    c.insets = new Insets(10, 0, 5, 10);
                    gb.setConstraints(cardNameField, c);
                    contentPane.add(cardNameField);

                    // expDate
                    c.gridwidth = GridBagConstraints.RELATIVE;
                    c.insets = new Insets(10, 10, 5, 0);
                    gb.setConstraints(expDateLabel, c);
                    contentPane.add(expDateLabel);


                    c.gridwidth = GridBagConstraints.REMAINDER;
                    c.insets = new Insets(10, 0, 5, 10);
                    gb.setConstraints(expDateField, c);
                    contentPane.add(expDateField);

                    // submit button
                    c.gridwidth = GridBagConstraints.REMAINDER;
                    c.insets = new Insets(5, 10, 10, 10);
                    c.anchor = GridBagConstraints.CENTER;
                    gb.setConstraints(submitButton, c);
                    contentPane.add(submitButton);

                    // TODO: frame or this?
                    submitButton.addActionListener(this);

                    // anonymous inner class for closing the window
                    frame.addWindowListener(new WindowAdapter() {
                        public void windowClosing(WindowEvent e) {
                            System.exit(0);
                        }
                    });

                    dlicenseField.requestFocus();

                    String dlicense = existingRes.getDlicense();
                    String fromDate = existingRes.getPickUpDate();
                    String toDate = existingRes.getDropOffDate();
                    String fromTime = existingRes.getPickUpTime();
                    String toTime = existingRes.getDropOffTime();
                    String cardName_input = cardNameField.getText();
                    // TODO: fix card no back to string later
                    // String cardNo_input = cardNoField.getText();
                    int cardNo_input = Integer.parseInt(cardNoField.getText());
                    String expDate_input = expDateField.getText();
                    int confNo = existingRes.getConfNo();
                    Vehicle chosen = availableVehicles.get(0);
                    String vlicense = chosen.getVlicense();
                    int odometer = chosen.getOdometer();



                    // TODO: fix card no back to string later
                    Rental model = new Rental(-1, vlicense, dlicense, fromDate, fromTime, toDate, toTime,
                            odometer, cardName_input, cardNo_input, expDate_input, confNo);
                    RentalsController.addRental(model);
                    System.out.println("before show receipt called");
                    showReceipt(vlicense, dlicense, fromDate, toDate, fromTime, toTime, Integer.toString(odometer),
                            cardName_input, Integer.toString(cardNo_input), expDate_input, Integer.toString(confNo));

                }


            }
        }
        catch (NumberFormatException e) {
            System.out.println("Please ensure Rental ID and Confirmation number are numbers not words");
        }
    }

    public void showReceipt(String vl, String dl, String fromDate, String toDate, String fromTime, String toTime,
                            String odometer, String cardName, String cardNo, String expiry, String confirm) {
        System.out.println("show me the money");
        frame.getContentPane().removeAll();
        frame.repaint();

        contentPane = new JPanel();
        frame.setContentPane(contentPane);

        // layout components using the GridBag layout manager
        gb = new GridBagLayout();
        c = new GridBagConstraints();

        contentPane.setLayout(gb);
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel headingLabel = new JLabel("RENTAL RECEIPT");

        JLabel vlicenseLabel = new JLabel("Location: "+ vl);
        JLabel dlicenseLabel = new JLabel("Driver's License: "+ dl);
        JLabel fromDateLabel = new JLabel("Pickup Date: "+ fromDate);
        JLabel fromTimeLabel = new JLabel("Pickup Time: "+ fromTime);
        JLabel toDateLabel = new JLabel("Drop-off Date: "+ toDate);
        JLabel toTimeLabel = new JLabel("Drop-off Time: "+ toTime);
        JLabel odometerLabel = new JLabel("Odometer reading: "+ odometer);
        JLabel cardNameLabel = new JLabel("Card Name: "+ cardName);
        JLabel cardNoLabel = new JLabel("Card Number: "+ cardNo);
        JLabel expDateLabel = new JLabel("Expiry Date: "+ expiry);
        JLabel confNoLabel = new JLabel("Confirmation Number: "+confirm);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        gb.setConstraints(headingLabel, c);
        contentPane.add(headingLabel);


        // vlicense
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 0);
        gb.setConstraints(vlicenseLabel, c);
        contentPane.add(vlicenseLabel);


        // dlicense
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 0);
        gb.setConstraints(dlicenseLabel, c);
        contentPane.add(dlicenseLabel);


        // from date
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 0);
        gb.setConstraints(fromDateLabel, c);
        contentPane.add(fromDateLabel);


        // from time
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 0);
        gb.setConstraints(fromTimeLabel, c);
        contentPane.add(fromTimeLabel);

        // to date
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 0);
        gb.setConstraints(toDateLabel, c);
        contentPane.add(toDateLabel);


        // to time
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 0);
        gb.setConstraints(toTimeLabel, c);
        contentPane.add(toTimeLabel);

        // odometer
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 0);
        gb.setConstraints(odometerLabel, c);
        contentPane.add(odometerLabel);

        // card name
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 0);
        gb.setConstraints(cardNameLabel, c);
        contentPane.add(cardNameLabel);

        // card number
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 0);
        gb.setConstraints(cardNoLabel, c);
        contentPane.add(cardNoLabel);


        // expiry date
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 0);
        gb.setConstraints(expDateLabel, c);
        contentPane.add(expDateLabel);


        // confNo
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 0);
        gb.setConstraints(confNoLabel, c);
        contentPane.add(confNoLabel);

        frame.pack();

        // center the frame
        Dimension d = frame.getToolkit().getScreenSize();
        Rectangle r = frame.getBounds();
        frame.setLocation( (d.width - r.width)/2, (d.height - r.height)/2 );

        // make the window visible
        frame.setVisible(true);


    }

}



