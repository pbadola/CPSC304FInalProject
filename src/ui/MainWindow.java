package ui;
import controller.ReturnsController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * This is the main menu of the program.
 */
public class MainWindow extends JFrame implements ActionListener {
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";

    private JButton available_vehicles;
    private JButton reservation;
    private JButton rent;
    private JButton vehicle_return;
    private JButton reports;
    private JButton tables;

    private JPanel contentPane;
    private BufferedReader bufferedReader = null;
    int choice = -1;

    public MainWindow() {
        super("Main User Menu");
    }


    public void showFrame() {
        getContentPane().removeAll();
        repaint();
        available_vehicles = new JButton("View Available Vehicles");
        reservation = new JButton("Reserve a Vehicle");
        rent = new JButton("Rent a Vehicle");
        vehicle_return = new JButton("Return Vehicle");
        reports = new JButton("Reports");
        tables = new JButton("Table Manipulation");

        contentPane = new JPanel();
        this.setContentPane(contentPane);

        // layout components using the GridBag layout manager
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        contentPane.setLayout(gb);
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // place the buttons on window
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        gb.setConstraints(available_vehicles, c);
        gb.setConstraints(reservation, c);
        gb.setConstraints(rent, c);
        gb.setConstraints(vehicle_return, c);
        gb.setConstraints(reports, c);
        gb.setConstraints(tables, c);



        contentPane.add(available_vehicles);
        contentPane.add(reservation);
        contentPane.add(rent);
        contentPane.add(vehicle_return);
        contentPane.add(reports);
        contentPane.add(tables);

        available_vehicles.addActionListener(this);
        reservation.addActionListener(this);
        rent.addActionListener(this);
        vehicle_return.addActionListener(this);
        reports.addActionListener(this);
        tables.addActionListener(this);


        // anonymous inner class for closing the window
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // size the window to obtain a best fit for the components
        this.pack();

        // center the frame
        Dimension d = this.getToolkit().getScreenSize();
        Rectangle r = this.getBounds();
        this.setLocation( (d.width - r.width)/2, (d.height - r.height)/2 );

        // make the window visible
        this.setVisible(true);

    }



    /**
     * ActionListener Methods
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == available_vehicles) {
            AvailableVehicles a = new AvailableVehicles(this);
            a.showMenu();
        }  else if (e.getSource() == reservation) {
            ReservationUI r = new ReservationUI(this);
            r.showMenu();
        }  else if (e.getSource() == rent) {
            RentVehicleUI rv = new RentVehicleUI(this);
            rv.showMenu();
        }  else if (e.getSource() == vehicle_return) {
            System.out.println("Return vehicle option was chosen.");
          //  returnVehicle();
        }  else if (e.getSource() == reports) {
            System.out.println("reports option was selected");
        } else
            System.out.println("table manipulation button was chosen");
    }

    private void returnVehicle() {
        int tempRid = -1;
        System.out.print("Please enter rid of vehicle you want to return: ");
        tempRid = readInt();
        if (tempRid != -1) {
            ReturnsController.getReturn(tempRid);
        }
    }

    private int readInt() {
        String line = null;
        int input = -1;
        try {
            line = bufferedReader.readLine();
            input = Integer.parseInt(line);
        } catch (IOException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println(WARNING_TAG + " Your input was not an integer");
            }
        return input;
        }

    }
