package pl.lukaszbilski.Parts.Warehouse.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Controller;

@Controller
public class LoginController {
    @FXML
    Button loginButton;
    @FXML
    TextField passwordField;

    public void login(){
        System.out.println(passwordField.getText());
    }
}
