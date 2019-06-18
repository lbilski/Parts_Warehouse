package pl.lukaszbilski.Parts.Warehouse.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import org.springframework.stereotype.Controller;
import pl.lukaszbilski.Parts.Warehouse.controllers.branches.BranchesTabController;
import pl.lukaszbilski.Parts.Warehouse.controllers.service.ServiceTabController;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class MainViewController implements Initializable{
    @FXML Tab branches;
    @FXML private BranchesTabController branchesTabController;
    @FXML private OrdersTabController ordersTabController;
    @FXML private PricesTabController pricesTabController;
    @FXML private ServiceTabController serviceTabController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        branches.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                try {
                    branchesTabController.initialize(new URL("file:/" + "../fxml/branchesView.fxml"), null);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
