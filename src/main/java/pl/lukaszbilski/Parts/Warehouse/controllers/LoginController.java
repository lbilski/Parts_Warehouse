package pl.lukaszbilski.Parts.Warehouse.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.lukaszbilski.Parts.Warehouse.models.Utils;
import pl.lukaszbilski.Parts.Warehouse.models.repositories.LoginRepository;

@Controller
public class LoginController {
    @FXML
    Button loginButton;
    @FXML
    TextField passwordField;

    @Autowired
    LoginRepository loginRepository;
    @Autowired
    Utils utils;

    public void login(){
        if(!loginRepository.findByPassword(passwordField.getText()).isPresent()){
            utils.infoMessage("Logowanie","Błędne hasło");
        }
    }
}
