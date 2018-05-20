/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.GUI;

import static com.esprit.GUI.IHM_liste_membreController.idZbotrech;
import com.esprit.SERVICE.ServiceAnimal;
import com.esprit.entities.Animal;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author houssem
 */
public class IHM_Animal_DelegerController implements Initializable {

    @FXML
    private TableView<Animal> tableAnimal;
    @FXML
    private TableColumn<Animal, String> photo;
    @FXML
    private TableColumn<Animal, String> race;
    @FXML
    private TableColumn<Animal, String> sexe;
    @FXML
    private TableColumn<Animal, String> nom;
    @FXML
    private TableColumn<Animal, String> dateNaissance;
    public static int idAnimal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(idZbotrech);

        ServiceAnimal sa = new ServiceAnimal();
        System.out.println("id membre houxwa    " + IHM_loginController.membre.getId());
        for (Animal s : sa.selectAll(IHM_loginController.membre.getId())) {
            tableAnimal.getItems().add(s);
            race.setCellValueFactory(new PropertyValueFactory<>("race"));
            sexe.setCellValueFactory(new PropertyValueFactory<>("type"));
            photo.setCellValueFactory(new PropertyValueFactory<>("photo"));
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        }
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
    private void Anuler(ActionEvent event) {
    }

    @FXML
    private void Suivant(ActionEvent event) {
        Animal m = tableAnimal.getSelectionModel().getSelectedItem();

        if (m == null) {
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("choisir un animal pour deleger ou ajouter un autre animal");
            alert.show();
        } else {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("IHM_ajouter_delegation.fxml"));
                this.idAnimal = m.getIdAnimal();

                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.hide();
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (IOException e) {

            }

        }
    }

    @FXML
    private void Ajouter(ActionEvent event) {
    }

}
