module javaFX_1 {
	requires javafx.controls;
	
	opens application to javafx.graphics, javafx.fxml;
}
