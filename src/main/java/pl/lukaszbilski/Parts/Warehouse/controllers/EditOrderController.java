package pl.lukaszbilski.Parts.Warehouse.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.lukaszbilski.Parts.Warehouse.models.Service;
import pl.lukaszbilski.Parts.Warehouse.models.models.OrdersModel;
import pl.lukaszbilski.Parts.Warehouse.models.repositories.OrdersRepository;

import java.sql.Date;

@Controller
public class EditOrderController{
    OrdersModel inputModel;

    @FXML
    TextField wholesale, vin, unit, quantity, partName, numberOrder, car, branch, partCode;
    @FXML
    TextArea notes;
    @FXML
    DatePicker dateOrder, dateAssembly;
    @FXML
    Button saveAndExit;

    @Autowired
    OrdersRepository ordersRepository;

    public void saveAndExit(){
        inputModel.setCity(branch.getText());
        inputModel.setWholesale(wholesale.getText());
        inputModel.setDate_order(Date.valueOf(dateOrder.getValue()));
        inputModel.setNumber_order(numberOrder.getText());
        inputModel.setCar(car.getText());
        inputModel.setPart(partName.getText());
        inputModel.setPartCode(partCode.getText());
        inputModel.setUnit(unit.getText());
        inputModel.setQuantity(Integer.parseInt(quantity.getText()));
        inputModel.setId_vin(vin.getText());
        if(dateAssembly.getValue()==null){
            inputModel.setAssembly(null);
        }else{
            inputModel.setAssembly(Date.valueOf(dateAssembly.getValue()));
        }
        inputModel.setNotes(notes.getText());

        ordersRepository.save(inputModel);
        Service.infoMessage("Edycja", "Zaktualizowano dane");
        Stage stageToClose = (Stage) saveAndExit.getScene().getWindow();
        stageToClose.close();
    }

    public void init(){
        wholesale.setText(inputModel.getWholesale());
        vin.setText(inputModel.getId_vin());
        unit.setText(inputModel.getUnit());
        quantity.setText(String.valueOf(inputModel.getQuantity()));
        partName.setText(inputModel.getPart());
        partCode.setText(inputModel.getPartCode());
        numberOrder.setText(inputModel.getNumber_order());
        car.setText(inputModel.getCar());
        branch.setText(inputModel.getCity());
        notes.setText(inputModel.getNotes());
        dateOrder.setValue((inputModel.getDate_order().toLocalDate()));
        if(inputModel.getAssembly()==null){
            dateAssembly.setValue(null);
        }else{
            dateAssembly.setValue(inputModel.getAssembly().toLocalDate());
        }
    }
}
