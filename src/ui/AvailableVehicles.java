package ui;

import controller.VehiclesController;
import model.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Timestamp;
import java.util.ArrayList;


public class AvailableVehicles extends JFrame implements ActionListener {
    private static final int TEXT_FIELD_WIDTH = 30;
    private JButton submitButton;
    private JButton viewButton;
    private JButton menuButton;

    private JTextField carTypeField;
    private JTextField locationField;
    private JTextField fromDateTimeField;
    private JTextField toDateTimeField;
    private JTextField cityField;


    private String carType;
    private String location;
    private String fromDateTime;
    private String toDateTime;
    private String city;
    private Timestamp fromTimestamp;
    private Timestamp toTimestamp;

    private JFrame frame;
    private JPanel contentPane;
    private GridBagLayout gb;
    private  GridBagConstraints c;

    private ArrayList<Vehicle> vehicles = null;


    AvailableVehicles(JFrame frame){
        super("Available Vehicle Menu");
        this.frame = frame;
    }
    void showMenu(){
        frame.getContentPane().removeAll();
        frame.repaint();

        JLabel carTypeLabel = new JLabel("Car type: ");
        JLabel locationLabel = new JLabel("Location: ");
        JLabel cityLabel = new JLabel("City: ");
        JLabel fromDateTimeLabel = new JLabel("Pickup Date and Time: ");
        JLabel toDateTimeLabel = new JLabel("Drop-off Date and Time: ");

        carTypeField = new JTextField(TEXT_FIELD_WIDTH);
        locationField = new JTextField(TEXT_FIELD_WIDTH);
        fromDateTimeField = new JTextField(TEXT_FIELD_WIDTH);
        toDateTimeField = new JTextField(TEXT_FIELD_WIDTH);
        cityField = new JTextField(TEXT_FIELD_WIDTH);

        submitButton = new JButton("SUBMIT");

        contentPane = new JPanel();
        frame.setContentPane(contentPane);

        // layout components using the GridBag layout manager
        gb = new GridBagLayout();
        c = new GridBagConstraints();

        contentPane.setLayout(gb);
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // place the car type label
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 0);
        gb.setConstraints(carTypeLabel, c);
        contentPane.add(carTypeLabel);

        // place the text field for the car type
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(10, 0, 5, 10);
        gb.setConstraints(carTypeField, c);
        contentPane.add(carTypeField);


        // place the location label
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 0);
        gb.setConstraints(locationLabel, c);
        contentPane.add(locationLabel);

        // place the text field for the location
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(10, 0, 5, 10);
        gb.setConstraints(locationField, c);
        contentPane.add(locationField);

        // place the city label
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 0);
        gb.setConstraints(cityLabel, c);
        contentPane.add(cityLabel);

        // place the text field for the location
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(10, 0, 5, 10);
        gb.setConstraints(cityField, c);
        contentPane.add(cityField);

        // place the from date and time label
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 0);
        gb.setConstraints(fromDateTimeLabel, c);
        contentPane.add(fromDateTimeLabel);

        // place the text field for the from date and time
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(10, 0, 5, 10);
        gb.setConstraints(fromDateTimeField, c);
        contentPane.add(fromDateTimeField);

        //to date
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 0);
        gb.setConstraints(toDateTimeLabel, c);
        contentPane.add(toDateTimeLabel);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(10, 0, 5, 10);
        gb.setConstraints(toDateTimeField, c);
        contentPane.add(toDateTimeField);


        //submit button
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

        frame.setVisible(true);

        // place the cursor in the text field for the username
        carTypeField.requestFocus();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submitButton){

            carType = carTypeField.getText();
            location = locationField.getText();
            fromDateTime = fromDateTimeField.getText();
            toDateTime = toDateTimeField.getText();
            city = cityField.getText();
            showResultsCount();

        } else if(e.getSource() == menuButton){
           MainWindow mw = new MainWindow();
           mw.showFrame();
        }else if(e.getSource() == viewButton){
            showCompleteResults();
        }

    }
    private void showResultsCount(){
        frame.getContentPane().removeAll();
        frame.repaint();
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
        if(!fromDateTime.isBlank() && !fromDateTime.isBlank()){
            fromTimestamp = Timestamp.valueOf(fromDateTime);
            toTimestamp = Timestamp.valueOf(toDateTime);
        } else {
            fromTimestamp = null;
            toTimestamp = null;
        }
        System.out.println("Before getting the vehicles");
        vehicles = VehiclesController.getAvailableVehicles(carType, location, city, toTimestamp, fromTimestamp);
        System.out.println("After getting the vehicles");
        //TODO: Reconsider whether the count query is what is needed. I think we should do the query
        int count = vehicles.size();

        JLabel availableLabel = new JLabel("There are "+ count + " vehicles available.");
        viewButton = new JButton("VIEW AVAILABLE VEHICLES");
        menuButton = new JButton("MAIN MENU");

        // place the login button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        gb.setConstraints(availableLabel, c);
        contentPane.add(availableLabel);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        contentPane.add(viewButton);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        contentPane.add(menuButton);

        viewButton.addActionListener(this);
        menuButton.addActionListener(this);

        frame.pack();
        frame.setVisible(true);

    }

    private void showCompleteResults(){
        frame.getContentPane().removeAll();
        frame.repaint();

        JLabel headingLabel = new JLabel("**** AVAILABLE VEHICLES****");
        JList<Vehicle> availableVehicles = null;

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        gb.setConstraints(headingLabel, c);
        contentPane.add(headingLabel);


        String vehicleString = "";
        for(Vehicle vehicle: vehicles){
            vehicleString += vehicle.toString()+"\n\n";
        }

        JTextArea textArea = new JTextArea(vehicleString);

        //c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 0);
        gb.setConstraints(textArea, c);
        contentPane.add(textArea);

        contentPane.add(Box.createHorizontalStrut(10));

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        contentPane.add(menuButton);

        menuButton.addActionListener(this);

        frame.pack();
        frame.setVisible(true);
    }
}
