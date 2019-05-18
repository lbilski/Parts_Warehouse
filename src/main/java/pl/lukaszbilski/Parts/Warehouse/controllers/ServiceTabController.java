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
import pl.lukaszbilski.Parts.Warehouse.models.models.OrdersModel;
import pl.lukaszbilski.Parts.Warehouse.models.models.ServicesModel;
import pl.lukaszbilski.Parts.Warehouse.models.repositories.ServicesRepository;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;


@Controller
public class ServiceTabController implements Initializable {
    @FXML
    TableView <ServicesModel> tableServices;

    @FXML
    TableColumn<Object, String> colStatus, colBranch, colLicensePlate, colCarID, colTasks, colFinishedTasks, colNextActionTask;

    @FXML
    TableColumn<OrdersModel, Date> colApplicationDate, colNextActionDate;

    @Autowired
    ServicesRepository servicesRepository;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setListServices(FXCollections.observableList(servicesRepository.findAll()));

        Service.setWrapCellFactory(colTasks);
        Service.setWrapCellFactory(colFinishedTasks);
        Service.setWrapCellFactory(colNextActionTask);

    }

    private void setListServices (ObservableList<ServicesModel> input){
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colBranch.setCellValueFactory(new PropertyValueFactory<>("branch"));
        colApplicationDate.setCellValueFactory(new PropertyValueFactory<>("applicationDate"));
        colLicensePlate.setCellValueFactory(new PropertyValueFactory<>("licensePlate"));
        colCarID.setCellValueFactory(new PropertyValueFactory<>("carID"));
        colTasks.setCellValueFactory(new PropertyValueFactory<>("tasks"));
        colFinishedTasks.setCellValueFactory(new PropertyValueFactory<>("finishedTasks"));
        colNextActionDate.setCellValueFactory(new PropertyValueFactory<>("dateOfNextAction"));
        colNextActionTask.setCellValueFactory(new PropertyValueFactory<>("nextActionTask"));

        tableServices.setItems(input);
    }
}
