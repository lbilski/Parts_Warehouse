package pl.lukaszbilski.Parts.Warehouse.controllers.service;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.lukaszbilski.Parts.Warehouse.models.Service;
import pl.lukaszbilski.Parts.Warehouse.models.models.CompanyBranchModel;
import pl.lukaszbilski.Parts.Warehouse.models.models.ServicesModel;
import pl.lukaszbilski.Parts.Warehouse.models.repositories.CompanyBranchRepository;
import pl.lukaszbilski.Parts.Warehouse.models.repositories.ServicesRepository;

import java.sql.Date;

@Controller
public class EditServiceController {
    @FXML
    MenuButton cityMenu, status;
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

    ServicesModel inputModel;

    public void saveAndExit(){
        inputModel.setStatus(status.getText());
        inputModel.setBranch(cityMenu.getText());
        inputModel.setApplicationDate(Date.valueOf(dateApplication.getValue()));
        inputModel.setLicensePlate(licensePlate.getText());
        inputModel.setCarID(carID.getText());
        inputModel.setTasks(allTasks.getText());
        inputModel.setFinishedTasks(tasksDone.getText());
        inputModel.setDateOfNextAction(Date.valueOf(dateNextAction.getValue()));
        inputModel.setNextActionTask(nextActionTask.getText());

        try {
            servicesRepository.save(inputModel);
            Stage stageToClose = (Stage) saveAndExit.getScene().getWindow();
            stageToClose.close();
        }catch (Exception e){
            Service.infoMessage("Błąd", "Błędnie wypełnione dane");
        }
    }

    public void init(){
        status.setText(inputModel.getStatus());
        cityMenu.setText(inputModel.getBranch());
        if(inputModel.getApplicationDate() != null){
            dateApplication.setValue(inputModel.getApplicationDate().toLocalDate());
        }else{
            dateApplication.setValue(null);
        }
        licensePlate.setText(inputModel.getLicensePlate());
        carID.setText(inputModel.getCarID());
        allTasks.setText(inputModel.getTasks());
        tasksDone.setText(inputModel.getFinishedTasks());
        if(inputModel.getDateOfNextAction() != null){
            dateNextAction.setValue(inputModel.getDateOfNextAction().toLocalDate());
        }else{
            dateNextAction.setValue(null);
        }
        nextActionTask.setText(inputModel.getNextActionTask());

        setCityMenu();
    }

    public void setActive(){
        status.setText("Aktywne");
    }

    public void setCompleted(){
        status.setText("Zakończone");
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
