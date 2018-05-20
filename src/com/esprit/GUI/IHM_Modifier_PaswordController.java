/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.GUI;

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
public class IHM_Modifier_PaswordController implements Initializable {

    @FXML
    private TextField passeActuel;
    @FXML
    private TextField passeNouveau;
    @FXML
    private TextField passeNouveauConfirmer;
    @FXML
    private Label passe;
    @FXML
    private Label nouveaupasse;
    @FXML
    private Label nouveaupasseConfirmer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Modifier(ActionEvent event) {
        UserController cm = new UserController();
        Userr m = IHM_loginController.membre;

        if (passeActuel.getText().isEmpty() || passeNouveau.getText().isEmpty() || passeNouveauConfirmer.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("il faut saisir tous les chmps");
            alert.show();
        } else if (cm.login(m.getEmail(), passeActuel.getText()) == 0) {
            passe.setText("mot de passe incorecte");
        } else if (passeNouveau.getText().length() < 8) {
            nouveaupasse.setText("il faut que plus de 8 caractere");

        } else if (!passeNouveau.getText().equals(passeNouveauConfirmer.getText())) {
            nouveaupasseConfirmer.setText("il faut confirmer correctement le mot de passe");
        } else {
            Userr m2 = new Userr(m.getUsername(), m.getAdresse(), m.getEmail(), m.getNum_tel(), m.getImage(), passeNouveau.getText());
            cm.modifierpasword(m2, m.getId());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("mot de passe modifier avec succes");
            alert.show();
        }
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("IHM_Profil.fxml"));

        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(new Scene(root1));
        stage.show();

    }

}
