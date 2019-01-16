package pl.lukaszbilski.Parts.Warehouse.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import pl.lukaszbilski.Parts.Warehouse.models.Service;
import pl.lukaszbilski.Parts.Warehouse.models.models.CompanyBranchModel;
import pl.lukaszbilski.Parts.Warehouse.models.repositories.CompanyBranchRepository;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class BranchesTabController implements Initializable{

    @FXML
    TableView<CompanyBranchModel> tableCompanyBranch;
    @FXML
    TableColumn<Object, String> city, loginHart, branchHart, notesHart, loginInterCars, branchInterCars, notesInterCars;
    @FXML
    Button editButton, addButton;

    @Autowired
    CompanyBranchRepository companyBranchRepository;

    @Autowired
    ApplicationContext applicationContext;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setListCompanyBranch();

        Service.allowTableToBeCopy(tableCompanyBranch);
        Service.setWrapCellFactory(notesInterCars);
        Service.setWrapCellFactory(notesHart);

        tableCompanyBranch.getSelectionModel().setCellSelectionEnabled(true);
    }

    //Updating list of company branches from database
    private void setListCompanyBranch(){
        ObservableList<CompanyBranchModel> allBranches = FXCollections.observableList(companyBranchRepository.findAllByOrderByCity());

        city.setCellValueFactory(new PropertyValueFactory<>("city"));
        loginHart.setCellValueFactory(new PropertyValueFactory<>("login_Hart"));
        branchHart.setCellValueFactory(new PropertyValueFactory<>("branch_Hart"));
        notesHart.setCellValueFactory(new PropertyValueFactory<>("notes_Hart"));
        loginInterCars.setCellValueFactory(new PropertyValueFactory<>("login_Inter_Cars"));
        branchInterCars.setCellValueFactory(new PropertyValueFactory<>("branch_Inter_Cars"));
        notesInterCars.setCellValueFactory(new PropertyValueFactory<>("notes_Inter_Cars"));

        tableCompanyBranch.setItems(allBranches);
    }

    //Open new stage with option to edit branch
    public void editData(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/editBranch.fxml"));
            fxmlLoader.setControllerFactory(applicationContext::getBean);
            Parent root = fxmlLoader.load();
            EditBranchController controller = fxmlLoader.getController();
            controller.model = tableCompanyBranch.getSelectionModel().getSelectedItem();
            controller.init();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(editButton.getScene().getWindow());
            stage.showAndWait();

            initialize(new URL("file:/" + "../fxml/branchesView.fxml"),null);
        }catch (Exception e){
            Service.infoMessage("Błąd", "Błąd w wyborze pola do edycji");
            e.printStackTrace();
        }
    }

    //Add new branch or client ID
    public void addNew(){
        //TO-DO
        Service.infoMessage("Auto części", "Funkcja zostanie dodana");
    }
}
