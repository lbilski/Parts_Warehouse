package pl.lukaszbilski.Parts.Warehouse.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.lukaszbilski.Parts.Warehouse.models.models.*;
import pl.lukaszbilski.Parts.Warehouse.models.repositories.*;

import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;

@Controller
public class NewOrderController implements Initializable{

    @FXML
    Button saveAndExit, saveAndContinue;
    @FXML
    DatePicker dateOrder, dateAssembly;
    @FXML
    MenuButton cityMenu, carsMenu, partsMenu, wholesaleMenu;
    @FXML
    TextArea notes;
    @FXML
    TextField numberOrder, quantity, unit, vin;

    @Autowired
    CompanyBranchRepository companyBranchRepository;
    @Autowired
    CarsRepository carsRepository;
    @Autowired
    PartsRepository partsRepository;
    @Autowired
    WholesaleRepository wholesaleRepository;
    @Autowired
    OrdersRepository ordersRepository;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCityMenu();
        setCarsMenu();
        setPartsMenu();
        setWholesaleMenu();
    }

    private void setCityMenu(){
        for(CompanyBranchModel city:companyBranchRepository.findAllByOrderByCity()) {
            MenuItem newItem =
                    MenuItemBuilder.create()
                            .text(city.getCity())
                            .onAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    cityMenu.setText(city.getCity());
                                }
                            })
                            .build();
            cityMenu.getItems().add(newItem);
        }
    }

    private void setCarsMenu(){
        for(CarsModel car:carsRepository.findAllByOrderByName()) {
            MenuItem newItem =
                    MenuItemBuilder.create()
                            .text(car.getName())
                            .onAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    carsMenu.setText(car.getName());
                                }
                            })
                            .build();
            carsMenu.getItems().add(newItem);
        }
    }

    private void setPartsMenu(){
        for(PartsModel part:partsRepository.findAllByOrderByName()) {
            MenuItem newItem =
                    MenuItemBuilder.create()
                            .text(part.getName())
                            .onAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    partsMenu.setText(part.getName());
                                    unit.setText(part.getUnit());
                                }
                            })
                            .build();
            partsMenu.getItems().add(newItem);
        }
    }

    private void setWholesaleMenu(){
        for(WholesaleModel wholesale:wholesaleRepository.findAll()) {
            MenuItem newItem =
                    MenuItemBuilder.create()
                            .text(wholesale.getName())
                            .onAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    wholesaleMenu.setText(wholesale.getName());
                                }
                            })
                            .build();
            wholesaleMenu.getItems().add(newItem);
        }
    }

    public void saveAndExit(){
        OrdersModel modelToSend = newModel();
        ordersRepository.save(modelToSend);

        Stage stageToClose = (Stage) saveAndExit.getScene().getWindow();
        stageToClose.close();
    }

    public void saveAndNext(){
        partsMenu.setText("Nazwa części");
        unit.setText("");
    }

    private OrdersModel newModel(){
        OrdersModel model = new OrdersModel();

        model.setCity(cityMenu.getText());
        model.setWholesale(wholesaleMenu.getText());
        model.setDate_order(Date.valueOf(dateOrder.getValue()));
        model.setNumber_order(numberOrder.getText());
        model.setCar(carsMenu.getText());
        model.setPart(partsMenu.getText());
        model.setUnit(unit.getText());
        model.setQuantity(Integer.parseInt(quantity.getText()));
        model.setId_vin(vin.getText());

        if(dateAssembly.getValue()==null){
            model.setAssembly(null);
        }else{
            model.setAssembly(Date.valueOf(dateAssembly.getValue()));
        }

        model.setNotes(notes.getText());

        return model;
    }
}
