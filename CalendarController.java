package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class CalendarController {
	
	// All of the panes for the day slots
	@FXML
	private Pane D1;
	@FXML
	private Pane D2;
	@FXML
	private Pane D3;
	@FXML
	private Pane D4;
	@FXML
	private Pane D5;
	@FXML
	private Pane D6;
	@FXML
	private Pane D7;
	@FXML
	private Pane D8;
	@FXML
	private Pane D9;
	@FXML
	private Pane D10;
	@FXML
	private Pane D11;
	@FXML
	private Pane D12;
	@FXML
	private Pane D13;
	@FXML
	private Pane D14;
	@FXML
	private Pane D15;
	@FXML
	private Pane D16;
	@FXML
	private Pane D17;
	@FXML
	private Pane D18;
	@FXML
	private Pane D19;
	@FXML
	private Pane D20;
	@FXML
	private Pane D21;
	@FXML
	private Pane D22;
	@FXML
	private Pane D23;
	@FXML
	private Pane D24;
	@FXML
	private Pane D25;
	@FXML
	private Pane D26;
	@FXML
	private Pane D27;
	@FXML
	private Pane D28;
	@FXML
	private Pane D29;
	@FXML
	private Pane D30;
	@FXML
	private Pane D31;
	@FXML
	private Pane D32;
	@FXML
	private Pane D33;
	@FXML
	private Pane D34;
	@FXML
	private Pane D35;
	@FXML
	private Pane D36;
	@FXML
	private Pane D37;
	@FXML
	private Pane D38;
	@FXML
	private Pane D39;
	@FXML
	private Pane D40;
	@FXML
	private Pane D41;
	@FXML
	private Pane D42;
	
	// The panel that lists the events of the selected day
	@FXML
	private Pane EventPanel;
	
	// The object within the panel that stores the events
	@FXML
	private Accordion EventList;
	
	// The label indicating which month is being displayed
	@FXML
	private Label MonthLabel;
	
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
	
	// Display previous month
	public void PrevMonth (ActionEvent event) {
		
	}
	
	// Display next month
	public void NextMonth (ActionEvent event) {
		
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
	
}
