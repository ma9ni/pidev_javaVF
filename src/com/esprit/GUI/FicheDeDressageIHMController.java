/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.GUI;

//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.pdf.PdfWriter;
import com.esprit.SERVICE.ControlleurChamps;
import com.esprit.SERVICE.FicheDeDressageService;
import com.esprit.entities.FicheDeDressage;
import com.esprit.entities.Userr;
import com.esprit.entities.Animal;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author makni
 */
public class FicheDeDressageIHMController implements Initializable {

    @FXML
    private Label prop;
    @FXML
    private Button ajouter;
    @FXML
    private Button annuler;
    private TextField photo;
    @FXML
    private DatePicker dated;
    @FXML
    private DatePicker dateF;
    @FXML
    private TextField specialite;

    @FXML
    private TextField despline;
    @FXML
    private TextField obeissance;
    @FXML
    private TextField accompagnement;
    @FXML
    private TextField interception;
    private final Label jobStatus = new Label();
    float note = 0;

    FicheDeDressageService cfdd = new FicheDeDressageService();
    @FXML
    private TableView<Animal> listeanimal;
    @FXML
    private TableColumn<Animal, Integer> id_anim;
    @FXML
    private TableColumn<Animal, String> nom_anim;
    @FXML
    private Button ajouteranim;

    ArrayList<Animal> animals = cfdd.selectAllAnimal();
    int id;
    @FXML
    private Button imprimer;
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
    @FXML
    private Label racelab;
    @FXML
    private Label nomlab;
    @FXML
    private ImageView imageanim;
    @FXML
    private Label sexee;
    Animal a;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ajouter.setDisable(true);
        nom_anim.setCellValueFactory(new PropertyValueFactory("nom"));

        System.out.println("aniiiiiiiiim" + animals);
        listeanimal.getItems().addAll(animals);
        listeanimal.setOnMouseClicked((event) -> {
            if (event.getClickCount() == 2) {
                a = listeanimal.getItems().get(listeanimal.getSelectionModel().getSelectedIndex());
                racelab.setText("Race : " + a.getRace());
                nomlab.setText("Nom : " + a.getNom());
                prop.setText("Propritére : " + a.getNomproprietaire());
                sexee.setText("sexe : " + a.getSexe());
                ajouter.setDisable(false);
            }
        });
        imprimer.setOnAction((ActionEvent event) -> {
            //printSetup(textArea, );
        });

    }

    private boolean verif() {
        ControlleurChamps cc = new ControlleurChamps();
        if (specialite.getText().isEmpty()) {
            erreursp.setText("* Specialité Vide");
        } else if (interception.getText().isEmpty()) {
            erreursp.setText("");
            erreurinterc.setText("Interception Vide");
        } else if (!cc.isNumber(interception.getText())) {
            erreursp.setText("");
            erreurinterc.setText("interception non Valide");
        } else if (despline.getText().isEmpty()) {
            erreursp.setText("");
            erreurinterc.setText("");
            erreurdes.setText("despline Vide");
        } else if (!cc.isNumber(despline.getText())) {
            erreursp.setText("");
            erreurinterc.setText("");
            erreurdes.setText("despline non Valide");
        } else if (accompagnement.getText().isEmpty()) {
            erreursp.setText("");
            erreurinterc.setText("");
            erreurdes.setText("");
            erreuraco.setText("accompagnement Vide");
        } else if (!cc.isNumber(accompagnement.getText())) {
            erreursp.setText("");
            erreurinterc.setText("");
            erreurdes.setText("");
            erreuraco.setText("accompagnement non Valide");
        } else if (obeissance.getText().isEmpty()) {
            erreursp.setText("");
            erreurinterc.setText("");
            erreurdes.setText("");
            erreuraco.setText("");
            erreurobe.setText("obeissance Vide");
        } else if (!cc.isNumber(obeissance.getText())) {
            erreursp.setText("");
            erreurinterc.setText("");
            erreurdes.setText("");
            erreuraco.setText("");
            erreurobe.setText("obeissance non Valide");
        } else if (dated.getValue().toString().isEmpty()) {
            erreursp.setText("");
            erreurinterc.setText("");
            erreurdes.setText("");
            erreuraco.setText("");
            erreurobe.setText("");
            erreurdeb.setText("Date debut vide");
        } else if (dateF.getValue().toString().isEmpty()) {
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
    private void ajouter(ActionEvent event) throws ParseException {

        if (verif()) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date datedeb = format.parse(dated.getValue().toString());
            Date datFin = format.parse(dateF.getValue().toString());
            if (datFin.before(datedeb)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setContentText("verifier date");
                alert.showAndWait();

            } else {
                Userr u = new Userr();
                u.setId(id);
                float ntotal = Float.parseFloat(despline.getText()) + Float.parseFloat(obeissance.getText()) + Float.parseFloat(accompagnement.getText()) + Float.parseFloat(interception.getText()) / 4;
                FicheDeDressage fdd = new FicheDeDressage(u, specialite.getText(), Float.parseFloat(despline.getText()), Float.parseFloat(obeissance.getText()), Float.parseFloat(accompagnement.getText()), Float.parseFloat(interception.getText()), ntotal, datedeb, datFin, a, 1);
                cfdd.ajouterFicheDeDressage(fdd);
//                cfdd.ajouterFicheDeDressage(2, specialite.getText(), Float.parseFloat(despline.getText()), Float.parseFloat(obeissance.getText()), Float.parseFloat(accompagnement.getText()), Float.parseFloat(interception.getText()), ntotal, datedeb, datFin, id, 1);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("fiche de soin créer");
                alert.showAndWait();
            }
        }

    }

    @FXML
    private void annuler(ActionEvent event) {
    }

    private void printSetup(Node node, Stage owner) {
        // Create the PrinterJob
        PrinterJob job = PrinterJob.createPrinterJob();

        if (job == null) {
            return;
        }

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

    @FXML
    private void imprimer(ActionEvent event) {
//
//        Document doc = new Document();
//        PdfWriter.getInstance(doc, new FileOutputStream("fiche de dressage.pdf"));
//        doc.open();
//        doc.add(new Paragraph("                             Fiche de Dressage:"));
//        doc.add(new Paragraph("proprietére :" + prop.getText()));
//        doc.add(new Paragraph("despline" + String.valueOf(despline.getText())));
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
    }

}
