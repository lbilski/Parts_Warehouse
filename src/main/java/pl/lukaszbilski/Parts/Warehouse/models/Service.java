package pl.lukaszbilski.Parts.Warehouse.models;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

    public static void allowTableToBeCopy(TableView<?> table){
        table.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.C && event.isControlDown()){

                    ObservableList<TablePosition> positionList = table.getSelectionModel().getSelectedCells();

                    int row = positionList.get(0).getRow();
                    int col = positionList.get(0).getColumn();

                    Object cell = (Object) table.getColumns().get(col).getCellData(row);

                    final Clipboard clipboard = Clipboard.getSystemClipboard();
                    final ClipboardContent content = new ClipboardContent();
                    content.putString(cell.toString());
                    clipboard.setContent(content);
                }
            }
        });
    }
}
