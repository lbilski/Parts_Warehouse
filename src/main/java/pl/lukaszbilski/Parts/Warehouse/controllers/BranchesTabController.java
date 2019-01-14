package pl.lukaszbilski.Parts.Warehouse.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    CompanyBranchRepository companyBranchRepository;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setListCompanyBranch();
        tableCompanyBranch.getSelectionModel().setCellSelectionEnabled(true);
        Service.allowTableToBeCopy(tableCompanyBranch);
    }

    private void setListCompanyBranch(){
        ObservableList<CompanyBranchModel> observableList = FXCollections.observableList(companyBranchRepository.findAllByOrderByCity());

        city.setCellValueFactory(new PropertyValueFactory<>("city"));
        loginHart.setCellValueFactory(new PropertyValueFactory<>("login_Hart"));
        branchHart.setCellValueFactory(new PropertyValueFactory<>("branch_Hart"));
        notesHart.setCellValueFactory(new PropertyValueFactory<>("notes_Hart"));
        loginInterCars.setCellValueFactory(new PropertyValueFactory<>("login_Inter_Cars"));
        branchInterCars.setCellValueFactory(new PropertyValueFactory<>("branch_Inter_Cars"));
        notesInterCars.setCellValueFactory(new PropertyValueFactory<>("notes_Inter_Cars"));
        Service.setWrapCellFactory(notesInterCars);
        Service.setWrapCellFactory(notesHart);

        tableCompanyBranch.setItems(observableList);
    }
}
