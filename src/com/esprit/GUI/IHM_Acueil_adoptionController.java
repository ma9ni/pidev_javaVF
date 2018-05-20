/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.GUI;

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
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class IHM_Acueil_adoptionController implements Initializable {

    @FXML
    private AnchorPane imagebaghround;
    @FXML
    private AnchorPane plan;
    @FXML
    private AnchorPane MenuAnchorpane;
    @FXML
    private Pane menu;
    @FXML
    private Button btngestion;
    @FXML
    private Button buttonProfil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void login(ActionEvent event) {
    }

    @FXML
    private void logout(ActionEvent event) {
    }

    @FXML
    private void onClickSoin(ActionEvent event) {
    }

    @FXML
    private void onClickCommercial(ActionEvent event) {
    }

    @FXML
    private void onClickAnimal(ActionEvent event) {
    }

    @FXML
    private void onClickWIKI(ActionEvent event) {
    }

    @FXML
    private void onClickConcours(ActionEvent event) {
    }

    @FXML
    private void onClickDressage(ActionEvent event) {
    }

    @FXML
    private void onClickGestionItilisateur(ActionEvent event) {
    }

    @FXML
    private void onClickProfil(ActionEvent event) {
    }

    @FXML
    private void donner(ActionEvent event) throws IOException {
        if (IHM_loginController.membre != null) {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("IHM_Animal_Donner.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.hide();
            stage.setScene(new Scene(root1));
            stage.show();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setContentText("il faut etre conecter");
            alert.show();
        }

    }

    @FXML
    private void deleger(ActionEvent event) throws IOException {
        if (IHM_loginController.membre != null) {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("IHM_Animal_Deleger.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.hide();
            stage.setScene(new Scene(root1));
            stage.show();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setContentText("il faut etre conecter");
            alert.show();

        }
    }

    @FXML
    private void adopter(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("IHM_Adoption_afficher.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.hide();

            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {

        }

    }

    @FXML
    private void annuler(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("IHM_Notre_Accueil.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(new Scene(root1));
        stage.show();
    }

}
