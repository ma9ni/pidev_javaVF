/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.GUI;

import com.esprit.SERVICE.ControlleurChamps;
import com.esprit.SERVICE.FicheDeDressageService;
import com.esprit.entities.FicheDeDressage;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author makni
 */
public class ConsulterFicheDeDressageController implements Initializable {

    @FXML
    private TableView<FicheDeDressage> listeFicheDeDressage;
    @FXML
    private Button Modifier;
    @FXML
    private Button Supprimer;
    @FXML
    private Button ajouter;
    @FXML
    private Button annuler;
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

    FicheDeDressageService cfdd = new FicheDeDressageService();

    ArrayList<FicheDeDressage> arrayficheDeDressages = cfdd.displayFicheDeDressage();
    @FXML
    private TextField specialitetext;
    @FXML
    private TextField obeissancetext;
    @FXML
    private TextField interceptiontext;
    @FXML
    private TextField displinetext;
    @FXML
    private TextField accompagnementtext;
    @FXML
    private DatePicker datedep;
    @FXML
    private DatePicker datef;
    private final Label jobStatus = new Label();
    /**
     * test ahmed*
     */
    public static int iddd = 1;
    @FXML
    private Label erreursp;
    @FXML
    private Label erreurdes;
    @FXML
    private Label erreurinterc;
    @FXML
    private Label erreuraco;
    @FXML
    private Label erreurobe;
    @FXML
    private Label erreurdeb;
    @FXML
    private Label erreurfin;

    FicheDeDressage fs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Modifier.setDisable(true);
        specialite.setCellValueFactory(new PropertyValueFactory<>("specialite"));
        displine.setCellValueFactory(new PropertyValueFactory<>("displine"));
        obeissance.setCellValueFactory(new PropertyValueFactory<>("obeissance"));
        accompagnement.setCellValueFactory(new PropertyValueFactory<>("accompagnement"));
        interception.setCellValueFactory(new PropertyValueFactory<>("interception"));
        noteTotal.setCellValueFactory(new PropertyValueFactory<>("noteTotal"));
        dateDebut.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        dateFin.setCellValueFactory(new PropertyValueFactory<>("dateFin"));

