/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.GUI;

import static com.esprit.GUI.IHM_liste_membreController.idZbotrech;
import com.esprit.SERVICE.ControlleurAdoption;
import com.esprit.entities.Adoption;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author houssem
 */
public class IHM_Adoption_afficherController implements Initializable {

    ControlleurAdoption cm = new ControlleurAdoption();
    ArrayList<Adoption> membres = cm.selectAll();
    ObservableList<Adoption> membre = FXCollections.observableArrayList(membres);

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField search;
    @FXML
    private TableView<Adoption> adoption;
    @FXML
    private TableColumn<Adoption, String> description;

    @FXML
    TableColumn<Adoption, String> type;
    @FXML
    TableColumn<Adoption, String> lieu;
    @FXML

    private AnchorPane dialog;
    @FXML
    private TableColumn<Adoption, Button> action;
    @FXML
    private CheckBox adoptdefinitive;
    @FXML
    private CheckBox aadoptetemporaire;

    public static Adoption adop;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Adoption ad = new Adoption();

        for (Adoption adp : ad.selectAll()) {

            adoption.getItems().add(adp);
            type.setCellValueFactory(new PropertyValueFactory<>("type"));
            lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
            description.setCellValueFactory(new PropertyValueFactory<>("description"));
            //  action.addEventHandler(EventType.ROOT, eventHandler);

        }
        initial();

    }

    @FXML
    private void retour(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("IHM_Adoption.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(new Scene(root1));
        stage.show();

    }

    @FXML
    private void Annuler(ActionEvent event) {
    }

    @FXML
    private void Afficher(ActionEvent event) {
        Adoption m = adoption.getSelectionModel().getSelectedItem();

        if (m == null) {
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("choisir une annonce pour l'afficher");
            alert.show();
        } else {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("IHM_afiche_Annonce_Adoption.fxml"));
                this.adop = m;

                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.hide();
                stage.setScene(new Scene(root1));
                stage.setX(m.getIdMembre());
                stage.show();
                System.out.println(idZbotrech);
            } catch (IOException e) {

            }

        }

    }

    private void initial() {
        FilteredList<Adoption> filteredData = new FilteredList<>(membre, p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(ev -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                try {
                    if (ev.getType().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches first name.
                    } else if (ev.getType().contains(lowerCaseFilter))/*.toLowerCase().contains(lowerCaseFilter)) */ {
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
        SortedList<Adoption> sortedData = new SortedList<>(filteredData);
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(adoption.comparatorProperty());
        // 5. Add sorted (and filtered) data to the table.
        adoption.setItems(sortedData);
    }

}
