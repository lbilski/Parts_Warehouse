package pl.lukaszbilski.Parts.Warehouse.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.lukaszbilski.Parts.Warehouse.models.models.CarsModel;
import pl.lukaszbilski.Parts.Warehouse.models.models.PartsModel;
import pl.lukaszbilski.Parts.Warehouse.models.models.PricesModel;
import pl.lukaszbilski.Parts.Warehouse.models.repositories.CarsRepository;
import pl.lukaszbilski.Parts.Warehouse.models.repositories.PartsRepository;
import pl.lukaszbilski.Parts.Warehouse.models.repositories.PricesRepository;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class AddPricesController implements Initializable{
    @FXML
    MenuButton partsMenu, carsMenu;
    @FXML
    Button saveAndExit, saveAndContinue;
    @FXML
    TextField unit, codeIC, priceIC, codeHart, priceHart;
    @FXML
    TextArea notes;

    @Autowired
    PartsRepository partsRepository;
    @Autowired
    CarsRepository carsRepository;
    @Autowired
    PricesRepository pricesRepository;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initPartsMenu();
        initCarsMenu();
    }

    private void initPartsMenu(){
        for(PartsModel part: partsRepository.findAllByOrderByName()){
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

    private void initCarsMenu(){
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

    public void saveAndExit(){
        PricesModel modelToSend = newModel();
        pricesRepository.save(modelToSend);

        Stage stage = (Stage) saveAndExit.getScene().getWindow();
        stage.close();
    }

    public void saveAndNext(){
        PricesModel modelToSend = newModel();
        pricesRepository.save(modelToSend);

        partsMenu.setText("Nazwa części");
        unit.setText("");
        carsMenu.setText("Samochód");
        codeIC.setText("");
        priceIC.setText("");
        codeHart.setText("");
        priceHart.setText("");
    }

    private PricesModel newModel(){
        PricesModel model = new PricesModel();

        model.setName(partsMenu.getText());
        model.setUnit(unit.getText());
        model.setCar(carsMenu.getText());
        model.setCode_Inter_Cars(codeIC.getText());
        model.setPrice_Inter_Cars(priceIC.getText());
        model.setCode_Hart(codeHart.getText());
        model.setPrice_Hart(priceHart.getText());
        model.setNotes(notes.getText());

        return model;
    }
}
