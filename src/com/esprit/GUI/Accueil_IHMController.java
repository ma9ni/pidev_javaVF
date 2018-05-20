/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author pc asus
 */
public class Accueil_IHMController implements Initializable {

    @FXML
    private Pane menu;
    @FXML
    private Button btngestion;
    @FXML
    private Button buttonProfil;
    @FXML
    private AnchorPane MenuAnchorpane;
    @FXML
    private AnchorPane imagebaghround;
    @FXML
    private AnchorPane plan;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        int i = 1;

        if (i == 1) {
            buttonProfil.setVisible(false);
            btngestion.setVisible(false);

        }
        menu.setTranslateX(-190);
        TranslateTransition menuTranslation = new TranslateTransition(Duration.millis(500), menu);

        menuTranslation.setFromX(-190);
        menuTranslation.setToX(0);

        menu.setOnMouseEntered(evt -> {
            menuTranslation.setRate(1);
            menuTranslation.play();
        });
        menu.setOnMouseExited(evt -> {
            menuTranslation.setRate(-1);
            menuTranslation.play();
        });
    }

    @FXML
    private void onClickSoin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Makni_AffichageFicheDeSoinGUI.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show();
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
    private void onClickDressage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ConsulterFicheDeDressage.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onClickGestionItilisateur(ActionEvent event) {
    }

    @FXML
    private void onClickProfil(ActionEvent event) {
    }

}