        listeFicheDeDressage.getItems().addAll(arrayficheDeDressages);
        listeFicheDeDressage.setOnMouseClicked(
        (event) -> {

            if (event.getClickCount() == 2) {
                ConsulterFicheDeDressageController.iddd = listeFicheDeDressage.getItems().get(listeFicheDeDressage.getSelectionModel().getSelectedIndex()).getId_animal().getId();
                fs = listeFicheDeDressage.getItems().get(listeFicheDeDressage.getSelectionModel().getSelectedIndex());
                specialitetext.setText(fs.getSpecialite());
                obeissancetext.setText(String.valueOf(fs.getObeissance()));
                interceptiontext.setText(String.valueOf(fs.getInterception()));
                displinetext.setText(String.valueOf(fs.getDispline()));
                accompagnementtext.setText(String.valueOf(fs.getAccompagnement()));
                LocalDate ld = LocalDate.parse(fs.getDateDebut().toString());
                LocalDate ld1 = LocalDate.parse(fs.getDateFin().toString());
                datedep.setValue(ld);
                datef.setValue(ld1);
                Modifier.setDisable(false);
            }
        }
        );
//        impri.setOnAction((ActionEvent event) -> {
//            printSetup(listeFicheDeDressage);
//        });
    }

    public void ref() {
        listeFicheDeDressage.getItems().clear();
        listeFicheDeDressage.getItems().addAll(cfdd.displayFicheDeDressage());

    }

    private boolean verif() {
        ControlleurChamps cc = new ControlleurChamps();
        if (specialitetext.getText().isEmpty()) {
            erreursp.setText("* Specialité Vide");
        } else if (interceptiontext.getText().isEmpty()) {
            erreursp.setText("");
            erreurinterc.setText("Interception Vide");
        } else if (!cc.isFloat(interceptiontext.getText())) {
            erreursp.setText("");
            erreurinterc.setText("interception non Valide");
        } else if (displinetext.getText().isEmpty()) {
            erreursp.setText("");
            erreurinterc.setText("");
            erreurdes.setText("despline Vide");
        } else if (!cc.isFloat(displinetext.getText())) {
            erreursp.setText("");
            erreurinterc.setText("");
            erreurdes.setText("despline non Valide");
        } else if (accompagnementtext.getText().isEmpty()) {
            erreursp.setText("");
            erreurinterc.setText("");
            erreurdes.setText("");
            erreuraco.setText("accompagnement Vide");
        } else if (!cc.isFloat(accompagnementtext.getText())) {
            erreursp.setText("");
            erreurinterc.setText("");
            erreurdes.setText("");
            erreuraco.setText("accompagnement non Valide");
        } else if (obeissancetext.getText().isEmpty()) {
            erreursp.setText("");
            erreurinterc.setText("");
            erreurdes.setText("");
            erreuraco.setText("");
            erreurobe.setText("obeissance Vide");
        } else if (!cc.isFloat(obeissancetext.getText())) {
            erreursp.setText("");
            erreurinterc.setText("");
            erreurdes.setText("");
            erreuraco.setText("");
            erreurobe.setText("obeissance non Valide");
        } else if (datedep.getValue().toString().isEmpty()) {
            erreursp.setText("");
            erreurinterc.setText("");
            erreurdes.setText("");
            erreuraco.setText("");
            erreurobe.setText("");
            erreurdeb.setText("Date debut vide");
        } else if (datef.getValue().toString().isEmpty()) {
            erreursp.setText("");
            erreurinterc.setText("");
            erreurdes.setText("");
            erreuraco.setText("");
            erreurobe.setText("");
            erreurdeb.setText("");
            erreurfin.setText("Date fin vide");
        } else {
            erreursp.setText("");
            erreurinterc.setText("");
            erreurdes.setText("");
            erreuraco.setText("");
            erreurobe.setText("");
            erreurdeb.setText("");
            erreurfin.setText("");
            return true;
        }
        return false;

    }

    @FXML
    private void modi(ActionEvent event) throws ParseException {

//listeFicheDeDressage.getItems().get(listeFicheDeDressage.getSelectionModel().getSelectedIndex()).getId_animal();
        if (verif()) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date datedeb = format.parse(datedep.getValue().toString());
            Date datFin = format.parse(datef.getValue().toString());
            Float notet = Float.parseFloat(displinetext.getText()) + Float.parseFloat(obeissancetext.getText()) + Float.parseFloat(accompagnementtext.getText()) + Float.parseFloat(interceptiontext.getText()) / 4;

            FicheDeDressage fss = new FicheDeDressage(fs.getId(), fs.getId_membre(), specialitetext.getText(), Float.parseFloat(displinetext.getText()), Float.parseFloat(obeissancetext.getText()), Float.parseFloat(accompagnementtext.getText()), Float.parseFloat(interceptiontext.getText()), notet, datedeb, datFin, fs.getId_animal(), 1);
            if (cfdd.modifierFicheDeDressage(fss) == 1) {
                cfdd.modifierFicheDeDressage(fss);
//            cfdd.modifierFicheDeDressage(id, 1, specialitetext.getText(), Float.parseFloat(displinetext.getText()), Float.parseFloat(obeissancetext.getText()), Float.parseFloat(accompagnementtext.getText()), Float.parseFloat(interceptiontext.getText()), notet, datedeb, datFin, id_anim, 1);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("fiche de dressage modifier");
                alert.showAndWait();
                ref();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);

            }

        }
    }

    @FXML
    private void supp(ActionEvent event) {
        cfdd.supprimerFicheDeDressage(listeFicheDeDressage.getItems().get(listeFicheDeDressage.getSelectionModel().getSelectedIndex()));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("fiche de dressage supprimer");
        alert.showAndWait();
        listeFicheDeDressage.getItems().remove(listeFicheDeDressage.getSelectionModel().getSelectedIndex());

    }

    @FXML
    private void add(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FicheDeDressageIHM.fxml"));
        Parent root = (Parent) loader.load();
        Stage window;
        window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();

    }

    @FXML
    private void annuler(ActionEvent event) {
    }

    private void printSetup(Node node) {
        // Create the PrinterJob
        PrinterJob job = PrinterJob.createPrinterJob();

        if (job == null) {
            return;
        }
        Window owner = null;

        // Show the print setup dialog
        boolean proceed = job.showPrintDialog(owner);

        if (proceed) {
            print(job, node);
        }
    }

    private void print(PrinterJob job, Node node) {
        // Set the Job Status Message
        jobStatus.textProperty().bind(job.jobStatusProperty().asString());

        // Print the node
        boolean printed = job.printPage(node);

        if (printed) {
            job.endJob();
        }

    }

//    @FXML
//    private void imprimer(ActionEvent event) throws FileNotFoundException, DocumentException {
//
//        Document doc = new Document();
//        PdfWriter.getInstance(doc, new FileOutputStream("fiche de dressage.pdf"));
//        doc.open();
//        doc.add(new Paragraph("                   Fiche de Dressage:"));
//        doc.add(new Paragraph("despline" + String.valueOf(displinetext.getText())));
//        doc.add(new Paragraph("obeissance" + String.valueOf(obeissance.getText())));
//        doc.add(new Paragraph("accompagnement" + String.valueOf(accompagnement.getText())));
//        doc.add(new Paragraph("interception" + String.valueOf(interception.getText())));
//        doc.close();
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Information Dialog");
//        alert.setHeaderText(null);
//        alert.setContentText("Fiche créer");
//        alert.showAndWait();
//        doc.close();
//    }
}
