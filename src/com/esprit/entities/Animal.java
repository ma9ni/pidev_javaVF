/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

import Utilities.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.ImageView;

/**
 *
 * @author Ahmed
 */
public class Animal {

    private int idAnimal;
    private String type;
    private String nom;
    private String race;
    private java.sql.Date date_naissance;
    private String description;
    private String photo;
    private ImageView image;
    private Double poid;
    private int etat;
    private int id_membre;
    private String Nomproprietaire;
    private String sexe;

    public Animal() {
    }

    public Animal(int idAnimal, String nom, String description) {
        this.idAnimal = idAnimal;
        this.nom = nom;
        this.description = description;
    }

    public Animal(String type, String nom, String race, Date date_naissance, String description, String photo, ImageView image, Double poid, int etat, int id_membre) {

        this.type = type;
        this.nom = nom;
        this.race = race;
        this.date_naissance = date_naissance;
        this.description = description;
        this.photo = photo;
        this.image = image;
        this.poid = poid;
        this.etat = etat;
        this.id_membre = id_membre;
    }

    public Animal(String type, String nom, String race, Date date_naissance, String description, String photo, Double poid, int etat, int id_membre) {

        this.type = type;
        this.nom = nom;
        this.race = race;
        this.date_naissance = date_naissance;
        this.description = description;
        this.photo = photo;

        this.poid = poid;
        this.etat = etat;
        this.id_membre = id_membre;
    }

    public Animal(String type, String nom, String race, Date date_naissance, String description, Double poid, int etat, int id_membre) {

        this.type = type;
        this.nom = nom;
        this.race = race;
        this.date_naissance = date_naissance;
        this.description = description;
        this.poid = poid;
        this.etat = etat;
        this.id_membre = id_membre;
    }

    public Animal(String type, String nom, String race, Date date_naissance, String description, String photo, ImageView image, Double poid, int etat, int id_membre, String Nomproprietaire, String sexe, Statement stmt) {
        this.type = type;
        this.nom = nom;
        this.race = race;
        this.date_naissance = date_naissance;
        this.description = description;
        this.photo = photo;
        this.image = image;
        this.poid = poid;
        this.etat = etat;
        this.id_membre = id_membre;
        this.Nomproprietaire = Nomproprietaire;
        this.sexe = sexe;
        this.stmt = stmt;
    }

    public String getNomproprietaire() {
        return Nomproprietaire;
    }

