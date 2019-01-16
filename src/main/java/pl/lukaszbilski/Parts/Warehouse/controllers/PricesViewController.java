package pl.lukaszbilski.Parts.Warehouse.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.lukaszbilski.Parts.Warehouse.models.Service;
import pl.lukaszbilski.Parts.Warehouse.models.models.PricesModel;
import pl.lukaszbilski.Parts.Warehouse.models.repositories.PricesRepository;

import java.net.URL;
import java.util.ResourceBundle;


@Controller
public class PricesViewController implements Initializable{
    @FXML
    TableView<PricesModel> tablePrices;

    @FXML
    TableColumn<PricesModel, String> colCar, colName, colUnit, colCodeIC, colPriceIC, colCodeHart, colPriceHart;

    @Autowired
    PricesRepository pricesRepository;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setListPrices();

        Service.allowTableToBeCopy(tablePrices);

        tablePrices.getSelectionModel().setCellSelectionEnabled(true);
    }

    private void setListPrices(){
        ObservableList<PricesModel> allBranches = FXCollections.observableList(pricesRepository.findAll());

        colCar.setCellValueFactory(new PropertyValueFactory<>("car"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colUnit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        colCodeIC.setCellValueFactory(new PropertyValueFactory<>("code_Inter_Cars"));
        colPriceIC.setCellValueFactory(new PropertyValueFactory<>("price_Inter_Cars"));
        colCodeHart.setCellValueFactory(new PropertyValueFactory<>("code_Hart"));
        colPriceHart.setCellValueFactory(new PropertyValueFactory<>("price_Hart"));

        tablePrices.setItems(allBranches);
    }
}
