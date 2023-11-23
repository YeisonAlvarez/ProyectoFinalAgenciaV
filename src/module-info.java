module AgenciaViajes {
	requires javafx.controls;
	requires lombok;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
	
	exports controlador to javafx.fxml;
	opens controlador to javafx.fxml,javafx.base;
	
	exports logica to javafx.fxml;
	opens logica to javafx.fxml,javafx.base;
}

