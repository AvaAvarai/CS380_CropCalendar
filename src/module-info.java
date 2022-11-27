module CS380FinalProject {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires java.sql;
	
	opens calendar to javafx.graphics, javafx.fxml;
}