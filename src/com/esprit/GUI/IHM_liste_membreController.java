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
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author houssem
 */
public class IHM_liste_membreController implements Initializable {

    @FXML
    private TableView<Userr> table;
    @FXML
    private TableColumn<Userr, String> id;
    @FXML
    private TableColumn<Userr, String> typr;
    @FXML
    private TableColumn<Userr, String> nom;
    @FXML
    private TableColumn<Userr, String> prenom;
    @FXML
    private TableColumn<Userr, String> adresse;
    @FXML
    private TableColumn<Userr, String> num;
    @FXML
    private TableColumn<Userr, String> email;

    public static int idZbotrech;

    UserController cm = new UserController();
    ArrayList<Userr> membres = cm.selectAll(IHM_loginController.membre.getId());
    ObservableList<Userr> membre = FXCollections.observableArrayList(membres);
    @FXML
    private TextField reherchefield;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        UserController m = new UserController();
        System.out.println("kuhdljsmjjdk;bdomjb" + IHM_loginController.membre.getId());

        for (Userr mem : m.selectAll(IHM_loginController.membre.getId())) {
            table.getItems().add(mem);
            id.setCellValueFactory(new PropertyValueFactory<>("idUserr"));
            typr.setCellValueFactory(new PropertyValueFactory<>("type"));
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            num.setCellValueFactory(new PropertyValueFactory<>("num"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
            ;

        }
        initial();

    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UserrIHM.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();

    }

    @FXML
    private void modifier(ActionEvent event) {
        Userr m = table.getSelectionModel().getSelectedItem();

        if (m == null) {
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("choisir un membre pour modifier");
            alert.show();
        } else {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("IHM_membre_modifier.fxml"));
                this.idZbotrech = m.getId();

                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.setX(m.getId());
                stage.show();
                System.out.println(idZbotrech);
            } catch (IOException e) {

            }

        }
    }

    @FXML
    private void supprimer(ActionEvent event) {

        Userr m = table.getSelectionModel().getSelectedItem();

        if (m == null) {
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("choisir un membre pour supprimer");
            alert.show();
        } else {
            UserController sm = new UserController();

            int a = sm.supprimerMembre(m);

            if (a == 1) {

                Alert alert;
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("suppréssion effectuer avec succés refléchir la page pour voir les modifications");
                alert.show();
            } else {
                Alert alert;
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("echec de suppression");
                alert.show();
            }
        }

    }

    @FXML
    private void annuler(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void reflechir(ActionEvent event) throws IOException {
        Parent parent125 = FXMLLoader.load(getClass().getResource("IHM_liste_membre.fxml"));
        Scene scene1116 = new Scene(parent125);
        Stage stage1116 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage1116.hide();
        stage1116.setScene(scene1116);
        stage1116.show();
    }

    @FXML
    private void afficher(ActionEvent event) throws IOException {
        Userr m = table.getSelectionModel().getSelectedItem();

        if (m == null) {
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("choisir un membre pour l'afficher");
            alert.show();
        } else {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("IHM_membre_afficher.fxml"));
                this.idZbotrech = m.getId();

                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.setX(m.getId());
                stage.show();
                System.out.println(idZbotrech);
            } catch (IOException e) {

            }

        }
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        Parent parent125 = FXMLLoader.load(getClass().getResource("IHM_login.fxml"));
        Scene scene1116 = new Scene(parent125);
        Stage stage1116 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage1116.hide();
        stage1116.setScene(scene1116);

        stage1116.show();
        Notifications.create().text("vous etes deconnecter ").showInformation();;
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

    private void initial() {
        FilteredList<Userr> filteredData = new FilteredList<>(membre, p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        reherchefield.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(ev -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                try {
                    if (ev.getUsername().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches first name.
                    } else if (ev.getUsername().contains(lowerCaseFilter))/*.toLowerCase().contains(lowerCaseFilter)) */ {
                        return true; // Filter matches last name.
                    }
                    return false; // Does not match.
                } catch (NullPointerException ex) {
                    System.out.println(ex.toString());
                }
                return false;
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Userr> sortedData = new SortedList<>(filteredData);
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        // 5. Add sorted (and filtered) data to the table.
        table.setItems(sortedData);
    }

}
