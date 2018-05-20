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
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author houssem
 */
public class IHM_inscriptionController implements Initializable {

    @FXML
    private TextField nom;
    private TextField prenom;
    @FXML
    private TextField tel;
    @FXML
    private Button ajouter;
    @FXML
    private TextField motdepasse;
    @FXML
    private TextField adresse;
    @FXML
    private TextField email;
    @FXML
    private TextField conf;
    @FXML
    private ChoiceBox<String> type;
    @FXML
    private Button brows;
    @FXML
    private TextField doc;
    @FXML
    private Label Erornom;
    @FXML
    private Label ErorEmail;
    @FXML
    private Label Errorprenom;
    @FXML
    private Label errorMotPAsse;
    @FXML
    private Label errorConfirmerMotdePass;
    @FXML
    private Label erroreTel;
    @FXML
    private Label ErorAdresse;
    @FXML
    private Label ErrorePhoto;
    @FXML
    private Label Erortype;

    public static int code;
    public static Userr m;
    public static String types;

    @FXML
    private ImageView image;

    private static Path destination1;
    private static File selectedfile1;
    @FXML
    private AnchorPane imageView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        type.getItems().add("simple utilisateur");
        type.getItems().add("veterinere");
        type.getItems().add("dresseur");
    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        UserController ca = new UserController();
        ControlleurChamps cc = new ControlleurChamps();
        int c = 1;

        if (nom.getText().isEmpty() || !cc.isName(nom.getText())) {
            c = 0;
            if (nom.getText().isEmpty()) {
                Erornom.setText("il faut ajouter un username");

            } else {
                Erornom.setText("il faut ajouter un username  valide");
            }

        }

        if (tel.getText().isEmpty() || !cc.isNum(tel.getText())) {
            c = 0;
            if (tel.getText().isEmpty()) {
                erroreTel.setText("il faut ajouter un numero de téléphone");

            } else {
                erroreTel.setText("il faut ajouter un numero  valide");
            }

        }
        if (email.getText().isEmpty() || !cc.isEmail(email.getText()) || ca.emailExiste(email.getText()) == 1) {
            c = 0;
            if (email.getText().isEmpty()) {
                ErorEmail.setText("il faut ajouter un email");

            } else if (!cc.isEmail(email.getText())) {
                ErorEmail.setText("il faut ajouter un email valide");
            } else {
                ErorEmail.setText("email existe déja");
            }

        }

        if (motdepasse.getText().isEmpty() || !conf.getText().equals(motdepasse.getText()) || motdepasse.getText().length() < 8) {
            c = 0;
            if (motdepasse.getText().isEmpty()) {
                errorMotPAsse.setText("il faut ajouter un mot de passe");
            } else if (motdepasse.getText().length() < 8) {
                errorMotPAsse.setText("il faut plus de 8 caractére");
            } else if (!conf.getText().equals(motdepasse.getText())) {

                errorConfirmerMotdePass.setText("il faut confirmer le mot de passe");

            }
        } else if (type.getValue() == "simple utilisateur" && c == 1) {
            m = new Userr(nom.getText(), email.getText(), motdepasse.getText(), Integer.parseInt(tel.getText()), doc.getText(), adresse.getText());
            if (selectedfile1 != null) {
                Files.copy(selectedfile1.toPath(), destination1, StandardCopyOption.REPLACE_EXISTING);
            }

            Random rand = new Random();

            this.code = rand.nextInt(4000) + 1000;
            ServiceEmail se = new ServiceEmail();
            se.sendEmail(email.getText(), "votre code confirmation est :" + code + "");

            types = "simpleUtilisateur";
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("IHM_ConfirmationCode.fxml"));

            Parent root1;
            try {
                root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.hide();
                stage.setScene(new Scene(root1));
                stage.setX(m.getId());
                stage.show();
            } catch (IOException ex) {
                System.out.println("error dans la redirection au confirmation de compte");
            }

        } else if (type.getValue() == "veterinaire" && c == 1) {
            Random rand = new Random();
            if (selectedfile1 != null) {
                Files.copy(selectedfile1.toPath(), destination1, StandardCopyOption.REPLACE_EXISTING);
            }

            this.code = rand.nextInt(4000) + 1000;
            ServiceEmail se = new ServiceEmail();
            se.sendEmail(email.getText(), "votre code confirmation est :" + code + "");

            types = "Veterinaire";
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("IHM_ConfirmationCode.fxml"));

            Parent root1;
            try {
                root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.hide();
                stage.setScene(new Scene(root1));
                stage.setX(m.getId());
                stage.show();
            } catch (IOException ex) {
                System.out.println("error dans la redirection au confirmation de compte");
            }

        } else if (type.getValue() == "dresseur" && c == 1) {
            if (selectedfile1 != null) {
                Files.copy(selectedfile1.toPath(), destination1, StandardCopyOption.REPLACE_EXISTING);
            }

            Random rand = new Random();

            this.code = rand.nextInt(4000) + 1000;
            ServiceEmail se = new ServiceEmail();
            se.sendEmail(email.getText(), "votre code confirmation est :" + code + "");

            types = "dresseur";
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("IHM_ConfirmationCode.fxml"));

            Parent root1;
            try {
                root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.hide();
                stage.setScene(new Scene(root1));
                stage.setX(m.getId());
                stage.show();
            } catch (IOException ex) {
                System.out.println("error dans la redirection au confirmation de compte");
            }

        } else {
            Erortype.setText("il faut choisir un type d'utilisation");

        }
    }

    @FXML
    private void brows(ActionEvent event) throws MalformedURLException {

        FileChooser fc = new FileChooser();
        selectedfile1 = fc.showOpenDialog(null);
        destination1 = Paths.get("C:\\wamp64\\www\\PIZanimo\\photo_membre", selectedfile1.getName());
        doc.setText(selectedfile1.getName());

    }

    @FXML
    private void annuler(ActionEvent event) {
        Stage stage = new Stage();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
    }

}
