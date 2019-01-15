package pl.lukaszbilski.Parts.Warehouse.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.lukaszbilski.Parts.Warehouse.models.Service;
import pl.lukaszbilski.Parts.Warehouse.models.models.CompanyBranchModel;
import pl.lukaszbilski.Parts.Warehouse.models.repositories.CompanyBranchRepository;


@Controller
public class EditBranchController {
    @FXML
    TextField city, loginHart, branchHart, loginInterCars, branchInterCars;
    @FXML
    TextArea notesHart, notesInterCars;
    @FXML
    Button saveAndExit;

    CompanyBranchModel model;

    @Autowired
    CompanyBranchRepository branchRepository;

    public void saveAndExit(){
        model.setNotes_Hart(notesHart.getText());
        model.setNotes_Inter_Cars(notesInterCars.getText());

        branchRepository.save(model);
        Service.infoMessage("Aktualizacja", "Zaktualizowano dane");
        Stage stageToClose = (Stage) saveAndExit.getScene().getWindow();
        stageToClose.close();
    }

    public void init(){
        city.setText(model.getCity());
        loginHart.setText(model.getLogin_Hart());
        branchHart.setText(model.getBranch_Hart());
        notesHart.setText(model.getNotes_Hart());
        loginInterCars.setText(model.getLogin_Inter_Cars());
        branchInterCars.setText(model.getBranch_Inter_Cars());
        notesInterCars.setText(model.getNotes_Inter_Cars());
    }
}
