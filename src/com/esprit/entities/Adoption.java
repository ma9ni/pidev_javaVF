/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

import Utilities.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author houssem
 */
public class Adoption {

    private int idAdoption;
    private int idMembre;
    private Date dateAnnonce;
    private String lieu;
    private int etatAdoption;
    private String description;
    private String type;
    private int idAnimal;
    //  private Animal idAnimal;
//constructeurs

    public Adoption() {
    }

    public Adoption(int idMembre, String lieu, String description, String type, int idAnimal) {

    }
//    public Adoption(Userr idMembre, String lieu, String description, String type, Animal idAnimal) {
//        this.idMembre = idMembre;
//        this.lieu = lieu;
//        this.description = description;
//        this.type = type;
//        this.idAnimal = idAnimal;
//    }

    public Adoption(int idMembre, Date dateAnnonce, String lieu, int etatAdoption, String description) {
        this.idMembre = idMembre;
        this.dateAnnonce = dateAnnonce;
        this.lieu = lieu;
        this.etatAdoption = etatAdoption;
        this.description = description;
    }
// les getters et stters

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    // getters and setters
    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdAdoption() {
        return idAdoption;
    }

    public void setIdAdoption(int idAdoption) {
        this.idAdoption = idAdoption;
    }

    public int getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(int idMembre) {
        this.idMembre = idMembre;
    }

    public Date getDateAnnonce() {
        return dateAnnonce;
    }

    public void setDateAnnonce(Date dateAnnonce) {
        this.dateAnnonce = dateAnnonce;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public int getEtatAdoption() {
        return etatAdoption;
    }

    public void setEtatAdoption(int etatAdoption) {
        this.etatAdoption = etatAdoption;
    }

    // les methodes
    // les attributs relatifs au methodes CRUD
    Connection conn = DataSource.getInstance().getConnection();
    private Statement stmt;

    /**
     * l'ajou
     *
     * @return t*
     */
    public int ajouterDonnation() {
        try {
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println("erreur lors de la creation du statment \n");
            System.out.println(ex.getMessage());
        }
        // preparation de la requette
        String maRequette = "INSERT INTO `adoption`("
                + "`lieu`, "
                + "`type`, "
                + "`dateAnnonce`, "
                + "`id_membre`,"
                + "`etatAdoption`,"
                + "`description`,"
                + "`id_animal`"
                + ") "
                + " VALUES ( '"
                + this.lieu + "','donner',now(),"
                + this.idMembre
                + ",1,'"
                + this.description + "',"
                + this.idAnimal
                + ");";
        System.out.println(maRequette);
        // execution de la requette
        try {
            stmt.executeUpdate(maRequette);
            System.out.println(" l'ajout est effectue");
            return 1;
        } catch (SQLException ahmed_makni) {
            System.out.println("erreur lors de l'exxecution de la requete d'ajout d'une donnation d'un annimal \n");
            System.out.println(ahmed_makni.getMessage());

        }
        return 0;

    }

    public int ajouterDelegation() {
        try {
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println("erreur lors de la creation du statment \n");
            System.out.println(ex.getMessage());
        }
        // preparation de la requette
        String maRequette = "INSERT INTO `adoption`("
                + "`lieu`, "
                + "`type`, "
                + "`dateAnnonce`, "
                + "`id_membre`,"
                + "`etatAdoption`,"
                + "`description`,"
                + "`id_animal`"
                + ") "
                + " VALUES ( '"
                + this.lieu + "','deleger',now(),"
                + this.idMembre
                + ",1,'"
                + this.description + "',"
                + this.idAnimal
                + "');";
        // execution de la requette
        try {
            stmt.executeUpdate(maRequette);
            System.out.println(" l'ajout est effectue");
            return 1;
        } catch (SQLException ahmed_makni) {
            System.out.println("erreur lors de l'exxecution de la requete d'ajout d'un accessoire \n");
            System.out.println(ahmed_makni.getMessage());

        }
        return 0;

    }

    //supprission d'un acc
    public int supprimerAdoption() {
        try {
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println("erreur lors de la creation du statment \n");
            System.out.println(ex.getMessage());
        }
        // preparation de la requette
        String maRequette = "UPDATE `adoption` "
                + "SET `Etat_Accessoire`= 0"
                + " WHERE Id_Accessoire = "
                + this.idAdoption
                + ";";

        // execution de la requette
        try {
            stmt.executeUpdate(maRequette);
            System.out.println(" l'ajout est effectue");
            return 1;
        } catch (SQLException ahmed_makni) {
            System.out.println("erreur lors de l'exxecution de la requete de la supprission \n");
            System.out.println(ahmed_makni.getMessage());

        }
        return 0;

    }

    //la methode modifier
    public int modifierAdoption() {
        try {
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println("erreur lors de la creation du statment \n");
            System.out.println(ex.getMessage());
        }
        // preparation de la requette
        String maRequette = "UPDATE `adoption` SET "
                + "`dateAnnonce`=" + this.dateAnnonce + ","
                + "`lieu`=" + this.lieu + ","
                + "`etatAdoption`=" + this.etatAdoption + ","
                + "`idMembre`=" + this.idMembre
                + "`description`=" + this.description
                + "WHERE 1 "
                + ";";

        // execution de la requette
        try {
            stmt.executeUpdate(maRequette);
            System.out.println(" l'ajout est effectue");
            return 1;
        } catch (SQLException ahmed_makni) {
            System.out.println("erreur lors de l'exxecution de la requete de la supprission \n");
            System.out.println(ahmed_makni.getMessage());

        }
        return 0;
    }

    //methode consultation
    public Adoption getAdoption(int id) {
        Adoption leResultat = new Adoption();
        try {
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println("erreur lors de la creation du statment \n");
            System.out.println(ex.getMessage());
        }
        // preparation de la requette
        String maRequette = "SELECT * FROM adoption WHERE Id_Adoption = " + id + ";";

        // execution de la requette
        try {
            ResultSet res = stmt.executeQuery(maRequette);
            System.out.println(" la recuperation des donnees est effectue");
            while (res.next()) {
                leResultat.idAdoption = res.getInt(1);
                leResultat.idMembre = res.getInt(2);
                leResultat.dateAnnonce = res.getDate(3);
                leResultat.etatAdoption = res.getInt(4);
                leResultat.description = res.getString(5);
                /*et la suite ***/
            }

        } catch (SQLException houssem_marnissi) {
            System.out.println("erreur lors de l'exxecution de la requete de la supprission \n");
            System.out.println(houssem_marnissi.getMessage());

        }

        return leResultat;
    }

    public ArrayList<Adoption> selectAll() {
        ArrayList<Adoption> le = new ArrayList();
        try {
            PreparedStatement st = conn.prepareStatement(
                    "SELECT id_adoption,id_membre,type,dateAnnonce,lieu"
                    + ",etatAdoption,description FROM adoption");
            ResultSet res = st.executeQuery();
            while (res.next()) {
                Adoption e = new Adoption();
                e.setIdAdoption(res.getInt(1));
                e.setLieu(res.getString(5));
                e.setIdMembre(res.getInt(2));
                e.setDateAnnonce(res.getDate(4));
                e.setType(res.getString(3));
                e.setDescription(res.getString(7));

                le.add(e);
            }
            return le;
        } catch (SQLException ex) {
            System.out.println("ereuur dans l'aafichage");
            return null;
        }
    }
}
