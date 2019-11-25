package ui;

import controller.VehiclesController;
import model.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;


public class AvailableVehicles extends JFrame implements ActionListener {
    private static final int TEXT_FIELD_WIDTH = 30;
    private JButton submitButton;
    private JButton viewButton;
    private JButton menuButton;

    private JTextField carTypeField;
    private JTextField locationField;
    private JTextField fromDateField;
    private JTextField fromTimeField;
    private JTextField toDateField;
    private JTextField toTimeField;
    private JTextField cityField;


    private String carType;
    private String location;
    private String fromDate;
    private String fromTime;
    private String toDate;
    private String toTime;
    private String city;

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
        JLabel fromDateLabel = new JLabel("Pickup Date: ");
        JLabel fromTimeLabel = new JLabel("Pickup Time: ");
        JLabel toDateLabel = new JLabel("Drop-off Date: ");
        JLabel toTimeLabel = new JLabel("Drop-off Time: ");

        carTypeField = new JTextField(TEXT_FIELD_WIDTH);
        locationField = new JTextField(TEXT_FIELD_WIDTH);
        fromDateField = new JTextField(TEXT_FIELD_WIDTH);
        fromTimeField = new JTextField(TEXT_FIELD_WIDTH);
        toDateField = new JTextField(TEXT_FIELD_WIDTH);
        toTimeField = new JTextField(TEXT_FIELD_WIDTH);
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

        // place the from date label
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 0);
        gb.setConstraints(fromDateLabel, c);
        contentPane.add(fromDateLabel);

        // place the text field for the from date
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(10, 0, 5, 10);
        gb.setConstraints(fromDateField, c);
        contentPane.add(fromDateField);

      //from time
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 0);
        gb.setConstraints(fromTimeLabel, c);
        contentPane.add(fromTimeLabel);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(10, 0, 5, 10);
        gb.setConstraints(fromTimeField, c);
        contentPane.add(fromTimeField);

        //to date
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 0);
        gb.setConstraints(toDateLabel, c);
        contentPane.add(toDateLabel);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(10, 0, 5, 10);
        gb.setConstraints(toDateField, c);
        contentPane.add(toDateField);

        // to time
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 0);
        gb.setConstraints(toTimeLabel, c);
        contentPane.add(toTimeLabel);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(10, 0, 5, 10);
        gb.setConstraints(toTimeField, c);
        contentPane.add(toTimeField);


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
            fromDate = fromDateField.getText();
            fromTime = fromTimeField.getText();
            toDate = toDateField.getText();
            toTime = toTimeField.getText();
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
        vehicles = VehiclesController.getAvailableVehicles(carType, location, city);

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
