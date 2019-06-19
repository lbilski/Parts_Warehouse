package pl.lukaszbilski.Parts.Warehouse.controllers.branches;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.lukaszbilski.Parts.Warehouse.models.models.CompanyBranchModel;
import pl.lukaszbilski.Parts.Warehouse.models.repositories.CompanyBranchRepository;

@Controller
public class NewBranchController {

    @FXML
    Button saveAndExit;

    @FXML
    TextField loginHart, branchHart, notesHart, loginIC, branchIC, notesIC, loginAP, notesAP, branchName;

    @Autowired
    CompanyBranchRepository companyBranchRepository;

    public void saveAndExit() {
        CompanyBranchModel newBranch = new CompanyBranchModel();

        newBranch.setCity(branchName.getText());
        newBranch.setLogin_Hart(loginHart.getText());
        newBranch.setBranch_Hart(branchHart.getText());
        newBranch.setNotes_Hart(notesHart.getText());
        newBranch.setLogin_Inter_Cars(loginIC.getText());
        newBranch.setBranch_Inter_Cars(branchIC.getText());
        newBranch.setNotes_Inter_Cars(notesIC.getText());

        companyBranchRepository.save(newBranch);

        Stage stageToClose = (Stage) saveAndExit.getScene().getWindow();
        stageToClose.close();
    }
}
