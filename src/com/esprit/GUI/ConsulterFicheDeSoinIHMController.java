/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.GUI;

import com.esprit.SERVICE.ControlleurChamps;
import com.esprit.SERVICE.ControlleurFicheDeSoin;
import com.esprit.entities.FicheDeSoin;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author makni
 */
public class ConsulterFicheDeSoinIHMController implements Initializable {

    @FXML
    private TableView<FicheDeSoin> listeFicheDeSoin;
    private TableColumn<FicheDeSoin, Integer> id_f_soin;
    @FXML
    private TableColumn<FicheDeSoin, String> observation;

    @FXML
    private TableColumn<FicheDeSoin, String> medicament;

    @FXML
    private TableColumn<FicheDeSoin, String> prochainRDV;
    public static int id_f_soin_modifier;

    @FXML
    private Button ajouter;
    @FXML
    private Button modifer;
    @FXML
    private Button annuler;
    @FXML
    private Button supprimer;
    @FXML
    private Label id_membre;
    @FXML
    private TextArea observ;
    @FXML
    private DatePicker prchRDV;
    @FXML
    private TextField medi;

    @FXML
    private Label erreurobserv;
    @FXML
    private Label erreurdaterdv;
    @FXML
    private Label erreurMedi;
    int id;
    int idanim;
    FicheDeSoin fs;

    /**
     * Instanciation Du Controlleur*
     */
    ControlleurFicheDeSoin cfds = new ControlleurFicheDeSoin();

    ArrayList<FicheDeSoin> ficheDeSoins = cfds.ConsulterFicheDeSoin();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        modifer.setDisable(true);


        /*
         *Deffinition des Colonnes
         */
        observation.setCellValueFactory(new PropertyValueFactory("observation"));
        medicament.setCellValueFactory(new PropertyValueFactory("medicament"));
        prochainRDV.setCellValueFactory(new PropertyValueFactory("prochainRDV"));

        listeFicheDeSoin.getItems().addAll(ficheDeSoins);

        listeFicheDeSoin.setOnMouseClicked((event) -> {

            if (event.getClickCount() == 2) {
                modifer.setDisable(false);
                fs = listeFicheDeSoin.getItems().get(listeFicheDeSoin.getSelectionModel().getSelectedIndex());
                observ.setText(fs.getObservation());
                LocalDate lc = LocalDate.parse(fs.getProchainRDV().toString());
                prchRDV.setValue(lc);
                medi.setText(fs.getMedicament());
                id = fs.getId();
                idanim = fs.getId_animal().getId();

            }
        });
    }

    private boolean verif() {
        ControlleurChamps cc = new ControlleurChamps();
        if (medi.getText().isEmpty()) {
            erreurMedi.setText("* Medicament Vide");
        } else if (observation.getText().isEmpty()) {
            erreurMedi.setText("");
            erreurobserv.setText("* Observation Vide");
        } else if (prchRDV.getValue().toString().isEmpty()) {
            erreurMedi.setText("");
            erreurobserv.setText("");
            erreurdaterdv.setText("*Date vide");
        } else {
            erreurMedi.setText("");
            erreurobserv.setText("");
            erreurdaterdv.setText("");
            return true;
        }
        return false;

    }

    public void ref() {
        listeFicheDeSoin.getItems().clear();
        listeFicheDeSoin.getItems().addAll(cfds.ConsulterFicheDeSoin());
    }

    @FXML

    private void modifier(ActionEvent event) throws ParseException {
        if (verif()) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date prcv = format.parse(prchRDV.getValue().toString());
            FicheDeSoin fs = new FicheDeSoin(id, observ.getText(), medi.getText(), prcv);
            cfds.modifierFicheDeSoin(fs);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("fiche de soin modifer");
            alert.showAndWait();
            ref();
        }

    }

    @FXML
    private void annuler(ActionEvent event) {
    }

    @FXML
    private void supp(ActionEvent event) {
        cfds.updateFicheDeSoin(listeFicheDeSoin.getItems().get(listeFicheDeSoin.getSelectionModel().getSelectedIndex()));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("fiche de soin supprimer");
        alert.showAndWait();
        listeFicheDeSoin.getItems().remove(listeFicheDeSoin.getSelectionModel().getSelectedIndex());
    }

    @FXML
    private void ajouter(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FicheDeSoinIHM.fxml"));
        try {
            Parent root = (Parent) loader.load();
            FicheDeSoinIHMController control = loader.getController();
            Stage window;
            window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
            window.show();
            ref();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }

    }

}
