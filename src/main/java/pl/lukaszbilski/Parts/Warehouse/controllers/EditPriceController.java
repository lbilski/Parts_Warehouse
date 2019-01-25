package pl.lukaszbilski.Parts.Warehouse.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.lukaszbilski.Parts.Warehouse.models.Service;
import pl.lukaszbilski.Parts.Warehouse.models.models.PricesModel;
import pl.lukaszbilski.Parts.Warehouse.models.repositories.PricesRepository;


@Controller
public class EditPriceController {
    @FXML
    TextArea notes;

    @FXML
    TextField partName, unit, carsName, codeIC, priceIC, codeHart, priceHart;

    @Autowired
    PricesRepository pricesRepository;

    PricesModel model;


    public void saveAndExit(){
        model.setCode_Inter_Cars(codeIC.getText());
        model.setPrice_Inter_Cars(priceIC.getText());
        model.setCode_Hart(codeHart.getText());
        model.setPrice_Hart(priceHart.getText());

        Service.infoMessage("Edycja", "Zaktualizowano dane");
        pricesRepository.save(model);

        Stage stage = (Stage) notes.getScene().getWindow();
        stage.close();
    }

    public void init(){
        partName.setText(model.getName());
        unit.setText(model.getUnit());
        carsName.setText(model.getCar());
        codeIC.setText(model.getCode_Inter_Cars());
        priceIC.setText(model.getPrice_Inter_Cars());
        codeHart.setText(model.getPrice_Hart());
        priceHart.setText(model.getPrice_Hart());
        notes.setText(model.getNotes());
    }
}
