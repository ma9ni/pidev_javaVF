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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AcueilAnimalController implements Initializable {

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
    private void onClickAdoption(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("IHM_Adoption.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onClickConsulter_Animal(ActionEvent event) {
    }

    @FXML
    private void onClickSOSDisparition(ActionEvent event) {
    }

}
