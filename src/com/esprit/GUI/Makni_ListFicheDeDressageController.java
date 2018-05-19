/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.GUI;

import com.esprit.SERVICE.FicheDeDressageService;
import com.esprit.entities.FicheDeDressage;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author pc asus
 */
public class Makni_ListFicheDeDressageController implements Initializable {

    @FXML
    private TableView<FicheDeDressage> listeFicheDeDressage;
    @FXML
    private TableColumn<FicheDeDressage, Integer> id;
    @FXML
    private TableColumn<FicheDeDressage, String> specialite;
    @FXML
    private TableColumn<FicheDeDressage, Float> displine;
    @FXML
    private TableColumn<FicheDeDressage, Float> obeissance;
    @FXML
    private TableColumn<FicheDeDressage, Float> accompagnement;
    @FXML
    private TableColumn<FicheDeDressage, Float> interception;
    @FXML
    private TableColumn<FicheDeDressage, Float> noteTotal;
    @FXML
    private TableColumn<FicheDeDressage, Date> dateDebut;
    @FXML
    private TableColumn<FicheDeDressage, Date> dateFin;

    FicheDeDressageService ficheDeDressageService = new FicheDeDressageService();
    ArrayList<FicheDeDressage> fdds = ficheDeDressageService.displayFicheDeDressage();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        id.setCellValueFactory(new PropertyValueFactory<>("id_f_Dressage"));
        specialite.setCellValueFactory(new PropertyValueFactory<>("specialite"));
        displine.setCellValueFactory(new PropertyValueFactory<>("displine"));
        obeissance.setCellValueFactory(new PropertyValueFactory<>("obeissance"));
        accompagnement.setCellValueFactory(new PropertyValueFactory<>("accompagnement"));
        interception.setCellValueFactory(new PropertyValueFactory<>("interception"));
        noteTotal.setCellValueFactory(new PropertyValueFactory<>("noteTotal"));
        dateDebut.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        dateFin.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
        System.out.println("ddddddd" + fdds);
        listeFicheDeDressage.getItems().addAll(fdds);

    }

}
