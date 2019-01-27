package pl.lukaszbilski.Parts.Warehouse.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.lukaszbilski.Parts.Warehouse.models.models.CarsModel;
import pl.lukaszbilski.Parts.Warehouse.models.models.CompanyBranchModel;
import pl.lukaszbilski.Parts.Warehouse.models.models.OrdersModel;
import pl.lukaszbilski.Parts.Warehouse.models.models.PartsModel;
import pl.lukaszbilski.Parts.Warehouse.models.repositories.CarsRepository;
import pl.lukaszbilski.Parts.Warehouse.models.repositories.CompanyBranchRepository;
import pl.lukaszbilski.Parts.Warehouse.models.repositories.OrdersRepository;
import pl.lukaszbilski.Parts.Warehouse.models.repositories.PartsRepository;


import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

@Controller
public class OrdersTabController implements Initializable{
    @FXML
    TableView<OrdersModel> tableOrders;

    @FXML
    TableColumn<OrdersModel, String> colCity, colWholesale, colNumberOrder, colCar, colPartName, colUnit, colVIN, colNotes;

    @FXML
    TableColumn<OrdersModel, Integer>colQuantity;

    @FXML
    TableColumn<OrdersModel, Date>colDateOrder, colDateAssembly;

    @FXML
    Button sort;

    @FXML
    MenuButton cityMenu, carsMenu, assemblyMenu, partsMenu;

    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    CarsRepository carsRepository;
    @Autowired
    CompanyBranchRepository companyBranchRepository;
    @Autowired
    PartsRepository partsRepository;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setListOrders(FXCollections.observableList(ordersRepository.findAllByCityLikeAndCarLikeAndPartLikeAndAssemblyIsNull("Kraków","%", "%")));
        cityMenu.setText("Kraków");
        assemblyMenu.setText("Niezamontowane");
        setCarsMenu();
        setCityMenu();
        setPartsMenu();
    }

    private void setListOrders(ObservableList<OrdersModel> list){
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colWholesale.setCellValueFactory(new PropertyValueFactory<>("wholesale"));
        colDateOrder.setCellValueFactory(new PropertyValueFactory<>("date_order"));
        colNumberOrder.setCellValueFactory(new PropertyValueFactory<>("number_order"));
        colCar.setCellValueFactory(new PropertyValueFactory<>("car"));
        colPartName.setCellValueFactory(new PropertyValueFactory<>("part"));
        colUnit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colVIN.setCellValueFactory(new PropertyValueFactory<>("id_vin"));
        colDateAssembly.setCellValueFactory(new PropertyValueFactory<>("assembly"));
        colNotes.setCellValueFactory(new PropertyValueFactory<>("notes"));

        tableOrders.setItems(list);
    }

    public void sorting(){
        String city;
        String car;
        String part;


        if(cityMenu.getText().equals("Wszystkie miasta")){
            city = "%";
        }else{
            city = cityMenu.getText();
        }

        if(carsMenu.getText().equals("Wszystkie pojazdy")){
            car = "%";
        }else{
            car = carsMenu.getText();
        }

        if(partsMenu.getText().equals("Wszystkie części")){
            part = "%";
        }else {
            part = partsMenu.getText();
        }

        switch (assemblyMenu.getText()){
            case "Wszystkie zamówienia" :  setListOrders(FXCollections.observableList(ordersRepository.findAllByCityLikeAndCarLikeAndPartLike(city,car, part))); break;
            case "Zamontowane" : setListOrders(FXCollections.observableList(ordersRepository.findAllByCityLikeAndCarLikeAndPartLikeAndAssemblyNotNull(city, car, part))); break;
            case "Niezamontowane" : setListOrders(FXCollections.observableList(ordersRepository.findAllByCityLikeAndCarLikeAndPartLikeAndAssemblyIsNull(city,car, part))); break;
        }
    }

    public void setPartMenuDefault(){
        partsMenu.setText("Wszystkie części");
    }

    public void setCityMenuDefault(){
        cityMenu.setText("Wszystkie miasta");
    }

    public void setCarMenuDefault(){
        carsMenu.setText("Wszystkie pojazdy");
    }

    public void setAssemblyMenuDefault(){
        assemblyMenu.setText("Wszystkie zamówienia");
    }

    public void setAssemblyMenuTrue(){
        assemblyMenu.setText("Zamontowane");
    }

    public void setAssemblyMenuFalse(){
        assemblyMenu.setText("Niezamontowane");
    }


    private void setCarsMenu(){
        for(CarsModel car:carsRepository.findAllByOrderByName()){
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
        for(PartsModel part: partsRepository.findAll()){
            MenuItem newItem =
                    MenuItemBuilder.create()
                            .text(part.getName())
                            .onAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    partsMenu.setText(part.getName());
                                }
                            })
                            .build();
            partsMenu.getItems().add(newItem);
        }
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
}
