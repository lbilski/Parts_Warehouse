package pl.lukaszbilski.Parts.Warehouse.controllers.service;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.lukaszbilski.Parts.Warehouse.models.Service;
import pl.lukaszbilski.Parts.Warehouse.models.models.CompanyBranchModel;
import pl.lukaszbilski.Parts.Warehouse.models.models.ServicesModel;
import pl.lukaszbilski.Parts.Warehouse.models.repositories.CompanyBranchRepository;
import pl.lukaszbilski.Parts.Warehouse.models.repositories.ServicesRepository;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

@Controller
public class NewServiceController implements Initializable {

    @FXML
    MenuButton cityMenu;
    @FXML
    DatePicker dateNextAction, dateApplication;
    @FXML
    TextField licensePlate, carID;
    @FXML
    Button saveAndExit;
    @FXML
    TextArea allTasks, nextActionTask, tasksDone;

    @Autowired
    CompanyBranchRepository companyBranchRepository;
    @Autowired
    ServicesRepository servicesRepository;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCityMenu();
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

    public void saveAndExit(){
        ServicesModel servicesModel = new ServicesModel();

        servicesModel.setStatus("Aktywne");
        servicesModel.setBranch(cityMenu.getText());
        if(dateApplication.getValue() == null){
            servicesModel.setApplicationDate(null);
        }else{
            servicesModel.setApplicationDate(Date.valueOf(dateApplication.getValue()));
        }
        servicesModel.setLicensePlate(licensePlate.getText());
        servicesModel.setCarID(carID.getText());
        servicesModel.setTasks(allTasks.getText());
        servicesModel.setFinishedTasks(tasksDone.getText());
        if(dateNextAction.getValue() == null){
            servicesModel.setDateOfNextAction(null);
        }else{
            servicesModel.setDateOfNextAction(Date.valueOf(dateNextAction.getValue()));
        }
        servicesModel.setNextActionTask(nextActionTask.getText());

        try {
            servicesRepository.save(servicesModel);
            Stage stageToClose = (Stage) saveAndExit.getScene().getWindow();
            stageToClose.close();
        }catch (Exception e){
            e.printStackTrace();
            Service.infoMessage("Błąd", "Błędne wypełnione dane");
        }
    }
}
