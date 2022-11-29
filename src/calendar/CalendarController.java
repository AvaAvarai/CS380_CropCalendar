package calendar;

import javafx.fxml.FXML;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CalendarController {
	
	// All of the panes for the day slots
	@FXML
	private HBox DaySlots;
	
	// The panel that lists the events of the selected day
	@FXML
	private Pane EventPanel;
	// The panel used to log in and register
	@FXML
	private Pane LoginPanel;
	
	// The object within the panel that stores the events
	@FXML
	private Accordion EventList;
	
	// The label indicating which month is being displayed
	@FXML
	private Label MonthLabel;
	// The label indicating if a login/register was successful
	@FXML
	private Label Status;
	
	// The buttons for changing the displayed month
	@FXML
	private Button PrevMonthBtn;
	@FXML
	private Button NextMonthBtn;
	// The button to close the EventPanel
	@FXML
	private Button ClosePanelBtn;
	// The button to add an event
	@FXML
	private Button AddEventBtn;
	// The button to attempt a login
	@FXML
	private Button LoginBtn;
	// The button to attempt a register
	@FXML
	private Button RegisterBtn;
	
	// TextField for the login/register username input
	@FXML
	private TextField User;
	
	// TextField for the login/register password input
	@FXML
	private PasswordField Pass;
	
	private Calendar cal = Calendar.getInstance();
	
	private final String[] Months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	
	// Display previous month
	public void PrevMonth (ActionEvent event) {
		cal.add(Calendar.MONTH, -1);
		UpdateCalendar();
	}
	
	// Display next month
	public void NextMonth (ActionEvent event) {
		cal.add(Calendar.MONTH, 1);
		UpdateCalendar();
	}
	
	// Opens the panel displaying the events for the selected day
	public void OpenDayInfo (ActionEvent event) {
		
	}
	
	// Closes the panel displaying events of the selected day
	public void CloseDayInfo (ActionEvent event) {
		
	}
	
	// Adds an event to the selected day
	public void AddEvent (ActionEvent event) {
		
	}
	
	// Register an account
	public void Register (ActionEvent event) throws Exception {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/farmers", "root", "cs380");
			
			// NEED TO UPDATE THIS
			
			String query = "UPDATE ? from farmer where pass=?;";
			
			PreparedStatement ps = connection.prepareStatement(query);
			
			ps.setString(1, User.getText());
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				if (rs.getString("name").equals(Pass.getText())) {
					Status.setText("Register Success!");
				}
			}
			rs.close();
			ps.close();
			
		} catch (SQLException exception) {
			System.out.println("Error while connecting to the database");
		}
		
			// END NEED TO UPDATE SECTION
	}
	
	// Login to an account
	public void Login (ActionEvent event) throws Exception {
		try {
			// open connection
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/farmers", "root", "cs380");
			
			// select users
			String query = "SELECT * FROM farmer";
			
			// prepare statement
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			// compare email/pass pairs
			while (rs.next()) {
				String email = rs.getString("email");
				String password = rs.getString("pass");
				
				// user found
				if (User.getText().equals(email) && Pass.getText().equals(password)) {
					connection.close();
					Status.setText("Success");
					LoginPanel.setVisible(false);
					return;
				}
			}
			
            // no user found
			Status.setText("Invalid Credentials");
			rs.close();
			st.close();
			connection.close();
			
		} catch (SQLException exception) {
			System.out.println("Error while connecting to the database");
			exception.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Populates the app with correct data
	public void UpdateCalendar () {
		// Update the month label
		MonthLabel.setText(Months[cal.get(Calendar.MONTH)]);
		LoginPanel.setVisible(true);
		// Update the days of the month
		Calendar temp = (Calendar) cal.clone();
		// Set temp to be the first day of the current month
		temp.add(Calendar.DATE, -(temp.get(Calendar.DATE) - 1));
		// Determine temp's day of the week
		int dayOfWeek = temp.get(Calendar.DAY_OF_WEEK) - 1;
		// Use an array to organize the day numbers within the calendars scope
		int[] days = new int[42];
		// Second array to determine if a day is of the current month or an adjacent one
		boolean[] darken = new boolean[42];
		// Loop through until the numbers in this current month are filled
		int monthBorder = 0;
		for (int i = 0; i < temp.getActualMaximum(Calendar.DATE); i++) {
			days[dayOfWeek + i] = i + 1;
			monthBorder = dayOfWeek + i + 1;
		}
		// Use monthBorder to determine where to fill the following month's days
		days[monthBorder] = 1;
		darken[monthBorder] = true;
		// fill in the days proceeding the current month
		for (int i = monthBorder + 1; i < 42; i++) {
			days[i] = days[i - 1] + 1;
			darken[i] = true;
		}
		// Go to last day of the previous month
		temp.add(Calendar.MONTH, -1);
		temp.add(Calendar.DATE, temp.getActualMaximum(Calendar.DATE) - 1);
		// Fill in the days preceding the current month
		if (dayOfWeek > 0) {
			days[dayOfWeek - 1] = temp.get(Calendar.DATE);
			darken[dayOfWeek - 1] = true;
			for (int i = dayOfWeek - 2; i > -1; i--) {
				days[i] = days[i + 1] - 1;
				darken[i] = true;
			}
		}
		// Apply the data from the array to the calendar
		// Get the VBoxes for days of the week from the DaySlots HBox
		int iter = 0;
		for (Node i : DaySlots.getChildren()) {
			if (i instanceof VBox) {
				VBox j = (VBox) i;
				for (Node k : j.getChildren()) {
					if (k instanceof Pane) {
						Pane daySlot = (Pane) k;
						Rectangle background = (Rectangle) daySlot.getChildren().get(0);
						if (darken[iter]) {
							background.setFill(Color.LIGHTGRAY);
						} else {
							background.setFill(Color.WHITE);
						}
						Label dayNum = (Label) daySlot.getChildren().get(1);
						dayNum.setText("" + days[iter]);
						// Iterate by 7 after each Pane, moving down the VBox
						iter += 7;
					}
				}
			}
			// 7*6 - 1 = 41, thus iterating by 1 after each VBox
			iter -= 41;
		}
		
	}
	
	// Runs on startup
	public void initialize () {
		UpdateCalendar();
	}
	
}
