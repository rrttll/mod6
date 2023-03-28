module javaFX_1 {
	requires javafx.controls;
	requires org.junit.jupiter.api;
	
	opens application to javafx.graphics, javafx.fxml;
}
