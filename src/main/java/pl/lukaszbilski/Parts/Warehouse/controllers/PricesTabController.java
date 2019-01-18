package pl.lukaszbilski.Parts.Warehouse.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import pl.lukaszbilski.Parts.Warehouse.models.Service;
import pl.lukaszbilski.Parts.Warehouse.models.models.CarsModel;
import pl.lukaszbilski.Parts.Warehouse.models.models.PartsModel;
import pl.lukaszbilski.Parts.Warehouse.models.models.PricesModel;
import pl.lukaszbilski.Parts.Warehouse.models.repositories.CarsRepository;
import pl.lukaszbilski.Parts.Warehouse.models.repositories.PartsRepository;
import pl.lukaszbilski.Parts.Warehouse.models.repositories.PricesRepository;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


@Controller
public class PricesTabController implements Initializable{
    @FXML
    TableView<PricesModel> tablePrices;

    @FXML
    MenuButton partsButton, carsButton;

    @FXML
    Button addButton;

    @FXML
    TableColumn<PricesModel, String> colCar, colName, colUnit, colCodeIC, colPriceIC, colCodeHart, colPriceHart, colNotes;

    @Autowired
    PricesRepository pricesRepository;
    @Autowired
    PartsRepository partsRepository;
    @Autowired
    CarsRepository carsRepository;
    @Autowired
    ApplicationContext applicationContext;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tablePrices.getSelectionModel().setCellSelectionEnabled(true);

        setPartsButtonItems();
        setCarsButtonItems();

        Service.allowTableToBeCopy(tablePrices);

       setListPrices(FXCollections.observableList(pricesRepository.findAll()));
    }

    public void displayAll(){
        setListPrices(FXCollections.observableList(pricesRepository.findAll()));
    }


    private void setListPrices(ObservableList<PricesModel> list){

        colCar.setCellValueFactory(new PropertyValueFactory<>("car"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colUnit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        colCodeIC.setCellValueFactory(new PropertyValueFactory<>("code_Inter_Cars"));
        colPriceIC.setCellValueFactory(new PropertyValueFactory<>("price_Inter_Cars"));
        colCodeHart.setCellValueFactory(new PropertyValueFactory<>("code_Hart"));
        colPriceHart.setCellValueFactory(new PropertyValueFactory<>("price_Hart"));
        colNotes.setCellValueFactory(new PropertyValueFactory<>("notes"));

        tablePrices.setItems(list);
    }

    private void setCarsButtonItems(){
        for(CarsModel car:carsRepository.findAllByOrderByName()){
            MenuItem newItem =
                    MenuItemBuilder.create()
                    .text(car.getName())
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            setListPrices(FXCollections.observableList(pricesRepository.findAllByCar(car.getName())));
                        }
                    })
                    .build();
            carsButton.getItems().add(newItem);
        }
    }

    private void setPartsButtonItems(){
        for(PartsModel part:partsRepository.findAllByOrderByName()) {
            MenuItem newItem =
                    MenuItemBuilder.create()
                    .text(part.getName())
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            setListPrices(FXCollections.observableList(pricesRepository.findAllByName(part.getName())));
                        }
                    })
                    .build();
            partsButton.getItems().add(newItem);
        }
    }

    public void addNewItem(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/newPriceItem.fxml"));
            fxmlLoader.setControllerFactory(applicationContext::getBean);
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(addButton.getScene().getWindow());
            stage.showAndWait();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