    public void setNomproprietaire(String Nomproprietaire) {
        this.Nomproprietaire = Nomproprietaire;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public Double getPoid() {
        return poid;
    }

    public void setPoid(Double poid) {
        this.poid = poid;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getId_membre() {
        return id_membre;
    }

    public void setId_membre(int id_membre) {
        this.id_membre = id_membre;
    }

    /// Les attributs
    Connection conn = DataSource.getInstance().getConnection();
    private Statement stmt;

    public int ajouterAnimal() {
        try {
            stmt = conn.createStatement();
        } catch (SQLException ex) {

            System.out.println("erreur lors de la creation du statment \n");
            System.out.println(ex.getMessage());
        }

        String sql = "INSERT INTO `animal`("
        + "type, "
        + "nom ,"
        + "race ,"
        + "date_naissance,"
        + "description ,"
        + "`poid` ,"
        + "`etat`"
        + ") "
        + " VALUES ( '" + this.type + "','" + this.nom + "','" + this.race + "','" + this.date_naissance + "','" + this.description + "'," + this.poid + ",1);";

        try {
            stmt.executeUpdate(sql);
            System.out.println(" l'ajout est effectue");
            return 1;
        } catch (SQLException e) {
            System.out.println("erreur lors de l'exxecution de la requete d'ajout d'un animal \n");
            System.out.println(e.getMessage());

        }
        return 0;

    }

    //*************************************************************************************************************
//SELEC TALL
//*************************************************************************************************************
    public ArrayList<Animal> selectAll() {

        ArrayList<Animal> ani = new ArrayList<>();
        PreparedStatement pr;

        try {
            pr = conn.prepareStatement("SELECT * FROM animal  ");
            ResultSet res = pr.executeQuery();
            while (res.next()) {
                Animal animal = new Animal();
                animal.setIdAnimal(res.getInt("id_animal"));
                animal.setType(res.getString("type"));
                animal.setNom(res.getString("nom"));
                animal.setRace(res.getString("race"));
                animal.setDescription(res.getString("description"));
                animal.setDate_naissance(res.getDate("date_naissance"));
                animal.setPoid(res.getDouble("poid"));
                ani.add(animal);

            }
        } catch (SQLException ex) {
        }

        return ani;

    }

    public void supprimer(int id) {
        try {
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println("erreur lors de la creation du statment \n");
            System.out.println(ex.getMessage());
        }

        String sql = "update `animal`  set etat=0   WHERE id_animal=" + id;

        try {
            stmt.executeUpdate(sql);
            System.out.println(" supp est effectue");

        } catch (SQLException e) {
            System.out.println("erreur lors de l'exxecution de la requete d'ajout d'un SosDisparition\n");
            System.out.println(e.getMessage());
        }
    }

    public int modifierAnimal() {
        try {
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println("erreur lors de la creation du statment \n");
            System.out.println(ex.getMessage());
        }

        String maRequette = "UPDATE `animal` SET "
        + "`id_animal`=" + this.idAnimal + ","
        + "`type`=" + this.type + ","
        + "`nom`=" + this.nom + ","
        + "`race`=" + this.race + ","
        + "`date_naissance`=" + this.date_naissance + ","
        + "`description`=" + this.description + ","
        + "`poid`=" + this.poid + ""
        + "WHERE id_animal=" + idAnimal
        + ";";

        try {
            stmt.executeUpdate(maRequette);
            System.out.println(" l'ajout est effectue");
            return 1;
        } catch (SQLException e) {
            System.out.println("erreur lors de l'exxecution de la requete de la supprission \n");
            System.out.println(e.getMessage());

        }
        return 0;
    }
    //*************************************************************************************************************
//GET Animal
//*************************************************************************************************************

    public Animal getAnimal(int id) {

        Animal leResultat = new Animal();

        try {
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println("erreur lors de la creation du statment \n");
            System.out.println(ex.getMessage());
        }

        String maRequette = "SELECT * FROM animal WHERE id_animal = " + id + ";";

        try {
            ResultSet res = stmt.executeQuery(maRequette);
            System.out.println(" la recuperation des donnees est effectue");
            while (res.next()) {
                leResultat.setIdAnimal(res.getInt(1));

                leResultat.setType(res.getString(2));
                leResultat.setNom(res.getString(3));
                leResultat.setRace(res.getString(4));

                leResultat.setDate_naissance(res.getDate(5));
                leResultat.setPhoto(res.getString(6));
                leResultat.setDescription(res.getString(7));
                leResultat.setPoid(res.getDouble(8));
            }

        } catch (SQLException e) {
            System.out.println("erreur lors de l'exxecution de la requete de la supprission \n");
            System.out.println(e.getMessage());
        }

        return leResultat;
    }
//*************************************************************************************************************
//GET Animal From Membre Houssem
//*************************************************************************************************************

    public ArrayList<Animal> selectAllFromMembre(int id) {
        ArrayList<Animal> le = new ArrayList();
        try {
            PreparedStatement st = conn.prepareStatement("SELECT `id_animal`, `type`, `nom`, `race`, `date_naissance`, `description`, `photo`, `poid`, `id_membre`, `etat` FROM `animal` WHERE `etat`=1 AND `id_membre`=" + id);
            System.out.println("1");
            ResultSet res = st.executeQuery();
            while (res.next()) {
                System.out.println("2");
                Animal e = new Animal();
                e.setIdAnimal(res.getInt("id_animal"));
                e.setType(res.getString("type"));
                e.setRace(res.getString("race"));
                e.setPhoto(res.getString("photo"));
                e.setNom(res.getString("nom"));

                le.add(e);
            }
            return le;
        } catch (SQLException ex) {
            System.out.println("ereuur dans l'aafichage  xxx");
            return le;
        }
    }

}
