package pl.lukaszbilski.Parts.Warehouse.controllers.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import pl.lukaszbilski.Parts.Warehouse.models.Service;
import pl.lukaszbilski.Parts.Warehouse.models.models.OrdersModel;
import pl.lukaszbilski.Parts.Warehouse.models.models.ServicesModel;
import pl.lukaszbilski.Parts.Warehouse.models.repositories.ServicesRepository;

import java.io.IOException;
import java.net.MalformedURLException;
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

    @FXML
    Button newService, editService, refreshButton;

    @Autowired
    ServicesRepository servicesRepository;
    @Autowired
    ApplicationContext applicationContext;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setListServices(FXCollections.observableList(servicesRepository.findAllByOrderByDateOfNextAction()));

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

    public void addNewService(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/newService.fxml"));
            fxmlLoader.setControllerFactory(applicationContext::getBean);
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(newService.getScene().getWindow());
            stage.showAndWait();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void editService(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/editService.fxml"));
            fxmlLoader.setControllerFactory(applicationContext::getBean);
            Parent root = fxmlLoader.load();
            EditServiceController controller = fxmlLoader.getController();
            controller.inputModel = tableServices.getSelectionModel().getSelectedItem();
            controller.init();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(editService.getScene().getWindow());
            stage.showAndWait();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void refresh(){
        try {
            this.initialize(new URL("file:/" + "../fxml/serviceTabView.fxml"), null);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
