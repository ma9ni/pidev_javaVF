/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.GUI;

import com.esprit.SERVICE.ControlleurChamps;
import com.esprit.SERVICE.ServiceEmail;
import com.esprit.SERVICE.UserController;
import com.esprit.entities.Userr;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author houssem
 */
public class IHM_Forget_PaswordController implements Initializable {

    @FXML
    private Label error;
    @FXML
    private TextField email;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("IHM_login.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(new Scene(root1));
        stage.show();

    }

    @FXML
    private void Annuler(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void envoyer(ActionEvent event) {
        ControlleurChamps cc = new ControlleurChamps();
        UserController cm = new UserController();
        if (email.getText().isEmpty()) {
            error.setText("il faut saisir l'email de votre compte");
        } else if (!cc.isEmail(email.getText())) {
            error.setText("il faut saisir un email valide");

        } else if (cm.emailExiste(email.getText()) == 1) {

            ServiceEmail se = new ServiceEmail();

            Userr m = new Userr();
            m = cm.getMembreByEmail(email.getText());
            se.sendEmail(m.getEmail(), "le mot de passe de votre compte est :" + m.getPasword());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("email existe");
            alert.setContentText("verifier votre boite email et saisir votre mot de passe");
            alert.show();

        } else {

            error.setText("email n'existe pas dans notre platforme");
        }

    }

}
