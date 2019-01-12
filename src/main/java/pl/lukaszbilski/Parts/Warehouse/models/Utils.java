package pl.lukaszbilski.Parts.Warehouse.models;

import javafx.scene.control.Alert;

public class Utils {
    public void infoMessage(String title, String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
