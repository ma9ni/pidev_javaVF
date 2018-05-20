/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.GUI;

import com.esprit.SERVICE.ControlleurChamps;
import com.esprit.SERVICE.ControlleurFicheDeSoin;
import com.esprit.SERVICE.FicheDeDressageService;
import com.esprit.entities.Animal;
import com.esprit.entities.FicheDeSoin;
import com.esprit.entities.Userr;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author pc makni
 */
public class FicheDeSoinIHMController implements Initializable {

    @FXML
    private Text id_membre;

    @FXML
    private Label nom;
    @FXML
    private Label espece;
    @FXML
    private TextArea observation;
    @FXML
    private Label poids;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton female;
    @FXML
    private Text id_f_Soin;
    @FXML
    private TextField medi;
    @FXML
    private Button annuler;

    @FXML
    private DatePicker datep;
    @FXML
    private DatePicker revoirerdv;

    @FXML
    ToggleGroup genre;
    @FXML
    private Label id_vete;
    @FXML
    private Button ajouterFiche;
    @FXML
    private Label prop;
    @FXML
    private TableView<Animal> listeanimal;

    @FXML
    private TableColumn<Animal, String> nom_anim;

    int id;
    @FXML
    private Button ajouteranim;
    @FXML
    private Label erreurobserv;
    @FXML
    private Label erreurdaterdv;
    @FXML
    private Label erreurMedi;
    Animal a;

    FicheDeDressageService cfdd = new FicheDeDressageService();
    ArrayList<Animal> animals = cfdd.selectAllAnimal();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ajouterFiche.setDisable(true);
        nom_anim.setCellValueFactory(new PropertyValueFactory("nom"));
        listeanimal.getItems().addAll(animals);
        listeanimal.setOnMouseClicked((event) -> {
            if (event.getClickCount() == 2) {
                a = listeanimal.getItems().get(listeanimal.getSelectionModel().getSelectedIndex());
                espece.setText("Race : " + a.getRace());
                nom.setText("Nom : " + a.getNom());
                prop.setText("Propritére : " + a.getNomproprietaire());
                poids.setText("sexe : " + a.getSexe());
                datep.setValue(LocalDate.now());
                ajouterFiche.setDisable(false);
            } else {

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
        } else if (revoirerdv.getValue().toString().isEmpty()) {
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

    @FXML
    private void medi(MouseEvent event) {
    }

    @FXML
    private void annuler(ActionEvent event) {
    }

    @FXML
    private void ajouterFicheSoin(ActionEvent event) throws ParseException {
        ControlleurFicheDeSoin cfds = new ControlleurFicheDeSoin();
        String gen;
        if (female.isSelected()) {
            gen = "Female";
        } else {
            gen = "Male";
        }
        if (verif()) {
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            Date dateRDV = formater.parse(revoirerdv.getValue().toString());
            Date dateNoW = formater.parse(datep.getValue().toString());
            Date d = new Date();
            if (dateRDV.before(d)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("date non valide");
                alert.showAndWait();
            } else {
                Userr u = new Userr();
                u.setId(8);
                FicheDeSoin fs = new FicheDeSoin(u, observation.getText(), medi.getText(), dateNoW, a, dateRDV, id);
                cfds.ajouterFicheDeSoin(fs);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("fiche de dressage créer");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void ajouteranim(ActionEvent event) {
    }

}
