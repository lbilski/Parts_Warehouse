package pl.lukaszbilski.Parts.Warehouse.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import pl.lukaszbilski.Parts.Warehouse.models.Utils;
import pl.lukaszbilski.Parts.Warehouse.models.repositories.LoginRepository;

@Controller
public class LoginController extends Utils {
    @FXML
    Button loginButton;
    @FXML
    PasswordField passwordField;

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    ApplicationContext applicationContext;

    public void handleEnterPressed(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){
            login();
        }
    }

    public void login(){
        if(loginRepository.findByPassword(passwordField.getText()).isPresent()){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/mainView.fxml"));
                fxmlLoader.setControllerFactory(applicationContext::getBean);
                Parent root = fxmlLoader.load();
                Stage stageToClose = (Stage) loginButton.getScene().getWindow();
                stageToClose.close();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            infoMessage("Logowanie","Błędne hasło");
        }
    }
}
