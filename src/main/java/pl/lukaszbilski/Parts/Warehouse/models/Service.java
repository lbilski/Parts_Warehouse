package pl.lukaszbilski.Parts.Warehouse.models;

import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.text.Text;

public class Service {
    public static void infoMessage(String title, String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    public static void setWrapCellFactory(TableColumn<Object, String> table) {
        table.setCellFactory(tablecol -> {
            TableCell<Object, String> cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(table.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });
    }
}
